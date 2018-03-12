package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.ReadOnlyPartTimeManager;

/** Indicates the PartTimeManager in the model has changed*/
public class PartTimeManagerChangedEvent extends BaseEvent {

    public final ReadOnlyPartTimeManager data;

    public PartTimeManagerChangedEvent(ReadOnlyPartTimeManager data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "number of employees " + data.getEmployeeList().size() + ", number of tags " + data.getTagList().size();
    }
}
