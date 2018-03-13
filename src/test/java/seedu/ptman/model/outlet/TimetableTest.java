package seedu.ptman.model.outlet;

import static org.junit.Assert.assertEquals;
import static seedu.ptman.model.outlet.ShiftTest.TIME_AM;
import static seedu.ptman.model.outlet.ShiftTest.TIME_PM;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.Test;

import seedu.ptman.model.outlet.exceptions.IllegalTimeException;
import seedu.ptman.testutil.Assert;

public class TimetableTest {
    private static final LocalDate DATE_MON = LocalDate.of(2018, 3, 12);
    private static final LocalDate DATE_TUE = LocalDate.of(2018, 3, 13);
    private static final LocalDate DATE_WED = LocalDate.of(2018, 3, 14);
    private static final LocalDate DATE_THU = LocalDate.of(2018, 3, 15);
    private static final LocalDate DATE_FRI = LocalDate.of(2018, 3, 16);
    private static final LocalDate DATE_SAT = LocalDate.of(2018, 3, 17);
    private static final LocalDate DATE_SUN = LocalDate.of(2018, 3, 18);

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () ->
                new Timetable(null)
        );
    }

    @Test
    public void getStartOfWeekDate_datesInWeek_returnsMondayDate() {
        Timetable timetableMon = new Timetable(DATE_MON);
        Timetable timetableTue = new Timetable(DATE_TUE);
        Timetable timetableWed = new Timetable(DATE_WED);
        Timetable timetableThu = new Timetable(DATE_THU);
        Timetable timetableFri = new Timetable(DATE_FRI);
        Timetable timetableSat = new Timetable(DATE_SAT);
        Timetable timetableSun = new Timetable(DATE_SUN);
        assertEquals(DATE_MON, timetableMon.getStartOfWeekDate());
        assertEquals(DATE_MON, timetableTue.getStartOfWeekDate());
        assertEquals(DATE_MON, timetableWed.getStartOfWeekDate());
        assertEquals(DATE_MON, timetableThu.getStartOfWeekDate());
        assertEquals(DATE_MON, timetableFri.getStartOfWeekDate());
        assertEquals(DATE_MON, timetableSat.getStartOfWeekDate());
        assertEquals(DATE_MON, timetableSun.getStartOfWeekDate());
    }

    @Test
    public void addShift_validShift_shiftAdded() throws IllegalTimeException {
        Timetable timetable = new Timetable(DATE_MON);
        Shift shift1 = new Shift(TIME_AM, TIME_PM);
        Shift shift2 = new Shift(TIME_AM.plusHours(1), TIME_PM);
        Shift shift3 = new Shift(TIME_AM.plusHours(5), TIME_PM.minusHours(1));
        timetable.addShift(DayOfWeek.MONDAY, shift1);
        timetable.addShift(DayOfWeek.MONDAY, shift2);
        timetable.addShift(DayOfWeek.SUNDAY, shift2);
        timetable.addShift(DayOfWeek.SUNDAY, shift3);
        assertEquals(shift1, timetable.getShift(DayOfWeek.MONDAY, 0));
        assertEquals(shift2, timetable.getShift(DayOfWeek.MONDAY, 1));
        assertEquals(shift2, timetable.getShift(DayOfWeek.SUNDAY, 0));
        assertEquals(shift3, timetable.getShift(DayOfWeek.SUNDAY, 1));
    }

}
