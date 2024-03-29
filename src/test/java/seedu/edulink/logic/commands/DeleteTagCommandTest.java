package seedu.edulink.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edulink.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.edulink.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.edulink.testutil.TypicalPersons.BENSON;
import static seedu.edulink.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.edulink.model.Model;
import seedu.edulink.model.ModelManager;
import seedu.edulink.model.UserPrefs;
import seedu.edulink.model.student.Id;
import seedu.edulink.model.student.Student;
import seedu.edulink.model.tag.Tag;
import seedu.edulink.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code DeleteTagCommand}.
 */
public class DeleteTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Id firstStudentId = new Id("A1029384A");
        HashSet<Tag> firstTagList = new HashSet<Tag>();
        firstTagList.add(new Tag("TopStudent"));
        firstTagList.add(new Tag("PotentialTA"));

        Id secondStudentId = new Id("A1029384A");
        HashSet<Tag> secondTagList = new HashSet<Tag>();
        secondTagList.add(new Tag("TopStudent"));
        secondTagList.add(new Tag("PotentialTA"));

        Id thirdStudentId = new Id("A2129334F");
        HashSet<Tag> thirdTagList = new HashSet<Tag>();
        thirdTagList.add(new Tag("BadStudent"));
        thirdTagList.add(new Tag("GoodStudent"));

        DeleteTagCommand firstTagCommand = new DeleteTagCommand(firstStudentId, firstTagList);
        DeleteTagCommand secondTagCommand = new DeleteTagCommand(secondStudentId, secondTagList);
        DeleteTagCommand thirdTagCommand = new DeleteTagCommand(thirdStudentId, thirdTagList);

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
        HashSet<Tag> tagList = new HashSet<Tag>();
        tagList.add(new Tag("TopStudent"));
        Id invalidId = new Id("A0912124E");
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(invalidId, tagList);

        assertCommandFailure(deleteTagCommand, model, DeleteTagCommand.MESSAGE_PERSON_NOTFOUND);
    }

    @Test
    public void execute_tagNotFound_throwCommandException() {
        HashSet<Tag> tagList = new HashSet<Tag>();
        tagList.add(new Tag("potentialta"));
        Id invalidId = new Id("A0251893P");
        DeleteTagCommand deletetagCommand = new DeleteTagCommand(invalidId, tagList);

        assertCommandFailure(deletetagCommand, model, DeleteTagCommand.MESSAGE_TAG_NOT_FOUND);
    }

    @Test
    public void execute_validIdValidTag_success() {
        HashSet<Tag> tagList = new HashSet<Tag>();
        tagList.add(new Tag("friends"));
        Id validId = new Id("A0265901E");
        DeleteTagCommand deletetagCommand = new DeleteTagCommand(validId, tagList);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Student resultStudent = new PersonBuilder().withName("Benson Meier").withId("A0265901E")
                .withMajor("Physics").withIntake("2023")
                .withAddress("311, Clementi Ave 2, #02-25")
                .withEmail("johnd@example.com").withPhone("98765432")
                .withTags("owesMoney").build();
        expectedModel.setPerson(BENSON, resultStudent);
        assertCommandSuccess(deletetagCommand, model,
                String.format(DeleteTagCommand.MESSAGE_DELETE_TAG_SUCCESS, tagList), expectedModel);
    }

    @Test
    public void toStringMethod() {
        HashSet<Tag> tagList = new HashSet<Tag>();
        tagList.add(new Tag("TopStudent"));
        Id invalidId = new Id("A0912124E");
        TagCommand tagCommand = new TagCommand(invalidId, tagList);

        String expected = TagCommand.class.getCanonicalName() + "{Id=" + "A0912124E, " + "Tags=" + tagList + "}";
        assertEquals(expected, tagCommand.toString());
    }
}
