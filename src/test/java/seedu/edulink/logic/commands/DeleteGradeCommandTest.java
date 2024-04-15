package seedu.edulink.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_MODULE_CS2103T;
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

import seedu.edulink.model.Model;
import seedu.edulink.model.ModelManager;
import seedu.edulink.model.UserPrefs;
import seedu.edulink.model.grade.Grade;
import seedu.edulink.model.grade.Module;
import seedu.edulink.model.student.Id;
import seedu.edulink.model.student.Student;
import seedu.edulink.testutil.PersonBuilder;

public class DeleteGradeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIdValidModule_success() {
        Id validId = BENSON.getId();
        Module validModule = BENSON_GRADE.getModule();
        DeleteGradeCommand command = new DeleteGradeCommand(validId, validModule);

        Set<Grade> gradeList = new HashSet<>(BENSON.getGrades());
        gradeList.remove(BENSON_GRADE);
        Student resultStudent = new PersonBuilder().withName("Benson Meier").withId("A0265901E")
            .withMajor("Physics").withIntake("2023")
            .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com")
            .withPhone("98765432").withTags("owesMoney", "friends")
            .withGrades(gradeList).build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(BENSON, resultStudent);
        String expectedMessage = String.format(
            DeleteGradeCommand.MESSAGE_DELETE_GRADE_SUCCESS, validModule, validId);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noStudentFound_throwCommandException() {
        Id nonExistStudentId = new Id("A0912124E");
        Module validModule = new Module(VALID_MODULE_CS2103T);
        DeleteGradeCommand command = new DeleteGradeCommand(nonExistStudentId, validModule);
        String expectedMessage = DeleteGradeCommand.MESSAGE_PERSON_NOTFOUND;

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_noModuleFound_throwCommandException() {
        Id validId = ALICE.getId();
        Module validModule = new Module(VALID_MODULE_CS2103T);
        DeleteGradeCommand command = new DeleteGradeCommand(validId, validModule);
        String expectedMessage = DeleteGradeCommand.MESSAGE_GRADE_NOTFOUND;

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        DeleteGradeCommand command1 = new DeleteGradeCommand(BENSON.getId(), BENSON_GRADE.getModule());
        DeleteGradeCommand command2 = new DeleteGradeCommand(ALICE.getId(), ALICE_GRADE.getModule());

        // same object -> returns true
        assertTrue(command1.equals(command1));

        // different objects -> returns False
        assertFalse(command1.equals(command2));

        // null -> returns False
        assertFalse(command1.equals(null));

        // different types -> returns false
        assertFalse(command1.equals("not a DeleteGradeCommand"));

        // same values -> returns true
        DeleteGradeCommand command3 = new DeleteGradeCommand(new Id("A0251893P"),
            new Module("cs2113"));
        assertTrue(command2.equals(command3));

        // different values -> returns true
        DeleteGradeCommand command4 = new DeleteGradeCommand(new Id("A0251893P"),
             new Module(VALID_MODULE_CS2103T));
        assertFalse(command2.equals(command4));
    }


}
