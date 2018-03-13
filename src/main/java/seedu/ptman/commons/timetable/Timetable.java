package seedu.ptman.commons.timetable;

import java.time.LocalDateTime;
import java.util.ArrayList;

import seedu.ptman.commons.exceptions.InvalidTimeException;
import seedu.ptman.commons.exceptions.TimetableOutOfBoundsException;

/**
 * Stores the data required to display the schedule
 */
public class Timetable {
    private static final int NUM_DAYS_IN_WEEK = 7;
    private static final int INDEX_SHIFT = 1;
    private ArrayList<TimetableRow> timetableRows;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Timetable(LocalDateTime startDateTime, LocalDateTime endDateTime) throws InvalidTimeException {
        if (startDateTime.isAfter(endDateTime)) {
            throw new InvalidTimeException();
        }
        this.timetableRows = new ArrayList<>();
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        initTimetable();
    }

    private void initTimetable() {
        for (int i = 0; i < NUM_DAYS_IN_WEEK; i++) {
            timetableRows.add(new TimetableRow(startDateTime, endDateTime));
        }
    }

    public TimetableCell getCellFromTime(LocalDateTime cellDateTime) throws TimetableOutOfBoundsException {
        if (cellDateTime.isAfter(endDateTime) || cellDateTime.isBefore(startDateTime)) {
            throw new TimetableOutOfBoundsException();
        }
        return getRowFromTime(cellDateTime).getCellFromTime(cellDateTime);
    }

    private TimetableRow getRowFromTime(LocalDateTime cellTime) {
        return timetableRows.get(cellTime.getDayOfWeek().getValue() - INDEX_SHIFT);
    }
}
