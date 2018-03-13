package seedu.ptman.model.outlet;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a day on the timetable
 */
public class TimetableDay {
    private LocalDate date;
    private ArrayList<Shift> shiftList;

    public TimetableDay(LocalDate date, ArrayList<Shift> shiftList) {
        this.date = date;
        this.shiftList = shiftList;
    }
    // TODO: Implement better accessors
    public ArrayList<Shift> getShifts() {
        return shiftList;
    }
}
