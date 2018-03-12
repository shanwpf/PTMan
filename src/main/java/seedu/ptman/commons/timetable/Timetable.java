package seedu.ptman.commons.timetable;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Stores the data required to display the schedule
 */
public class Timetable {
    private static final int NUM_DAYS_IN_WEEK = 7;
    private ArrayList<TimetableRow> timetable;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Timetable(LocalDateTime startTime, LocalDateTime endTime) {
        this.timetable = new ArrayList<>();
        this.startTime = startTime;
        this.endTime = endTime;
        initTimetable();
    }

    private void initTimetable() {
        for (int i = 0; i < NUM_DAYS_IN_WEEK; i++) {
            timetable.add(new TimetableRow(startTime, endTime));
        }
    }
}
