package seedu.ptman.commons.timetable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Stores the timetable cells in a row
 */
public class TimetableRow {
    private ArrayList<TimetableCell> rowCellList;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Creates a timetable row
     * @param startTime
     */
    public TimetableRow(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        rowCellList = new ArrayList<>();
        initTimetableRow();
    }

    /**
     * Populates the rowCellList with TimetableCells
     */
    private void initTimetableRow() {
        int numCells = getNumberOfCells();
        for (int i = 0; i < numCells; i++) {
            rowCellList.add(new TimetableCell(startTime.plusHours(i), 3, i));
        }
    }

    private int getNumberOfCells() {
        return (int) ChronoUnit.HOURS.between(startTime, endTime);
    }

}
