package seedu.ptman.commons.util;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

//@@author shanwpf
/**
 * Utility methods for handling dates
 */
public class DateUtil {
    public static final Locale DEFAULT_LOCALE = Locale.FRANCE;

    /**
     * Returns the week number for {@code date} from the start of the year
     */
    public static int getWeekFromDate(LocalDate date) {
        requireNonNull(date);
        TemporalField woy = WeekFields.of(DEFAULT_LOCALE).weekOfWeekBasedYear();
        return date.get(woy);
    }
}
