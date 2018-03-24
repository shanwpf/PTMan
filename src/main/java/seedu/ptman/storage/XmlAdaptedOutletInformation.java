package seedu.ptman.storage;

import seedu.ptman.commons.exceptions.IllegalValueException;
import seedu.ptman.model.Password;
import seedu.ptman.model.outlet.OperatingHours;
import seedu.ptman.model.outlet.OutletContact;
import seedu.ptman.model.outlet.OutletEmail;
import seedu.ptman.model.outlet.OutletInformation;
import seedu.ptman.model.outlet.OutletName;

import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * JAXB-friendly version of the OutletInformation.
 */
@XmlRootElement(name = "outletinformation")
public class XmlAdaptedOutletInformation {

    public static final String FAIL_MESSAGE = "Outlet's %s field is missing!";

    @XmlElement(required = true)
    private String outletName;
    @XmlElement(required = true)
    private String operatingHours;
    @XmlElement(required = true)
    private String outletContact;
    @XmlElement(required = true)
    private String outletEmail;
    @XmlElement(required = true)
    private String passwordHash;

    public XmlAdaptedOutletInformation() {
        this.outletName = null;
        this.operatingHours = null;
        this.outletContact = null;
        this.outletEmail = null;
        this.passwordHash = null;
    }

    /**
     * Converts a given OutletInformation into this class for JAXB use.
     */
    public XmlAdaptedOutletInformation(OutletInformation source) {
        this();
        outletName = source.getName().fullName;
        operatingHours = source.getOperatingHours().value;
        outletContact = source.getOutletContact().value;
        outletEmail = source.getOutletEmail().value;
        passwordHash = source.getMasterPassword().getPasswordHash();
    }

    private OutletName setOutletName() throws IllegalValueException {
        if (this.outletName == null) {
            throw new IllegalValueException(String.format(FAIL_MESSAGE, OutletName.class.getSimpleName()));
        }
        if (!OutletName.isValidName(this.outletName)) {
            throw new IllegalValueException(OutletName.MESSAGE_NAME_CONSTRAINTS);
        }
        OutletName outletName = new OutletName(this.outletName);
        return outletName;
    }

    private OperatingHours setOperatingHours() throws IllegalValueException {
        if (this.operatingHours == null) {
            throw new IllegalValueException(String.format(FAIL_MESSAGE, OperatingHours.class.getSimpleName()));
        }
        if (!OperatingHours.isValidOperatingHours(this.operatingHours)) {
            throw new IllegalValueException(OperatingHours.MESSAGE_OPERATING_HOUR_CONSTRAINTS);
        }
        OperatingHours operatingHours = new OperatingHours(this.operatingHours);
        return operatingHours;
    }

    private OutletContact setOutletContact() throws IllegalValueException {
        if (this.outletContact == null) {
            throw new IllegalValueException(String.format(FAIL_MESSAGE, OutletContact.class.getSimpleName()));
        }
        if (!OutletContact.isValidOutletContact(this.outletContact)) {
            throw new IllegalValueException(OutletContact.MESSAGE_OUTLET_CONTACT_CONSTRAINTS);
        }
        OutletContact outletContact = new OutletContact(this.outletContact);
        return outletContact;
    }

    private OutletEmail setOutletEmail() throws IllegalValueException {
        if (this.outletEmail == null) {
            throw new IllegalValueException(String.format(FAIL_MESSAGE, OutletEmail.class.getSimpleName()));
        }
        if (!OutletEmail.isValidOutletEmail(this.outletEmail)) {
            throw new IllegalValueException(OutletEmail.MESSAGE_OUTLET_EMAIL_CONSTRAINTS);
        }
        OutletEmail outletEmail = new OutletEmail(this.outletEmail);
        return outletEmail;
    }

    private Password setPassword() throws IllegalValueException {
        if (this.passwordHash == null) {
            throw new IllegalValueException(String.format(FAIL_MESSAGE, Password.class.getSimpleName()));
        }
        Password masterPassword = new Password(this.passwordHash);
        return masterPassword;
    }

    /**
     * Converts this jaxb-friendly adapted outlet object into the model's OutletInformation object
     */
    public OutletInformation toModelType() throws IllegalValueException {
        final OutletName outletName = setOutletName();
        final OperatingHours operatingHours = setOperatingHours();
        final OutletContact outletContact = setOutletContact();
        final OutletEmail outletEmail = setOutletEmail();
        final Password masterPassword = setPassword();
        return new OutletInformation(outletName, operatingHours, outletContact, outletEmail, masterPassword);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedOutletInformation)) {
            return false;
        }

        XmlAdaptedOutletInformation otherOutlet = (XmlAdaptedOutletInformation) other;
        return Objects.equals(outletName, otherOutlet.outletName)
                && Objects.equals(operatingHours, otherOutlet.operatingHours)
                && Objects.equals(outletContact, otherOutlet.outletContact)
                && Objects.equals(outletEmail, otherOutlet.outletEmail);
    }
}
