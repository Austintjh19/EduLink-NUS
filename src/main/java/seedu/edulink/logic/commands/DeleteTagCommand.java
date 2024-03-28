package seedu.edulink.logic.commands;

import seedu.edulink.commons.util.ToStringBuilder;
import seedu.edulink.logic.commands.exceptions.CommandException;
import seedu.edulink.model.Model;
import seedu.edulink.model.student.Id;
import seedu.edulink.model.student.Student;
import seedu.edulink.model.tag.Tag;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static seedu.edulink.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * Removes tags from a student.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "dtag";

    public static final String MESSAGE_PERSON_NOTFOUND = "Can't find the person you specified.";
    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted Tags: %1$s";
    public static final String MESSAGE_USAGE = "Usage: " + COMMAND_WORD + " " + PREFIX_ID + "ID " + PREFIX_TAG + "Tag";
    public static final String MESSAGE_TAG_NOT_FOUND = "Invalid Command"
            + ", one or more tags you are looking for are not found.";

    private final Id personToEditId;
    private final Set<Tag> tags;

    /**
     * Creates a DeleteTagCommand to delete tags from a student.
     *
     * @param personToEditId the ID of the student user add tags to.
     * @param tags           a set of tags that the user wish to delete from the student.
     */
    public DeleteTagCommand(Id personToEditId, Set<Tag> tags) {
        requireAllNonNull(personToEditId, tags);

        this.personToEditId = personToEditId;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredPersonList();
        Student studentToEdit = null;
        for (Student student : lastShownList) {
            if (student.getId().equals(personToEditId)) {
                studentToEdit = student;
            }
        }
        if (studentToEdit == null) {
            throw new CommandException(MESSAGE_PERSON_NOTFOUND);
        }
        Set<Tag> originalTags = studentToEdit.getTags();
        Set<Tag> removedSet = new HashSet<>(originalTags);
        boolean isAllContained = originalTags.containsAll(tags);
        if (!isAllContained) {
            throw new CommandException(MESSAGE_TAG_NOT_FOUND);
        }
        removedSet.removeAll(tags);

        Student editedStudent = new Student(studentToEdit.getId(), studentToEdit.getMajor(), studentToEdit.getIntake(),
            studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
            studentToEdit.getAddress(), removedSet);

        model.setPerson(studentToEdit, editedStudent);
        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS, tags));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteTagCommand)) {
            return false;
        }

        DeleteTagCommand otherTagCommand = (DeleteTagCommand) other;
        boolean isStudentIdEqual = this.personToEditId.equals(otherTagCommand.personToEditId);
        boolean isTagListEqual = this.tags.equals(otherTagCommand.tags);
        return (isStudentIdEqual && isTagListEqual);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("Id", this.personToEditId)
            .add("Tags", this.tags)
            .toString();
    }
}
