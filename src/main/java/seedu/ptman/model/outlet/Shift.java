package seedu.ptman.model.outlet;

import static java.util.Objects.requireNonNull;

import java.time.LocalTime;

import javafx.collections.ObservableList;
import seedu.ptman.model.employee.Employee;
import seedu.ptman.model.employee.UniqueEmployeeList;
import seedu.ptman.model.employee.exceptions.DuplicateEmployeeException;
import seedu.ptman.model.employee.exceptions.EmployeeNotFoundException;
import seedu.ptman.model.outlet.exceptions.IllegalTimeException;

/**
 * Represents a shift that employees can work in.
 */
public class Shift {
    private LocalTime startTime;
    private LocalTime endTime;
    private UniqueEmployeeList uniqueEmployeeList;

    public Shift(LocalTime startTime, LocalTime endTime) throws IllegalTimeException {
        requireNonNull(startTime);
        requireNonNull(endTime);
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new IllegalTimeException();
        }
        this.startTime = startTime;
        this.endTime = endTime;
        this.uniqueEmployeeList = new UniqueEmployeeList();
    }

    /**
     * Adds employees that are working in this shift.
     * @param employees
     * @throws DuplicateEmployeeException
     */
    public void addEmployees(Employee... employees) throws DuplicateEmployeeException {
        for (Employee employee : employees) {
            uniqueEmployeeList.add(employee);
        }
    }

    /**
     * Removes employees who are no longer working in this shift.
     * @param employees
     * @throws EmployeeNotFoundException
     */
    public void removeEmployees(Employee... employees) throws EmployeeNotFoundException {
        for (Employee employee : employees) {
            uniqueEmployeeList.remove(employee);
        }
    }

    public ObservableList<Employee> getEmployeeList() {
        return uniqueEmployeeList.asObservableList();
    }
}
