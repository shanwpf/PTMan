package seedu.ptman.logic.commands;

import static seedu.ptman.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ptman.testutil.TypicalEmployees.getTypicalPartTimeManager;

import org.junit.Test;

import seedu.ptman.model.Model;
import seedu.ptman.model.ModelManager;
import seedu.ptman.model.PartTimeManager;
import seedu.ptman.model.Password;
import seedu.ptman.model.UserPrefs;
import seedu.ptman.model.outlet.OperatingHours;
import seedu.ptman.model.outlet.OutletName;

public class EditOutletCommandTest {

    private Model model = new ModelManager(getTypicalPartTimeManager(), new UserPrefs());

    @Test
    public void execute_allFieldsValid_success() {
        Password masterPassword = new Password();
        OutletName outletName = new OutletName("EditedOutlet");
        OperatingHours operatingHours = new OperatingHours("10:00-18:00");
        EditOutletCommand command = new EditOutletCommand(masterPassword, outletName, operatingHours);
        String expectedMessage = EditOutletCommand.MESSAGE_EDIT_OUTLET_SUCCESS;
        Model expectedModel = new ModelManager(new PartTimeManager(model.getPartTimeManager()), new UserPrefs());
        expectedModel.updateOutlet(outletName, operatingHours);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
