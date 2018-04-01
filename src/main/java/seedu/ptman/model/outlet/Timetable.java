package seedu.ptman.model.outlet;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * Represents a week where shifts can be added/removed on different days and times.
 */
public class Timetable {
    private LocalDate mondayDate;

    /**
     * Creates a timetable starting from the Monday of the week of the date provided
     * @param date
     */
    public Timetable(LocalDate date) {
        requireNonNull(date);
        this.mondayDate = findStartOfWeekDate(date);
    }

    public Timetable(Timetable timetable) {
        this.mondayDate = timetable.getMondayDate();
    }

    /**
     * Gets the date for the Monday of this timetable week
     * @return LocalDate of Monday
     */
    public LocalDate getMondayDate() {
        return mondayDate;
    }

    public static int getWeekFromDate(LocalDate date) {
        TemporalField woy = WeekFields.of(Locale.FRANCE).weekOfWeekBasedYear();
        return date.get(woy);
    }

    /**
     * Returns the date of the start of the week
     * @param date
     * @return
     */
    public static LocalDate findStartOfWeekDate(LocalDate date) {
        int week = getWeekFromDate(date);
        // We use Locale.FRANCE because it sets the first day of the week
        // to be Monday.
        WeekFields weekFields = WeekFields.of(Locale.FRANCE);
        return LocalDate.now()
                .withYear(date.getYear())
                .with(weekFields.weekOfYear(), week)
                .with(weekFields.dayOfWeek(), 1);
    }

}
