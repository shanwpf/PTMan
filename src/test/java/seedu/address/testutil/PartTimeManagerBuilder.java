package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.PartTimeManager;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code PartTimeManager ab = new PartTimeManagerBuilder().withPerson("John", "Doe").withTag("Friend").build();}
 */
public class PartTimeManagerBuilder {

    private PartTimeManager partTimeManager;

    public PartTimeManagerBuilder() {
        partTimeManager = new PartTimeManager();
    }

    public PartTimeManagerBuilder(PartTimeManager partTimeManager) {
        this.partTimeManager = partTimeManager;
    }

    /**
     * Adds a new {@code Person} to the {@code PartTimeManager} that we are building.
     */
    public PartTimeManagerBuilder withPerson(Person person) {
        try {
            partTimeManager.addPerson(person);
        } catch (DuplicatePersonException dpe) {
            throw new IllegalArgumentException("person is expected to be unique.");
        }
        return this;
    }

    /**
     * Parses {@code tagName} into a {@code Tag} and adds it to the {@code PartTimeManager} that we are building.
     */
    public PartTimeManagerBuilder withTag(String tagName) {
        try {
            partTimeManager.addTag(new Tag(tagName));
        } catch (IllegalValueException ive) {
            throw new IllegalArgumentException("tagName is expected to be valid.");
        }
        return this;
    }

    public PartTimeManager build() {
        return partTimeManager;
    }
}
