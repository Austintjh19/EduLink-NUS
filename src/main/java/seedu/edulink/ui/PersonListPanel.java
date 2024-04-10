package seedu.edulink.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.edulink.commons.core.LogsCenter;
import seedu.edulink.model.student.Student;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Student> personListView;

    private final MainWindow mainWindow;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(MainWindow mainWindow, ObservableList<Student> studentList) {
        super(FXML);
        this.mainWindow = mainWindow;
        personListView.setItems(studentList);
        personListView.setCellFactory(listView -> {
            PersonListViewCell cell = new PersonListViewCell();
            cell.setOnMouseClicked(mouseEvent -> {
                PersonListViewCell source = (PersonListViewCell) mouseEvent.getSource();
                mainWindow.updateStudentDetailsCard(source.getStudent());
            });
            return cell;
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Student> {
        private Student student;
        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(mainWindow, student, getIndex() + 1).getRoot());
                this.student = student;
            }
        }

        public Student getStudent() {
            return this.student;
        }
    }

    public ListView<Student> getPersonListView() {
        return this.personListView;
    }

}
