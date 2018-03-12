package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.ui.EmployeeCard;

/**
 * Represents a selection change in the Employee List Panel
 */
public class EmployeePanelSelectionChangedEvent extends BaseEvent {


    private final EmployeeCard newSelection;

    public EmployeePanelSelectionChangedEvent(EmployeeCard newSelection) {
        this.newSelection = newSelection;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public EmployeeCard getNewSelection() {
        return newSelection;
    }
}
