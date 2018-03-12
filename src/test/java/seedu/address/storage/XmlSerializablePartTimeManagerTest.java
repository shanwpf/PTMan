package seedu.address.storage;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.XmlUtil;
import seedu.address.model.PartTimeManager;
import seedu.address.testutil.TypicalPersons;

public class XmlSerializablePartTimeManagerTest {

    private static final String TEST_DATA_FOLDER = FileUtil.getPath("src/test/data/XmlSerializablePartTimeManagerTest/");
    private static final File TYPICAL_PERSONS_FILE = new File(TEST_DATA_FOLDER + "typicalPersonsPartTimeManager.xml");
    private static final File INVALID_PERSON_FILE = new File(TEST_DATA_FOLDER + "invalidPersonPartTimeManager.xml");
    private static final File INVALID_TAG_FILE = new File(TEST_DATA_FOLDER + "invalidTagPartTimeManager.xml");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        XmlSerializablePartTimeManager dataFromFile = XmlUtil.getDataFromFile(TYPICAL_PERSONS_FILE,
                XmlSerializablePartTimeManager.class);
        PartTimeManager partTimeManagerFromFile = dataFromFile.toModelType();
        PartTimeManager typicalPersonsPartTimeManager = TypicalPersons.getTypicalPartTimeManager();
        assertEquals(partTimeManagerFromFile, typicalPersonsPartTimeManager);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        XmlSerializablePartTimeManager dataFromFile = XmlUtil.getDataFromFile(INVALID_PERSON_FILE,
                XmlSerializablePartTimeManager.class);
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }

    @Test
    public void toModelType_invalidTagFile_throwsIllegalValueException() throws Exception {
        XmlSerializablePartTimeManager dataFromFile = XmlUtil.getDataFromFile(INVALID_TAG_FILE,
                XmlSerializablePartTimeManager.class);
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }
}
