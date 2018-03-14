package seedu.ptman.model.outlet;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import seedu.ptman.model.employee.Employee;
import seedu.ptman.model.employee.exceptions.DuplicateEmployeeException;
import seedu.ptman.model.employee.exceptions.EmployeeNotFoundException;

/**
 * Represents a week
 */
public class Timetable {
    private static final int INDEX_OFFSET = 1;
    private static final int NUM_DAYS_IN_WEEK = 7;
    private ArrayList<TimetableDay> dayList = new ArrayList<>();
    private LocalDate mondayDate;

    public Timetable(LocalDate date) {
        requireNonNull(date);
        this.mondayDate = findStartOfWeekDate(date);
        for (int i = 0; i < NUM_DAYS_IN_WEEK; i++) {
            dayList.add(new TimetableDay(this.mondayDate.plusDays(i), new ArrayList<>()));
        }
    }

    public LocalDate getMondayDate() {
        return mondayDate;
    }

    public Shift getShift(DayOfWeek dayOfWeek, int index) {
        return getTimetableDayFromDayOfWeek(dayOfWeek).getShift(index);
    }

    public boolean containsShift(Shift shift) {
        return getTimetableDayFromDayOfWeek(shift.getDayOfWeek()).containsShift(shift);
    }

    public void removeShift(Shift shift) {
        getTimetableDayFromDayOfWeek(shift.getDayOfWeek()).removeShift(shift);
    }

    public boolean shiftContains(DayOfWeek dayOfWeek, int index, Employee employee) {
        return getShift(dayOfWeek, index).contains(employee);
    }

    public void addEmployeeToShift(DayOfWeek dayOfWeek, int index, Employee employee)
            throws DuplicateEmployeeException {
        getTimetableDayFromDayOfWeek(dayOfWeek).getShifts().get(index).addEmployee(employee);
    }

    public void removeEmployeeFromShift(DayOfWeek dayOfWeek, int index, Employee employee) throws EmployeeNotFoundException {
        getTimetableDayFromDayOfWeek(dayOfWeek).getShifts().get(index).removeEmployee(employee);
    }

    public int getNumShifts(DayOfWeek dayOfWeek) {
        return getTimetableDayFromDayOfWeek(dayOfWeek).getShifts().size();
    }

    public void addShift(Shift shift) {
        getTimetableDayFromDayOfWeek(shift.getDayOfWeek()).getShifts().add(shift);
    }

    private TimetableDay getTimetableDayFromDayOfWeek(DayOfWeek dayOfWeek) {
        return dayList.get(dayOfWeek.getValue() - INDEX_OFFSET);
    }

    private int getWeekFromDate(LocalDate date) {
        TemporalField woy = WeekFields.of(Locale.FRANCE).weekOfWeekBasedYear();
        return date.get(woy);
    }

    /**
     * Returns the date of the start of the week
     * @param date
     * @return
     */
    private LocalDate findStartOfWeekDate(LocalDate date) {
        int week = getWeekFromDate(date);
        WeekFields weekFields = WeekFields.of(Locale.FRANCE);
        return LocalDate.now()
                .withYear(date.getYear())
                .with(weekFields.weekOfYear(), week)
                .with(weekFields.dayOfWeek(), 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Timetable timetable = (Timetable) o;
        return Objects.equals(dayList, timetable.dayList)
                && Objects.equals(mondayDate, timetable.mondayDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayList, mondayDate);
    }
}
