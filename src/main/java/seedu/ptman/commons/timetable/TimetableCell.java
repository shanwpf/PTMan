package seedu.ptman.commons.timetable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import seedu.ptman.model.employee.Employee;

/**
 * Stores data in a timetable cell
 */
public class TimetableCell {
    private int availableSlots;
    private ArrayList<Employee> employeeList;
    private LocalDateTime dateTime;

    public TimetableCell(LocalDateTime dateTime, int availableSlots) {
        this.employeeList = new ArrayList<>();
        this.dateTime = dateTime;
        this.availableSlots = availableSlots;
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    protected int getAvailableSlots() {
        return availableSlots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimetableCell that = (TimetableCell) o;
        return availableSlots == that.availableSlots
                && Objects.equals(employeeList, that.employeeList)
                && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(availableSlots, employeeList, dateTime);
    }

}
