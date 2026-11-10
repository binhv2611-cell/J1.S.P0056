/*
 * Worker.java
 *
 * Phiên bản: 1.0
 *
 * Ngày: 21/10/2026
 *
 * Dự án LAB211 - Quản lý thông tin nhân viên.
 * Class này lưu trữ thông tin của một nhân viên.
 */

package model;

/**
 * Đại diện cho một nhân viên trong chương trình.
 *
 * <p>Mỗi nhân viên có các thông tin: mã nhân viên, họ tên, tuổi, mức lương
 * và nơi làm việc. Mã nhân viên được dùng để phân biệt các nhân viên.
 * Độ tuổi hợp lệ nằm trong khoảng từ {@link #MIN_AGE} đến {@link #MAX_AGE}.
 */
public class Worker {

    /** Độ tuổi nhỏ nhất được phép của nhân viên. */
    public static final int MIN_AGE = 18;

    /** Độ tuổi lớn nhất được phép của nhân viên. */
    public static final int MAX_AGE = 50;

    /** Mã định danh của nhân viên. */
    private String code;

    /** Họ và tên của nhân viên. */
    private String name;

    /** Tuổi của nhân viên, tính theo năm. */
    private int age;

    /** Mức lương hiện tại của nhân viên. */
    private double salary;

    /** Địa điểm làm việc của nhân viên. */
    private String workLocation;

    /**
     * Khởi tạo một đối tượng nhân viên chưa có thông tin.
     * Có thể sử dụng các phương thức setter để gán dữ liệu sau khi khởi tạo.
     */
    public Worker() {
    }

    /**
     * Khởi tạo một nhân viên với đầy đủ thông tin.
     *
     * @param code mã nhân viên
     * @param name họ và tên nhân viên
     * @param age tuổi nhân viên
     * @param salary mức lương nhân viên
     * @param workLocation địa điểm làm việc của nhân viên
     */
    public Worker(String code, String name, int age, double salary,
            String workLocation) {
        this.code = code;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.workLocation = workLocation;
    }

    /**
     * Lấy mã nhân viên.
     *
     * @return mã nhân viên hiện tại
     */
    public String getCode() {
        return code;
    }

    /**
     * Cập nhật mã nhân viên.
     *
     * @param code mã nhân viên mới
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Lấy họ và tên nhân viên.
     *
     * @return họ và tên hiện tại của nhân viên
     */
    public String getName() {
        return name;
    }

    /**
     * Cập nhật họ và tên nhân viên.
     *
     * @param name họ và tên mới của nhân viên
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Lấy tuổi nhân viên.
     *
     * @return tuổi hiện tại của nhân viên
     */
    public int getAge() {
        return age;
    }

    /**
     * Cập nhật tuổi nhân viên.
     *
     * @param age tuổi mới của nhân viên
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Lấy mức lương hiện tại của nhân viên.
     *
     * @return mức lương hiện tại
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Cập nhật mức lương của nhân viên.
     *
     * @param salary mức lương mới
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Lấy địa điểm làm việc của nhân viên.
     *
     * @return địa điểm làm việc hiện tại
     */
    public String getWorkLocation() {
        return workLocation;
    }

    /**
     * Cập nhật địa điểm làm việc của nhân viên.
     *
     * @param workLocation địa điểm làm việc mới
     */
    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }
}
