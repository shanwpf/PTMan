package seedu.ptman.storage;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.ptman.commons.core.LogsCenter;
import seedu.ptman.commons.exceptions.DataConversionException;
import seedu.ptman.commons.exceptions.IllegalValueException;
import seedu.ptman.commons.util.FileUtil;
import seedu.ptman.model.outlet.OutletInformation;

/**
 * A class to access OutletInformation data stored as an xml file on the hard disk.
 */
public class XmlOutletInformationStorage implements OutletInformationStorage {

    private static final Logger logger = LogsCenter.getLogger(XmlOutletInformationStorage.class);
    private static final String BACKUP_FILE_EXTENSION = ".backup";

    private String filePath;

    public XmlOutletInformationStorage(String filePath) {
        this.filePath = filePath;
    }

    public String getOutletInformationFilePath() {
        return filePath;
    }

    @Override
    public Optional<OutletInformation> readOutletInformation() throws DataConversionException, IOException {
        return readOutletInformation(filePath);
    }

    public Optional<OutletInformation> readOutletInformation(String filePath) throws DataConversionException,
            FileNotFoundException {
        requireNonNull(filePath);

        File outletInformationFile = new File(filePath);

        if (!outletInformationFile.exists()) {
            logger.info("OutletInformation file " + outletInformationFile + " not found");
            return Optional.empty();
        }

        XmlAdaptedOutletInformation xmlAdaptedOutletInformation = XmlOutletFileStorage
                .loadDataFromSaveFile(new File(filePath));
        try {
            return Optional.of(xmlAdaptedOutletInformation.toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + outletInformationFile + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveOutletInformation(OutletInformation outletInformation) throws IOException {
        saveOutletInformation(outletInformation, filePath);
    }

    public void saveOutletInformation(OutletInformation outletInformation, String filePath) throws IOException {
        requireNonNull(outletInformation);
        requireNonNull(filePath);

        File file = new File(filePath);
        FileUtil.createIfMissing(file);
        XmlOutletFileStorage.saveDataToFile(file, new XmlAdaptedOutletInformation(outletInformation));
    }
}
