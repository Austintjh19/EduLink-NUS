package seedu.edulink.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.edulink.model.student.Student;

/**
 * A UI component that displays information of a {@code Person} in Detail.
 */
public class DetailsCard extends UiPart<Region> {

    private static final String FXML = "DetailsCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label studentId;
    @FXML
    private Label major;
    @FXML
    private Label intake;
    @FXML
    private Label module;
    @FXML
    private Label grade;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public DetailsCard(Student student) {
        super(FXML);
        this.student = student;
        studentId.setText(student.getId().id);
        name.setText(student.getName().fullName);
        phone.setText(student.getPhone().value);
        major.setText(student.getMajor().major);
        intake.setText(student.getIntake().toString());
        address.setText(student.getAddress().value);
        email.setText(student.getEmail().value);
        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
