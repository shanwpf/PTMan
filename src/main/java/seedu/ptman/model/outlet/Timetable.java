package seedu.ptman.model.outlet;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Represents a week
 */
public class Timetable {
    private static final int INDEX_OFFSET = 1;
    private static final int NUM_DAYS_IN_WEEK = 7;
    private ArrayList<TimetableDay> dayList = new ArrayList<>();
    private LocalDate startOfWeekDate;

    public Timetable(LocalDate date) {
        requireNonNull(date);
        this.startOfWeekDate = findStartOfWeekDate(date);
        for (int i = 0; i < NUM_DAYS_IN_WEEK; i++) {
            dayList.add(new TimetableDay(this.startOfWeekDate.plusDays(i), new ArrayList<>()));
        }
    }

    public LocalDate getStartOfWeekDate() {
        return startOfWeekDate;
    }

    public Shift getShift(DayOfWeek dayOfWeek, int index) {
        return getTimetableDayFromDayOfWeek(dayOfWeek).getShifts().get(index);
    }

    public int getNumShifts(DayOfWeek dayOfWeek) {
        return getTimetableDayFromDayOfWeek(dayOfWeek).getShifts().size();
    }

    public void addShift(DayOfWeek dayOfWeek, Shift shift) {
        getTimetableDayFromDayOfWeek(dayOfWeek).getShifts().add(shift);
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
}
