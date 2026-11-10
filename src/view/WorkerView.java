/*
 * WorkerView.java
 *
 * Phiên bản: 1.0
 *
 * Ngày: 21/10/2026
 *
 * Dự án LAB211 - Quản lý thông tin nhân viên.
 * Class này hiển thị menu, thông báo và bảng dữ liệu trên màn hình.
 */

package view;

import java.time.format.DateTimeFormatter;
import java.util.List;

import model.SalaryHistory;
import model.Worker;
import util.Validation;

/**
 * Phụ trách phần giao tiếp với người dùng trong chương trình.
 *
 * <p>Class này hiển thị menu, các lời nhắc nhập dữ liệu, thông báo và bảng
 * lịch sử lương. Class không trực tiếp xử lý nghiệp vụ hoặc thay đổi dữ liệu;
 * dữ liệu nhập vào được chuyển cho Controller xử lý.
 */
public class WorkerView {

    /**
     * Định dạng hiển thị một dòng trong bảng lịch sử lương.
     * Mỗi định dạng %-Ns căn nội dung về bên trái trong cột có độ rộng N ký tự.
     */
    private static final String ROW_FORMAT =
            "%-10s %-22s %-5s %-14s %-8s %-12s";

    /** Đường kẻ phân cách phần tiêu đề và dữ liệu trong bảng. */
    private static final String SEPARATOR =
            "--------------------------------------------------------------------------";

    /** Định dạng ngày tháng hiển thị trong bảng, ví dụ: 21/10/2026. */
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /** Đối tượng hỗ trợ đọc và kiểm tra dữ liệu người dùng nhập. */
    private final Validation validation = new Validation();

    /**
     * Hiển thị menu chính gồm năm chức năng của chương trình.
     */
    public void showMenu() {
        showMessage("\n======== Worker Management =========");
        showMessage("1. Add Worker");
        showMessage("2. Up salary");
        showMessage("3. Down salary");
        showMessage("4. Display Information salary");
        showMessage("5. Exit");
        showMessage("====================================");
    }

    /**
     * Yêu cầu người dùng chọn một chức năng trong menu.
     *
     * @param min giá trị lựa chọn nhỏ nhất được chấp nhận
     * @param max giá trị lựa chọn lớn nhất được chấp nhận
     * @return số thứ tự chức năng mà người dùng đã chọn
     */
    public int inputChoice(int min, int max) {
        showMessage("Choose a function (" + min + "-" + max + "):");
        return validation.inputInt(min, max);
    }

    /**
     * Yêu cầu người dùng nhập mã nhân viên.
     *
     * @return mã nhân viên người dùng đã nhập, không được để trống
     */
    public String inputWorkerCode() {
        showMessage("Enter Code:");
        return validation.inputString();
    }

    /**
     * Yêu cầu người dùng nhập thông tin của nhân viên mới và tạo đối tượng Worker.
     *
     * @param code mã nhân viên mới, đã được Controller kiểm tra trước đó
     * @return đối tượng Worker chứa đầy đủ thông tin vừa nhập
     */
    public Worker inputWorkerDetails(String code) {
        showMessage("Enter Name:");
        String name = validation.inputString();

        // Tuổi nhân viên phải nằm trong khoảng từ MIN_AGE đến MAX_AGE (18 đến 50).
        showMessage("Enter Age (" + Worker.MIN_AGE + "-"
                + Worker.MAX_AGE + "):");
        int age = validation.inputInt(Worker.MIN_AGE, Worker.MAX_AGE);

        showMessage("Enter Salary:");
        double salary = validation.inputPositiveDouble();

        showMessage("Enter work location:");
        String location = validation.inputString();

        // Kết hợp các thông tin đã nhập để tạo đối tượng nhân viên mới.
        return new Worker(code, name, age, salary, location);
    }

    /**
     * Yêu cầu người dùng nhập số tiền cần tăng hoặc giảm lương.
     *
     * @return số tiền người dùng nhập, luôn lớn hơn 0
     */
    public double inputSalaryAmount() {
        showMessage("Enter Salary:");
        return validation.inputPositiveDouble();
    }

    /**
     * Hiển thị danh sách lịch sử điều chỉnh lương dưới dạng bảng.
     * Nếu danh sách chưa có dữ liệu, hiển thị thông báo tương ứng.
     *
     * @param histories danh sách các bản ghi lịch sử lương cần hiển thị
     */
    public void displaySalaryHistory(List<SalaryHistory> histories) {
        // Nếu chưa có lịch sử điều chỉnh lương thì thông báo và kết thúc hàm.
        if (histories.isEmpty()) {
            showMessage("Salary history is empty.");
            return;
        }

        // Hiển thị tiêu đề và tên các cột trong bảng lịch sử lương.
        showMessage("\n-------------------- Display Information Salary "
                + "--------------------");
        showMessage(String.format(ROW_FORMAT, "Code", "Name", "Age",
                "Salary", "Status", "Date"));
        showMessage(SEPARATOR);

        // Duyệt qua từng bản ghi và hiển thị thông tin của lần điều chỉnh lương.
        // Mức lương được định dạng với hai chữ số thập phân, ví dụ: 1500.00.
        for (SalaryHistory history : histories) {
            showMessage(String.format(ROW_FORMAT,
                    history.getCode(), history.getName(), history.getAge(),
                    String.format("%.2f", history.getSalary()),
                    history.getStatus(), history.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        }
    }

    /**
     * Hiển thị một thông báo hoặc nội dung lên màn hình Console.
     *
     * @param message nội dung cần hiển thị
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
