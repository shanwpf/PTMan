package seedu.ptman.commons.timetable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Stores the timetable cells in a row
 */
public class TimetableRow {
    private static final int PLACEHOLDER_AVAIL_SLOTS = 3;
    private ArrayList<TimetableCell> rowCellList;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    /**
     * Creates a timetable row
     * @param startDateTime
     */
    public TimetableRow(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        rowCellList = new ArrayList<>();
        initTimetableRow();
    }

    /**
     * Populates the rowCellList with TimetableCells
     */
    private void initTimetableRow() {
        int numCells = getNumberOfCells();
        for (int i = 0; i < numCells; i++) {
            rowCellList.add(new TimetableCell(startDateTime.plusHours(i), PLACEHOLDER_AVAIL_SLOTS));
        }
    }

    protected TimetableCell getCellFromTime(LocalDateTime cellTime) {
        return rowCellList.get(getCellIndexFromTime(cellTime));
    }

    private int getCellIndexFromTime(LocalDateTime cellTime) {
        return (int) ChronoUnit.HOURS.between(startDateTime, cellTime);
    }

    protected int getNumberOfCells() {
        return (int) ChronoUnit.HOURS.between(startDateTime, endDateTime);
    }

}
