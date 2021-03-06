package seedu.ptman.model.outlet;

import static java.util.Objects.requireNonNull;
import static seedu.ptman.commons.util.AppUtil.checkArgument;

//@@author SunBangjie
/**
 * Represents an email of outlet in PTMan.
 * Guarantees: immutable; is valid as declared in {@link #isValidOutletEmail(String)}
 */
public class OutletEmail {

    private static  final String SPECIAL_CHARACTERS = "!#$%&'*+/=?`{|}~^.-";
    public static final String MESSAGE_OUTLET_EMAIL_CONSTRAINTS = "Outlet emails should be of the format "
            + "local-part@domain and adhere to the following constraints:\n"
            + "1. The local-part should only contain alphanumeric characters and these special "
            + "characters, excluding the parentheses, (" + SPECIAL_CHARACTERS + ") .\n"
            + "2. This is followed by a '@' and then a domain name. "
            + "The domain name must:\n"
            + "    - be at least 2 characters long\n"
            + "    - start and end with alphanumeric characters\n"
            + "    - consist of alphanumeric characters, a period or a hyphen for the "
            + "characters in between, if any.";
    // alphanumeric and special characters
    private static final String LOCAL_PART_REGEX = "^[\\w" + SPECIAL_CHARACTERS + "]+";
    // alphanumeric characters except underscore
    private static final String DOMAIN_FIRST_CHARACTER_REGEX = "[^\\W_]";
    // alphanumeric, period and hyphen
    private static final String DOMAIN_MIDDLE_REGEX = "[a-zA-Z0-9.-]*";
    private static final String DOMAIN_LAST_CHARACTER_REGEX = "[^\\W_]$";
    public static final String OUTLET_EMAIL_VALIDATION_REGEX = LOCAL_PART_REGEX + "@"
            + DOMAIN_FIRST_CHARACTER_REGEX + DOMAIN_MIDDLE_REGEX + DOMAIN_LAST_CHARACTER_REGEX;

    public final String value;

    /**
     * Constructs an {@code OutletEmail}.
     *
     * @param outletEmail A valid outletEmail ptman.
     */
    public OutletEmail(String outletEmail) {
        requireNonNull(outletEmail);
        checkArgument(isValidOutletEmail(outletEmail), MESSAGE_OUTLET_EMAIL_CONSTRAINTS);
        this.value = outletEmail;
    }

    /**
     * Returns if a given string is a valid outlet email.
     */
    public static boolean isValidOutletEmail(String test) {
        return test.matches(OUTLET_EMAIL_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OutletEmail // instanceof handles nulls
                && this.value.equals(((OutletEmail) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
