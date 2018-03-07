package seedu.address.ui;

import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.EventsUtil.postNow;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.ui.StatusBarFooter.NUM_PERSONS_STATUS;
import static seedu.address.ui.StatusBarFooter.SYNC_STATUS_INITIAL;
import static seedu.address.ui.StatusBarFooter.SYNC_STATUS_UPDATED;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import guitests.guihandles.StatusBarFooterHandle;
import seedu.address.commons.events.model.AddressBookChangedEvent;
import seedu.address.testutil.AddressBookBuilder;

public class StatusBarFooterTest extends GuiUnitTest {

    private static final String STUB_SAVE_LOCATION = "Stub";
    private static final String RELATIVE_PATH = "./";

    private static final int INITIAL_NUM_PERSONS = 0;

    private static final AddressBookChangedEvent EVENT_STUB = new AddressBookChangedEvent(
            new AddressBookBuilder().withPerson(ALICE).build());

    private static final Clock originalClock = StatusBarFooter.getClock();
    private static final Clock injectedClock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

    private StatusBarFooterHandle statusBarFooterHandle;

    @BeforeClass
    public static void setUpBeforeClass() {
        // inject fixed clock
        StatusBarFooter.setClock(injectedClock);
    }

    @AfterClass
    public static void tearDownAfterClass() {
        // restore original clock
        StatusBarFooter.setClock(originalClock);
    }

    @Before
    public void setUp() {
        StatusBarFooter statusBarFooter = new StatusBarFooter(STUB_SAVE_LOCATION, INITIAL_NUM_PERSONS);
        uiPartRule.setUiPart(statusBarFooter);

        statusBarFooterHandle = new StatusBarFooterHandle(statusBarFooter.getRoot());
    }

    @Test
    public void display() {
        // initial state
        assertStatusBarContent(RELATIVE_PATH + STUB_SAVE_LOCATION, SYNC_STATUS_INITIAL,
                String.format(NUM_PERSONS_STATUS, INITIAL_NUM_PERSONS));

        // after address book is updated
        postNow(EVENT_STUB);
        assertStatusBarContent(RELATIVE_PATH + STUB_SAVE_LOCATION,
                String.format(SYNC_STATUS_UPDATED, new Date(injectedClock.millis()).toString()),
                String.format(NUM_PERSONS_STATUS, EVENT_STUB.data.getPersonList().size()));
    }

    /**
     * Asserts that the save location matches that of {@code expectedSaveLocation}, the
     * sync status matches that of {@code expectedSyncStatus}, and the num persons matches
     * that of {@code expectedNumPersons}
     */
    private void assertStatusBarContent(String expectedSaveLocation,
                                        String expectedSyncStatus, String expectedNumPersons) {
        assertEquals(expectedSaveLocation, statusBarFooterHandle.getSaveLocation());
        assertEquals(expectedNumPersons, statusBarFooterHandle.getNumPersons());
        assertEquals(expectedSyncStatus, statusBarFooterHandle.getSyncStatus());
        guiRobot.pauseForHuman();
    }

}
