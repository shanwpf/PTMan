package seedu.ptman.logic.parser;

import static seedu.ptman.logic.commands.CommandTestUtil.INVALID_MASTER_PASSWORD_DESC;
import static seedu.ptman.logic.commands.CommandTestUtil.INVALID_OPERATING_HOURS_DESC;
import static seedu.ptman.logic.commands.CommandTestUtil.INVALID_OUTLET_NAME_DESC;
import static seedu.ptman.logic.commands.CommandTestUtil.MASTER_PASSWORD_DESC;
import static seedu.ptman.logic.commands.CommandTestUtil.OPERATING_HOURS_DESC;
import static seedu.ptman.logic.commands.CommandTestUtil.OUTLET_NAME_DESC;
import static seedu.ptman.logic.commands.CommandTestUtil.VALID_MASTER_PASSWORD;
import static seedu.ptman.logic.commands.CommandTestUtil.VALID_OPERATING_HOURS;
import static seedu.ptman.logic.commands.CommandTestUtil.VALID_OUTLET_NAME;
import static seedu.ptman.logic.parser.CliSyntax.PREFIX_MASTER_PASSWORD;
import static seedu.ptman.logic.parser.CliSyntax.PREFIX_OPERATING_HOURS;
import static seedu.ptman.logic.parser.CliSyntax.PREFIX_OUTLET_NAME;
import static seedu.ptman.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ptman.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.ptman.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;


import org.junit.Test;

import seedu.ptman.logic.commands.EditOutletCommand;
import seedu.ptman.model.Password;
import seedu.ptman.model.outlet.OperatingHours;
import seedu.ptman.model.outlet.OutletName;

public class EditOutletCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditOutletCommand.MESSAGE_USAGE);
    private EditOutletCommandParser parser = new EditOutletCommandParser();

    @Test
    public void parse_missingParts_failure() {
        String command_missingPassword = EditOutletCommand.COMMAND_WORD + " "
                + PREFIX_OUTLET_NAME + VALID_OUTLET_NAME + " "
                + PREFIX_OPERATING_HOURS + VALID_OPERATING_HOURS;
        assertParseFailure(parser, command_missingPassword, MESSAGE_INVALID_FORMAT);

        String command_missingOutletName = EditOutletCommand.COMMAND_WORD + " "
                + PREFIX_MASTER_PASSWORD + VALID_MASTER_PASSWORD + " "
                + PREFIX_OPERATING_HOURS + VALID_OPERATING_HOURS;
        assertParseFailure(parser, command_missingOutletName, MESSAGE_INVALID_FORMAT);

        String command_missingOperatingHours = EditOutletCommand.COMMAND_WORD + " "
                + PREFIX_MASTER_PASSWORD + VALID_MASTER_PASSWORD + " "
                + PREFIX_OUTLET_NAME + VALID_OUTLET_NAME;
        assertParseFailure(parser, command_missingOperatingHours, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        String command_invalidPassword = EditOutletCommand.COMMAND_WORD
                + INVALID_MASTER_PASSWORD_DESC + " "
                + PREFIX_OUTLET_NAME + VALID_OUTLET_NAME + " "
                + PREFIX_OPERATING_HOURS + VALID_OPERATING_HOURS;
        assertParseFailure(parser, command_invalidPassword, Password.MESSAGE_PASSWORD_CONSTRAINTS);

        String command_invalidName = EditOutletCommand.COMMAND_WORD + " "
                + PREFIX_MASTER_PASSWORD + VALID_MASTER_PASSWORD + INVALID_OUTLET_NAME_DESC + " "
                + PREFIX_OPERATING_HOURS + VALID_OPERATING_HOURS;
        assertParseFailure(parser, command_invalidName, OutletName.MESSAGE_NAME_CONSTRAINTS);

        String command_invalidOperatingHours = EditOutletCommand.COMMAND_WORD + " "
                + PREFIX_MASTER_PASSWORD + VALID_MASTER_PASSWORD + " "
                + PREFIX_OUTLET_NAME + VALID_OUTLET_NAME + INVALID_OPERATING_HOURS_DESC;
        assertParseFailure(parser, command_invalidOperatingHours, OperatingHours.MESSAGE_OPERATING_HOUR_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsValid_success() {
        String userInput = EditOutletCommand.COMMAND_WORD
                + MASTER_PASSWORD_DESC + OUTLET_NAME_DESC + OPERATING_HOURS_DESC;
        Password masterPassword = new Password(VALID_MASTER_PASSWORD);
        OutletName outletName = new OutletName(VALID_OUTLET_NAME);
        OperatingHours operatingHours = new OperatingHours(VALID_OPERATING_HOURS);
        EditOutletCommand expectedCommand = new EditOutletCommand(masterPassword, outletName, operatingHours);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
