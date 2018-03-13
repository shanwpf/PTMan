package seedu.ptman.commons.timetable;

import static org.junit.Assert.assertEquals;
import static seedu.ptman.testutil.TypicalTimes.ELEVEN_MAR_TWELVE_AM;
import static seedu.ptman.testutil.TypicalTimes.TEN_MAR_EIGHT_AM;
import static seedu.ptman.testutil.TypicalTimes.TEN_MAR_ONE_PM;
import static seedu.ptman.testutil.TypicalTimes.TEN_MAR_TWELVE_AM;
import static seedu.ptman.testutil.TypicalTimes.TWELVE_APR_ELEVEN_PM;
import static seedu.ptman.testutil.TypicalTimes.TWELVE_MAR_ELEVEN_PM;

import org.junit.Test;

import seedu.ptman.commons.exceptions.InvalidTimeException;
import seedu.ptman.commons.exceptions.TimetableOutOfBoundsException;
import seedu.ptman.testutil.Assert;

public class TimetableTest {
    @Test
    public void constructor_startTimeAfterEndTime_throwsInvalidTimeException() {
        Assert.assertThrows(InvalidTimeException.class, () ->
                new Timetable(TEN_MAR_EIGHT_AM, TEN_MAR_TWELVE_AM));
        Assert.assertThrows(InvalidTimeException.class, () ->
                new Timetable(TWELVE_APR_ELEVEN_PM, TEN_MAR_TWELVE_AM));
    }

    @Test
    public void getCellFromTimeTest() throws InvalidTimeException, TimetableOutOfBoundsException {
        Timetable timetable = new Timetable(TEN_MAR_EIGHT_AM, ELEVEN_MAR_TWELVE_AM);
        TimetableCell expectedCell = new TimetableCell(TEN_MAR_ONE_PM, 3);
        assertEquals(expectedCell, timetable.getCellFromTime(TEN_MAR_ONE_PM));
    }

    @Test
    public void getCellFromTime_timeOutOfBounds_throwsTimetableOutOfBoundsException() throws InvalidTimeException {
        Timetable timetable = new Timetable(TEN_MAR_EIGHT_AM, ELEVEN_MAR_TWELVE_AM);
        Assert.assertThrows(TimetableOutOfBoundsException.class, () ->
                timetable.getCellFromTime(TWELVE_MAR_ELEVEN_PM));
    }

}
