package seedu.edulink.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_MODULE_CS2103T;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_SCORE_89;
import static seedu.edulink.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.edulink.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.edulink.testutil.TypicalPersons.ALICE;
import static seedu.edulink.testutil.TypicalPersons.ALICE_GRADE;
import static seedu.edulink.testutil.TypicalPersons.BENSON;
import static seedu.edulink.testutil.TypicalPersons.BENSON_GRADE;
import static seedu.edulink.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.edulink.logic.Messages;
import seedu.edulink.model.Model;
import seedu.edulink.model.ModelManager;
import seedu.edulink.model.UserPrefs;
import seedu.edulink.model.grade.Grade;
import seedu.edulink.model.grade.Module;
import seedu.edulink.model.grade.Score;
import seedu.edulink.model.student.Id;
import seedu.edulink.model.student.Student;
import seedu.edulink.testutil.PersonBuilder;


public class GradeCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIdValidGrade_success() {
        Id validId = ALICE.getId();
        Grade validGrade = ALICE_GRADE;
        GradeCommand command = new GradeCommand(validId, validGrade);

        Set<Grade> gradeList = new HashSet<>(ALICE.getGrades());
        gradeList.add(validGrade);
        Student resultStudent = new PersonBuilder().withName("Alice Pauline").withId("A0251893P")
            .withMajor("Computer Science").withIntake("2023")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withTags("TA", "Smart", "friends")
            .withGrades(gradeList).build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(ALICE, resultStudent);
        String expectedMessage = String.format(
            GradeCommand.MESSAGE_ADD_GRADE_SUCCESS, Messages.format(validGrade));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noStudentFound_throwCommandException() {
        Id nonExistStudentId = new Id("A0912124E");
        Grade validGrade = new Grade(new Module(VALID_MODULE_CS2103T), new Score(VALID_SCORE_89));
        GradeCommand command = new GradeCommand(nonExistStudentId, validGrade);
        String expectedMessage = GradeCommand.MESSAGE_PERSON_NOTFOUND;

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_duplicateGrade_success() {
        Id validId = BENSON.getId();
        Grade validGrade = new Grade(new Module(VALID_MODULE_CS2103T), new Score(VALID_SCORE_89));
        GradeCommand command = new GradeCommand(validId, validGrade);

        Set<Grade> gradeList = new HashSet<>(BENSON.getGrades());
        gradeList.remove(BENSON_GRADE);
        gradeList.add(validGrade);
        Student resultStudent = new PersonBuilder().withName("Benson Meier").withId("A0265901E")
            .withMajor("Physics").withIntake("2023")
            .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com")
            .withPhone("98765432").withTags("owesMoney", "friends")
            .withGrades(gradeList).build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(BENSON, resultStudent);
        String expectedMessage = String.format(
            GradeCommand.MESSAGE_EDIT_GRADE_SUCCESS, validId,
            validGrade.getModule(), Messages.format(validGrade.getScore()));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        GradeCommand command1 = new GradeCommand(ALICE.getId(), ALICE_GRADE);
        GradeCommand command2 = new GradeCommand(BENSON.getId(), BENSON_GRADE);

        // same object -> returns true
        assertTrue(command1.equals(command1));

        // different objects -> returns False
        assertFalse(command1.equals(command2));

        // null -> returns False
        assertFalse(command1.equals(null));

        // different types -> returns false
        assertFalse(command1.equals("not a GradeCommand"));

        // same values -> returns true
        GradeCommand command3 = new GradeCommand(new Id("A0251893P"),
            new Grade(new Module("cs2113"), new Score(55)));
        assertTrue(command1.equals(command3));

        // different values -> returns false
        GradeCommand command4 = new GradeCommand(new Id("A0251893P"),
            new Grade(new Module(VALID_MODULE_CS2103T), new Score(VALID_SCORE_89)));
        assertFalse(command1.equals(command4));
    }

    @Test
    public void toStringMethod() {
        GradeCommand command = new GradeCommand(ALICE.getId(), ALICE_GRADE);
        String expected = GradeCommand.class.getCanonicalName()
            + "{Id=" + ALICE.getId()
            + ", grade=" + ALICE_GRADE + "}";
        assertEquals(expected, command.toString());
    }
}
