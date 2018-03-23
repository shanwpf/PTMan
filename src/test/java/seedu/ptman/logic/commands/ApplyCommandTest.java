package seedu.ptman.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.ptman.logic.commands.CommandTestUtil.DEFAULT_PASSWORD;
import static seedu.ptman.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.ptman.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.ptman.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.ptman.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.ptman.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.ptman.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.ptman.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ptman.logic.commands.CommandTestUtil.prepareRedoCommand;
import static seedu.ptman.logic.commands.CommandTestUtil.prepareUndoCommand;
import static seedu.ptman.logic.commands.CommandTestUtil.showEmployeeAtIndex;
import static seedu.ptman.testutil.TypicalEmployees.ALICE;
import static seedu.ptman.testutil.TypicalEmployees.getTypicalPartTimeManager;
import static seedu.ptman.testutil.TypicalIndexes.INDEX_FIRST_EMPLOYEE;
import static seedu.ptman.testutil.TypicalIndexes.INDEX_FIRST_SHIFT;
import static seedu.ptman.testutil.TypicalIndexes.INDEX_SECOND_EMPLOYEE;
import static seedu.ptman.testutil.TypicalShifts.MONDAY_AM;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.ptman.commons.core.Messages;
import seedu.ptman.commons.core.index.Index;
import seedu.ptman.logic.CommandHistory;
import seedu.ptman.logic.UndoRedoStack;
import seedu.ptman.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.ptman.model.Model;
import seedu.ptman.model.ModelManager;
import seedu.ptman.model.PartTimeManager;
import seedu.ptman.model.Password;
import seedu.ptman.model.UserPrefs;
import seedu.ptman.model.employee.Employee;
import seedu.ptman.model.employee.exceptions.DuplicateEmployeeException;
import seedu.ptman.model.outlet.Shift;
import seedu.ptman.testutil.Assert;
import seedu.ptman.testutil.EditEmployeeDescriptorBuilder;
import seedu.ptman.testutil.EmployeeBuilder;
import seedu.ptman.testutil.ShiftBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class ApplyCommandTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager(getTypicalPartTimeManager(), new UserPrefs());

    @Before
    public void setMode_adminMode() {
        model.setTrueAdminMode(new Password());
    }

    @Test
    public void execute_employeeNotInShift_success() throws Exception {
        Employee employee = new EmployeeBuilder().build();
        ApplyCommand applyCommand = prepareCommand(INDEX_FIRST_EMPLOYEE, INDEX_FIRST_SHIFT);

        String expectedMessage = String.format(ApplyCommand.MESSAGE_APPLY_SHIFT_SUCCESS, employee.getName(), INDEX_FIRST_SHIFT.getOneBased());

        Model expectedModel = new ModelManager(new PartTimeManager(model.getPartTimeManager()), new UserPrefs());
        expectedModel.setTrueAdminMode(new Password());

        Shift editedShift = new Shift(MONDAY_AM);
        editedShift.addEmployee(ALICE);
        expectedModel.updateShift(model.getFilteredShiftList().get(0), editedShift);
        assertCommandSuccess(applyCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Returns an {@code EditCommand} with parameters {@code index} and {@code descriptor}
     */
    private ApplyCommand prepareCommand(Index employeeIndex, Index shiftIndex) {
        ApplyCommand applyCommand = new ApplyCommand(employeeIndex, shiftIndex, new Password());
        applyCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return applyCommand;
    }
}
