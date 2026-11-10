/*
 * WorkerController.java
 *
 * Phiên bản: 1.0
 *
 * Ngày: 21/10/2026
 *
 * Dự án LAB211 - Quản lý thông tin nhân viên.
 * Class này điều khiển chương trình và xử lý các chức năng quản lý nhân viên.
 */
package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import model.SalaryHistory;
import model.SalaryStatus;
import model.Worker;
import view.WorkerView;

/**
 * Điều khiển toàn bộ chương trình quản lý nhân viên.
 *
 * Class này kết nối View với dữ liệu nhân viên và xử lý các chức năng chính của
 * chương trình, bao gồm thêm nhân viên, điều chỉnh lương và hiển thị lịch sử
 * thay đổi lương.
 */
public class WorkerController {

    // Danh sách lưu trữ tất cả nhân viên đã được thêm vào chương trình.
    private final List<Worker> workers = new ArrayList<>();

    // Danh sách lưu trữ lịch sử của tất cả các lần điều chỉnh lương.
    private final List<SalaryHistory> salaryHistories = new ArrayList<>();

    // Đối tượng View dùng để nhận dữ liệu và hiển thị thông tin cho người dùng.
    private final WorkerView view = new WorkerView();

    /**
     * Khởi chạy chương trình quản lý nhân viên.
     *
     * Gọi menu chính và xử lý trường hợp chương trình không thể tiếp tục nhận
     * dữ liệu đầu vào.
     */
    public void run() {
        try {
            runMenu();
        } catch (IllegalStateException e) {
            view.showMessage("\n" + e.getMessage() + " Program terminated.");
        }
    }

    /**
     * Hiển thị menu chính và xử lý lựa chọn của người dùng.
     *
     * Menu được hiển thị liên tục cho đến khi người dùng chọn chức năng số 5 để
     * thoát chương trình.
     */
    private void runMenu() {
        while (true) {
            view.showMenu();

            // Thực hiện chức năng tương ứng với lựa chọn của người dùng.
            switch (view.inputChoice(1, 5)) {
                case 1:
                    addWorker();
                    break;
                case 2:
                    processIncreaseSalary();
                    break;
                case 3:
                    processDecreaseSalary();
                    break;
                case 4:
                    view.displaySalaryHistory(getInfomationSalary());
                    break;
                case 5:
                    view.showMessage("Program terminated.");
                    return;
                default:
                    break;
            }
        }
    }

    /**
     * Thêm một nhân viên mới vào danh sách.
     *
     * Yêu cầu người dùng nhập mã nhân viên chưa tồn tại, sau đó nhập các thông
     * tin còn lại và tạo đối tượng Worker.
     */
    private void addWorker() {
        view.showMessage("\n--------- Add Worker ----------");

        String code = inputUniqueWorkerCode();
        Worker worker = view.inputWorkerDetails(code);

        workers.add(worker);

        view.showMessage("Worker added successfully.");
    }

    /**
     * Yêu cầu người dùng nhập mã nhân viên cho đến khi mã đó không bị trùng.
     *
     * @return mã nhân viên chưa tồn tại trong danh sách
     */
    private String inputUniqueWorkerCode() {
        while (true) {
            String code = view.inputWorkerCode();

            if (!checkDuplicateCode(code)) {
                return code;
            }

            view.showMessage("Worker code already exists. Try again.");
        }
    }

    /**
     * Xử lý chức năng tăng lương cho nhân viên.
     *
     * Yêu cầu người dùng nhập mã nhân viên và số tiền cần tăng. Nếu việc tăng
     * lương thành công, hiển thị thông báo thành công. Nếu xảy ra lỗi, bắt
     * ngoại lệ và hiển thị nguyên nhân.
     */
    private void processIncreaseSalary() {
        view.showMessage("\n----------- Up Salary ----------");

        String code = view.inputWorkerCode();

        try {
            changeSalary(SalaryStatus.UP,code,view.inputSalaryAmount() );

            view.showMessage("Salary increased successfully.");

        } catch (Exception e) {
            view.showMessage("Cannot increase salary: " + e.getMessage());
        }
    }

    /**
     * Xử lý chức năng giảm lương cho nhân viên.
     *
     * Yêu cầu người dùng nhập mã nhân viên và số tiền cần giảm. Nếu việc giảm
     * lương thành công, hiển thị thông báo thành công. Nếu xảy ra lỗi, bắt
     * ngoại lệ và hiển thị nguyên nhân.
     */
    private void processDecreaseSalary() {
        view.showMessage("\n---------- Down Salary ---------");

        String code = view.inputWorkerCode();

        try {
            changeSalary(SalaryStatus.DOWN, code, view.inputSalaryAmount());

            view.showMessage("Salary decreased successfully.");

        } catch (Exception e) {
            view.showMessage("Cannot decrease salary: " + e.getMessage());
        }
    }

