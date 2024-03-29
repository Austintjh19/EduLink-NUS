package seedu.edulink.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edulink.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.edulink.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.edulink.testutil.TypicalPersons.BENSON;
import static seedu.edulink.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.edulink.model.Model;
import seedu.edulink.model.ModelManager;
import seedu.edulink.model.UserPrefs;
import seedu.edulink.model.student.Id;
import seedu.edulink.model.student.Student;
import seedu.edulink.model.tag.Tag;
import seedu.edulink.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code EditTagCommand}.
 */
public class EditTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Id firstStudentId = new Id("A1029384A");
        Tag firstTagFirstStu = new Tag("TopStudent");
        Tag secondTagFirstStu = new Tag("PotentialTA");

        Id secondStudentId = new Id("A1029384A");
        Tag firstTagSecondStu = new Tag("TopStudent");
        Tag secondTagSecondStu = new Tag("PotentialTA");

        Id thirdStudentId = new Id("A2129334F");
        Tag firstTagThirdStu = new Tag("BadStudent");
        Tag secondTagThirdStu = new Tag("GoodStudent");

        EditTagCommand firstTagCommand = new EditTagCommand(firstStudentId, firstTagFirstStu, secondTagFirstStu);
        EditTagCommand secondTagCommand = new EditTagCommand(secondStudentId, firstTagSecondStu, secondTagSecondStu);
        EditTagCommand thirdTagCommand = new EditTagCommand(thirdStudentId, firstTagThirdStu, secondTagThirdStu);

        // same object -> returns true
        assertTrue(firstTagCommand.equals(firstTagCommand));

        // same values -> returns true
        assertTrue(firstTagCommand.equals(secondTagCommand));

        // different types -> returns false
        assertFalse(firstTagCommand.equals(1));

        // null -> returns false
        assertFalse(firstTagCommand.equals(null));

        // different person -> returns false
        assertFalse(firstTagCommand.equals(thirdTagCommand));
    }

    @Test
    public void execute_noStudentFound_throwCommandException() {
        Tag tagToReplace = new Tag("TopStudent");
        Tag resultingTag = new Tag("friend");
        Id invalidId = new Id("A0912124E");
        EditTagCommand deleteTagCommand = new EditTagCommand(invalidId, tagToReplace, resultingTag);
        assertCommandFailure(deleteTagCommand, model, DeleteTagCommand.MESSAGE_PERSON_NOTFOUND);
    }

    @Test
    public void execute_tagDuplicateTagNotFound_throwCommandException() {
        Tag tagToReplace = new Tag("potentialta");
        Tag resultingTag = new Tag("friends");
        Id validId = new Id("A0251893P");
        EditTagCommand editTagCommand = new EditTagCommand(validId, tagToReplace, resultingTag);
        assertCommandFailure(editTagCommand, model, EditTagCommand.MESSAGE_DUPLICATE_AND_NOT_FOUND);
    }

    @Test
    public void execute_tagDuplicate_throwCommandException() {
        Tag tagToReplace = new Tag("owesMoney");
        Tag resultingTag = new Tag("friends");
        Id validId = new Id("A0265901E");
        EditTagCommand editTagCommand = new EditTagCommand(validId, tagToReplace, resultingTag);
        assertCommandFailure(editTagCommand, model, EditTagCommand.MESSAGE_DUPLICATE);
    }

    @Test
    public void execute_tagNotFound_throwCommandException() {
        Tag tagToReplace = new Tag("Money");
        Tag resultingTag = new Tag("good");
        Id validId = new Id("A0265901E");
        EditTagCommand editTagCommand = new EditTagCommand(validId, tagToReplace, resultingTag);
        assertCommandFailure(editTagCommand, model, EditTagCommand.MESSAGE_TAG_NOT_FOUND);
    }

    @Test
    public void execute_validIdValidTag_success() {
        Tag tagToReplace = new Tag("friends");
        Tag resultingTag = new Tag("parent");
        Id validId = new Id("A0265901E");
        EditTagCommand editTagCommand = new EditTagCommand(validId, tagToReplace, resultingTag);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Student resultStudent = new PersonBuilder().withName("Benson Meier").withId("A0265901E")
                .withMajor("Physics").withIntake("2023")
                .withAddress("311, Clementi Ave 2, #02-25")
                .withEmail("johnd@example.com").withPhone("98765432")
                .withTags("owesMoney", "parent").build();
        expectedModel.setPerson(BENSON, resultStudent);
        assertCommandSuccess(editTagCommand, model,
                String.format(EditTagCommand.MESSAGE_EDIT_TAG_SUCCESS, validId, tagToReplace, resultingTag),
                expectedModel);
    }

    @Test
    public void toStringMethod() {
        Tag tagToReplace = new Tag("friends");
        Tag resultingTag = new Tag("parent");
        Id validId = new Id("A0265901E");
        EditTagCommand editTagCommand = new EditTagCommand(validId, tagToReplace, resultingTag);

        String expected = EditTagCommand.class.getCanonicalName() + "{Id=" + "A0265901E, " + "TagToEdit=" + tagToReplace
                + ", ResultingTag=" + resultingTag + "}";
        assertEquals(expected, editTagCommand.toString());
    }
}
