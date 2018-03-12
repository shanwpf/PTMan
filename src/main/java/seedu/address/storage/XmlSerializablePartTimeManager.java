package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.PartTimeManager;
import seedu.address.model.ReadOnlyPartTimeManager;

/**
 * An Immutable PartTimeManager that is serializable to XML format
 */
@XmlRootElement(name = "addressbook")
public class XmlSerializablePartTimeManager {

    @XmlElement
    private List<XmlAdaptedPerson> persons;
    @XmlElement
    private List<XmlAdaptedTag> tags;

    /**
     * Creates an empty XmlSerializablePartTimeManager.
     * This empty constructor is required for marshalling.
     */
    public XmlSerializablePartTimeManager() {
        persons = new ArrayList<>();
        tags = new ArrayList<>();
    }

    /**
     * Conversion
     */
    public XmlSerializablePartTimeManager(ReadOnlyPartTimeManager src) {
        this();
        persons.addAll(src.getPersonList().stream().map(XmlAdaptedPerson::new).collect(Collectors.toList()));
        tags.addAll(src.getTagList().stream().map(XmlAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts this addressbook into the model's {@code PartTimeManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated or duplicates in the
     * {@code XmlAdaptedPerson} or {@code XmlAdaptedTag}.
     */
    public PartTimeManager toModelType() throws IllegalValueException {
        PartTimeManager partTimeManager = new PartTimeManager();
        for (XmlAdaptedTag t : tags) {
            partTimeManager.addTag(t.toModelType());
        }
        for (XmlAdaptedPerson p : persons) {
            partTimeManager.addPerson(p.toModelType());
        }
        return partTimeManager;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlSerializablePartTimeManager)) {
            return false;
        }

        XmlSerializablePartTimeManager otherAb = (XmlSerializablePartTimeManager) other;
        return persons.equals(otherAb.persons) && tags.equals(otherAb.tags);
    }
}