    /**
     * Thực hiện tăng hoặc giảm lương cho một nhân viên.
     *
     * Kiểm tra dữ liệu đầu vào, tìm nhân viên theo mã, tính toán mức lương mới,
     * cập nhật lương và lưu lịch sử điều chỉnh.
     *
     * @param status loại điều chỉnh lương, gồm UP (tăng) hoặc DOWN (giảm)
     * @param code mã nhân viên cần điều chỉnh lương
     * @param amount số tiền cần tăng hoặc giảm
     * @return true nếu việc điều chỉnh lương thành công
     * @throws Exception nếu trạng thái lương không hợp lệ, mã nhân viên bị
     * trống hoặc không tồn tại, số tiền không lớn hơn 0, số tiền giảm vượt quá
     * lương hiện tại hoặc mức lương mới quá lớn
     */
    public boolean changeSalary(
            SalaryStatus status, String code, double amount)
            throws Exception {

        /* Bước 1: Kiểm tra dữ liệu đầu vào.Salary status cannot be null. xảy ra 
        khi tham số status truyền vào phương thức changeSalary() có giá trị null.
        SalaryStatus là enum gồm hai giá trị UP và DOWN. Controller tự truyền hai 
        giá trị này vào changeSalary(), nên người dùng không thể nhập null thông 
        qua menu thông thường. Đây là trường hợp kiểm tra dữ liệu phòng ngừa để 
        đảm bảo phương thức hoạt động an toàn khi được gọi từ những nơi khác."*/
        if (status == null) {
            throw new Exception("Salary status cannot be null.");
        }

        if (isBlank(code)) {
            throw new Exception("Worker code cannot be empty.");
        }

        if (!Double.isFinite(amount) || amount <= 0) {
            throw new Exception("Salary amount must be greater than 0.");
        }

        // Bước 2: Tìm nhân viên theo mã được cung cấp.
        Worker worker = findWorkerByCode(code);

        if (worker == null) {
            throw new Exception("Worker code does not exist.");
        }

        // Đảm bảo số tiền giảm không lớn hơn mức lương hiện tại.
        if (status == SalaryStatus.DOWN && amount > worker.getSalary()) {
            throw new Exception(
                    "Decrease amount must not exceed current salary.");
        }

        // Bước 3: Tính mức lương mới dựa trên loại điều chỉnh.
        double salary = status == SalaryStatus.UP
                ? worker.getSalary() + amount
                : worker.getSalary() - amount;

        /* Kiểm tra kết quả tính toán có vượt quá giới hạn kiểu double hay không.
        Ví dụ 1.0E308 = 1X10^308*/
        if (!Double.isFinite(salary)) {
            throw new Exception("Resulting salary is too large.");
        }

        // Bước 4: Cập nhật lương mới và lưu lịch sử điều chỉnh.
        worker.setSalary(salary);

        salaryHistories.add(
                new SalaryHistory(worker, status, LocalDate.now())
        );

        return true;
    }

    /**
     * Lấy danh sách lịch sử điều chỉnh lương và sắp xếp theo mã nhân viên.
     *
     * Tạo một danh sách bản sao trước khi sắp xếp để không làm thay đổi thứ tự
     * của danh sách lịch sử ban đầu.
     *
     * @return danh sách lịch sử điều chỉnh lương đã được sắp xếp theo mã nhân
     * viên
     */
    public List<SalaryHistory> getInfomationSalary() {
        List<SalaryHistory> result = new ArrayList<>(salaryHistories);

        result.sort(
                Comparator.comparing(
                        SalaryHistory::getCode,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        return result;
    }

    /**
     * Kiểm tra mã nhân viên đã tồn tại trong danh sách hay chưa.
     *
     * @param code mã nhân viên cần kiểm tra
     * @return true nếu mã nhân viên đã tồn tại, ngược lại trả về false
     */
    public boolean checkDuplicateCode(String code) {
        return checkWorkerExists(code);
    }

    /**
     * Kiểm tra nhân viên có tồn tại trong danh sách hay không.
     *
     * @param code mã nhân viên cần tìm kiếm
     * @return true nếu tìm thấy nhân viên, ngược lại trả về false
     */
    public boolean checkWorkerExists(String code) {
        return findWorkerByCode(code) != null;
    }

    /**
     * Tìm kiếm nhân viên dựa trên mã nhân viên.
     *
     * Không phân biệt chữ hoa và chữ thường khi so sánh mã. Ví dụ: W001 và w001
     * được xem là cùng một mã nhân viên.
     *
     * @param code mã nhân viên cần tìm kiếm
     * @return đối tượng Worker nếu tìm thấy, ngược lại trả về null
     */
    private Worker findWorkerByCode(String code) {
        if (isBlank(code)) {
            return null;
        }

        // Duyệt qua danh sách để tìm nhân viên có mã trùng khớp.
        for (Worker worker : workers) {
            if (worker.getCode().equalsIgnoreCase(code)) {
                return worker;
            }
        }

        return null;
    }

    /**
     * Kiểm tra một chuỗi có giá trị null, rỗng hoặc chỉ chứa khoảng trắng hay
     * không.
     *
     * @param value chuỗi cần kiểm tra
     * @return true nếu chuỗi không có nội dung hợp lệ, ngược lại trả về false
     */
    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
