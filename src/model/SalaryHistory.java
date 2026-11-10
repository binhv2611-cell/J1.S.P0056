/*
 * SalaryHistory.java
 *
 * Phiên bản: 1.0
 *
 * Ngày: 21/10/2026
 *
 * Dự án LAB211 - Quản lý thông tin nhân viên.
 * Class này lưu lại thông tin của một lần điều chỉnh lương nhân viên.
 */

package model;

import java.time.LocalDate;

/**
 * Lưu trữ lịch sử của một lần thay đổi lương nhân viên.
 *
 * <p>Mỗi khi lương của nhân viên được tăng hoặc giảm, chương trình tạo một
 * đối tượng SalaryHistory mới. Đối tượng này sao chép thông tin nhân viên
 * tại thời điểm điều chỉnh, bao gồm mã, tên, tuổi, mức lương mới và nơi làm
 * việc; đồng thời lưu loại điều chỉnh (UP hoặc DOWN) và ngày thực hiện.
 * Những thay đổi lương về sau của nhân viên không làm thay đổi bản ghi này.
 */
public class SalaryHistory extends Worker {

    /** Loại điều chỉnh lương: UP (tăng) hoặc DOWN (giảm). */
    private final SalaryStatus status;

    /** Ngày thực hiện điều chỉnh lương. */
    private final LocalDate date;

    /**
     * Tạo một bản ghi lịch sử bằng cách sao chép thông tin hiện tại của nhân viên.
     *
     * @param worker nhân viên vừa được điều chỉnh lương
     * @param status loại điều chỉnh lương, gồm UP hoặc DOWN
     * @param date ngày thực hiện điều chỉnh
     */
    public SalaryHistory(Worker worker, SalaryStatus status, LocalDate date) {
        // Sao chép thông tin nhân viên tại thời điểm tạo bản ghi lịch sử.
        super(worker.getCode(), worker.getName(), worker.getAge(),
                worker.getSalary(), worker.getWorkLocation());
        this.status = status;
        this.date = date;
    }

    /**
     * Lấy loại điều chỉnh lương.
     *
     * @return UP nếu tăng lương hoặc DOWN nếu giảm lương
     */
    public SalaryStatus getStatus() {
        return status;
    }

    /**
     * Lấy ngày thực hiện điều chỉnh lương.
     *
     * @return ngày điều chỉnh lương
     */
    public LocalDate getDate() {
        return date;
    }
}
