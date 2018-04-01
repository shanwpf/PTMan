package seedu.ptman.model.outlet;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

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
                new Timetable((LocalDate) null)
        );
        Assert.assertThrows(NullPointerException.class, () ->
                new Timetable((Timetable) null)
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
        assertEquals(DATE_MON, timetableMon.getMondayDate());
        assertEquals(DATE_MON, timetableTue.getMondayDate());
        assertEquals(DATE_MON, timetableWed.getMondayDate());
        assertEquals(DATE_MON, timetableThu.getMondayDate());
        assertEquals(DATE_MON, timetableFri.getMondayDate());
        assertEquals(DATE_MON, timetableSat.getMondayDate());
        assertEquals(DATE_MON, timetableSun.getMondayDate());
    }
}
