/*
 * SalaryStatus.java
 *
 * Phiên bản: 1.0
 *
 * Ngày: 21/10/2026
 *
 * Dự án LAB211 - Quản lý thông tin nhân viên.
 * File này định nghĩa các loại điều chỉnh lương (UP và DOWN).
 */

package model;

/**
 * Kiểu liệt kê xác định loại điều chỉnh lương của nhân viên.
 *
 * <p>UP biểu thị thao tác tăng lương, DOWN biểu thị thao tác giảm lương.
 */
public enum SalaryStatus {

    /** Đại diện cho thao tác tăng lương. */
    UP,

    /** Đại diện cho thao tác giảm lương. */
    DOWN
}
