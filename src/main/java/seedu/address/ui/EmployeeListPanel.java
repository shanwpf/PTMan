package seedu.address.ui;

import java.util.logging.Logger;

import org.fxmisc.easybind.EasyBind;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.commons.events.ui.EmployeePanelSelectionChangedEvent;
import seedu.address.model.employee.Employee;

/**
 * Panel containing the list of employees.
 */
public class EmployeeListPanel extends UiPart<Region> {
    private static final String FXML = "EmployeeListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(EmployeeListPanel.class);

    @FXML
    private ListView<EmployeeCard> employeeListView;

    public EmployeeListPanel(ObservableList<Employee> employeeList) {
        super(FXML);
        setConnections(employeeList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Employee> employeeList) {
        ObservableList<EmployeeCard> mappedList = EasyBind.map(
                employeeList, (employee) -> new EmployeeCard(employee, employeeList.indexOf(employee) + 1));
        employeeListView.setItems(mappedList);
        employeeListView.setCellFactory(listView -> new EmployeeListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void setEventHandlerForSelectionChangeEvent() {
        employeeListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        logger.fine("Selection in employee list panel changed to : '" + newValue + "'");
                        raise(new EmployeePanelSelectionChangedEvent(newValue));
                    }
                });
    }

    /**
     * Scrolls to the {@code EmployeeCard} at the {@code index} and selects it.
     */
    private void scrollTo(int index) {
        Platform.runLater(() -> {
            employeeListView.scrollTo(index);
            employeeListView.getSelectionModel().clearAndSelect(index);
        });
    }

    @Subscribe
    private void handleJumpToListRequestEvent(JumpToListRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        scrollTo(event.targetIndex);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code EmployeeCard}.
     */
    class EmployeeListViewCell extends ListCell<EmployeeCard> {

        @Override
        protected void updateItem(EmployeeCard employee, boolean empty) {
            super.updateItem(employee, empty);

            if (empty || employee == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(employee.getRoot());
            }
        }
    }

}
