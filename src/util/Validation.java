/*
 * Validation.java
 *
 * Phiên bản: 1.0
 *
 * Ngày: 21/10/2026
 *
 * Dự án LAB211 - Quản lý thông tin nhân viên.
 * Class này đọc và kiểm tra dữ liệu người dùng nhập từ bàn phím.
 */

package util;

import java.util.Scanner;

/**
 * Hỗ trợ đọc dữ liệu từ bàn phím và kiểm tra tính hợp lệ của dữ liệu đầu vào.
 *
 * <p>Nếu người dùng nhập dữ liệu không đúng yêu cầu, các phương thức sẽ
 * hiển thị thông báo lỗi và yêu cầu nhập lại cho đến khi nhận được dữ liệu
 * hợp lệ.
 */
public class Validation {

    /** Đối tượng Scanner dùng để đọc dữ liệu người dùng nhập từ bàn phím. */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Đọc một dòng dữ liệu từ bàn phím và loại bỏ khoảng trắng ở đầu, cuối chuỗi.
     *
     * @return nội dung người dùng nhập sau khi đã loại bỏ khoảng trắng thừa
     * @throws IllegalStateException nếu không còn dữ liệu để đọc, ví dụ luồng
     *         nhập đã bị đóng
     */
    private String readLine() {
        // Kiểm tra còn dữ liệu đầu vào hay không trước khi đọc.
        if (!scanner.hasNextLine()) {
            throw new IllegalStateException("Input stream is closed.");
        }
        return scanner.nextLine().trim();
    }

    /**
     * Đọc một chuỗi không được để trống.
     * Nếu người dùng chỉ nhấn Enter hoặc nhập toàn khoảng trắng, yêu cầu nhập lại.
     *
     * @return chuỗi hợp lệ do người dùng nhập
     */
    public String inputString() {
        while (true) {
            // Tiếp tục yêu cầu nhập cho đến khi chuỗi có nội dung.
            String value = readLine();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Input cannot be empty. Please re-enter:");
        }
    }

    /**
     * Đọc một số nguyên nằm trong khoảng từ min đến max.
     * Yêu cầu nhập lại nếu dữ liệu không phải số nguyên hoặc nằm ngoài khoảng cho phép.
     *
     * @param min giá trị nhỏ nhất được chấp nhận
     * @param max giá trị lớn nhất được chấp nhận
     * @return số nguyên hợp lệ do người dùng nhập
     */
    public int inputInt(int min, int max) {
        while (true) {
            try {
                // Chuyển chuỗi vừa nhập thành số nguyên; nếu sai định dạng sẽ phát sinh NumberFormatException.
                int value = Integer.parseInt(readLine());

                // Chỉ chấp nhận giá trị nằm trong giới hạn min và max.
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Value must be from " + min + " to "
                        + max + ". Please re-enter:");
            } catch (NumberFormatException e) {
                // Dữ liệu không phải số nguyên, ví dụ: abc hoặc 1.5.
                System.out.println("Please enter an integer:");
            }
        }
    }

    /**
     * Đọc một số thực lớn hơn 0.
     * Giá trị có thể chứa phần thập phân, ví dụ: 1500.5.
     * Yêu cầu nhập lại nếu dữ liệu sai định dạng hoặc không thỏa điều kiện.
     *
     * @return số thực hợp lệ lớn hơn 0 do người dùng nhập
     */
    public double inputPositiveDouble() {
        while (true) {
            try {
                double value = Double.parseDouble(readLine());

                // Chỉ chấp nhận số hữu hạn và lớn hơn 0.
                // isFinite() loại bỏ các giá trị đặc biệt như Infinity và NaN.
                if (Double.isFinite(value) && value > 0) {
                    return value;
                }
                System.out.println(
                        "Value must be greater than 0. Please re-enter:");
            } catch (NumberFormatException e) {
                // Dữ liệu nhập vào không thể chuyển thành số, ví dụ: abc.
                System.out.println("Please enter a number:");
            }
        }
    }
}
