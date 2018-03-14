package seedu.ptman.model.outlet;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UniqueShiftListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        UniqueShiftList uniqueShiftList = new UniqueShiftList();
        thrown.expect(UnsupportedOperationException.class);
        uniqueShiftList.asObservableList().remove(0);
    }
}
