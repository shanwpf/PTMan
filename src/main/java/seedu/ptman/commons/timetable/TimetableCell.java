package seedu.ptman.commons.timetable;

import java.time.LocalDateTime;
import java.util.ArrayList;

import seedu.ptman.model.employee.Employee;

/**
 * Stores data in a timetable cell
 */
public class TimetableCell {
    private int availableSlots;
    private ArrayList<Employee> employeeList;
    private LocalDateTime time;
    private int rowIndex;
    private int colIndex;

    public TimetableCell(LocalDateTime time, int availableSlots, int rowIndex) {
        this.employeeList = new ArrayList<>();
        this.time = time;
        this.rowIndex = rowIndex;
        this.availableSlots = availableSlots;
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

}
