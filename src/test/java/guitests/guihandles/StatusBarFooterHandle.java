package guitests.guihandles;

import org.controlsfx.control.StatusBar;

import javafx.scene.Node;

/**
 * A handle for the {@code StatusBarFooter} at the footer of the application.
 */
public class StatusBarFooterHandle extends NodeHandle<Node> {
    public static final String STATUS_BAR_PLACEHOLDER = "#statusbarPlaceholder";

    private static final String SYNC_STATUS_ID = "#syncStatus";
    private static final String NUM_PERSONS_STATUS_ID = "#numPersonsStatus";
    private static final String SAVE_LOCATION_STATUS_ID = "#saveLocationStatus";

    private final StatusBar syncStatusNode;
    private final StatusBar numPersonsNode;
    private final StatusBar saveLocationNode;

    private String lastRememberedSyncStatus;
    private String lastRememberedNumPersons;
    private String lastRememberedSaveLocation;

    public StatusBarFooterHandle(Node statusBarFooterNode) {
        super(statusBarFooterNode);

        this.syncStatusNode = getChildNode(SYNC_STATUS_ID);
        this.numPersonsNode = getChildNode(NUM_PERSONS_STATUS_ID);
        this.saveLocationNode = getChildNode(SAVE_LOCATION_STATUS_ID);
    }

    /**
     * Returns the text of the sync status portion of the status bar.
     */
    public String getSyncStatus() {
        return syncStatusNode.getText();
    }

    /**
     * Returns the text of the 'num persons' portion of the status bar.
     */
    public String getNumPersons() {
        return numPersonsNode.getText();
    }

    /**
     * Returns the text of the 'save location' portion of the status bar.
     */
    public String getSaveLocation() {
        return saveLocationNode.getText();
    }

    /**
     * Remembers the content of the sync status portion of the status bar.
     */
    public void rememberSyncStatus() {
        lastRememberedSyncStatus = getSyncStatus();
    }

    /**
     * Returns true if the current content of the sync status is different from the value remembered by the most recent
     * {@code rememberSyncStatus()} call.
     */
    public boolean isSyncStatusChanged() {
        return !lastRememberedSyncStatus.equals(getSyncStatus());
    }

    /**
     * Remembers the content of the 'num Persons' portion of the status bar.
     */
    public void rememberNumPersons() {
        lastRememberedNumPersons = getNumPersons();
    }

    /**
     * Returns true if the current content of the 'num persons' is different from the value remembered by the most
     * recent {@code rememberSaveLocation()} call.
     */
    public boolean isNumPersonsChanged() {
        return !lastRememberedNumPersons.equals(getNumPersons());
    }

    /**
     * Remembers the content of the 'save location' portion of the status bar.
     */
    public void rememberSaveLocation() {
        lastRememberedSaveLocation = getSaveLocation();
    }

    /**
     * Returns true if the current content of the 'save location' is different from the value remembered by the most
     * recent {@code rememberSaveLocation()} call.
     */
    public boolean isSaveLocationChanged() {
        return !lastRememberedSaveLocation.equals(getSaveLocation());
    }
}
