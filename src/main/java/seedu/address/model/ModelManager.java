package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.PartTimeManagerChangedEvent;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Represents the in-memory model of the address book data.
 * All changes to any model should be synchronized.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final PartTimeManager partTimeManager;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given partTimeManager and userPrefs.
     */
    public ModelManager(ReadOnlyPartTimeManager partTimeManager, UserPrefs userPrefs) {
        super();
        requireAllNonNull(partTimeManager, userPrefs);

        logger.fine("Initializing with address book: " + partTimeManager + " and user prefs " + userPrefs);

        this.partTimeManager = new PartTimeManager(partTimeManager);
        filteredPersons = new FilteredList<>(this.partTimeManager.getPersonList());
    }

    public ModelManager() {
        this(new PartTimeManager(), new UserPrefs());
    }

    @Override
    public void resetData(ReadOnlyPartTimeManager newData) {
        partTimeManager.resetData(newData);
        indicatePartTimeManagerChanged();
    }

    @Override
    public ReadOnlyPartTimeManager getPartTimeManager() {
        return partTimeManager;
    }

    /** Raises an event to indicate the model has changed */
    private void indicatePartTimeManagerChanged() {
        raise(new PartTimeManagerChangedEvent(partTimeManager));
    }

    @Override
    public synchronized void deletePerson(Person target) throws PersonNotFoundException {
        partTimeManager.removePerson(target);
        indicatePartTimeManagerChanged();
    }

    @Override
    public synchronized void addPerson(Person person) throws DuplicatePersonException {
        partTimeManager.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        indicatePartTimeManagerChanged();
    }

    @Override
    public void updatePerson(Person target, Person editedPerson)
            throws DuplicatePersonException, PersonNotFoundException {
        requireAllNonNull(target, editedPerson);

        partTimeManager.updatePerson(target, editedPerson);
        indicatePartTimeManagerChanged();
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code partTimeManager}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return FXCollections.unmodifiableObservableList(filteredPersons);
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return partTimeManager.equals(other.partTimeManager)
                && filteredPersons.equals(other.filteredPersons);
    }

}
