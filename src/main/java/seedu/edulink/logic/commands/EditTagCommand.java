package seedu.edulink.logic.commands;

import static seedu.edulink.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.edulink.commons.util.ToStringBuilder;
import seedu.edulink.logic.commands.exceptions.CommandException;
import seedu.edulink.model.Model;
import seedu.edulink.model.student.Id;
import seedu.edulink.model.student.Student;
import seedu.edulink.model.tag.Tag;

/**
 * Edits a student's tag.
 */
public class EditTagCommand extends Command {

    public static final String COMMAND_WORD = "etag";

    public static final String MESSAGE_PERSON_NOTFOUND = "Can't find the Student you specified.";
    public static final String MESSAGE_EDIT_TAG_SUCCESS = "Edited Tags: %1$s: tag %2$s is replaced by %3$s";
    public static final String MESSAGE_USAGE = "Usage: " + COMMAND_WORD + " " + PREFIX_ID + "ID "
            + PREFIX_TAG + "Tag to edit " + PREFIX_TAG + "Resulting tag";
    public static final String MESSAGE_TAG_NOT_FOUND = "The tag you want to edit is not found.";
    public static final String MESSAGE_TWO_TAGS_NEED = "Please input exactly two tags.";
    public static final String MESSAGE_DUPLICATE = "Command Invalid: the resulting tag you input is already there.";

    public static final String MESSAGE_DUPLICATE_AND_NOT_FOUND =
            "Command Invalid: the tag you want to edit is not found, the resulting tag you input is already there.";
    private final Id studentToEditId;
    private final Tag tagToEdit;
    private final Tag resultingTag;

    /**
     * Creates a EditTagCommand to edit tags of a student.
     *
     * @param studentToEditId the ID of the student user add tags to.
     * @param tagToEdit the tag that the user wants to change.
     * @param resultingTag the resulting tag that the user wants to change to.
     */
    public EditTagCommand(Id studentToEditId, Tag tagToEdit, Tag resultingTag) {
        requireAllNonNull(studentToEditId, tagToEdit, resultingTag);

        this.studentToEditId = studentToEditId;
        this.tagToEdit = tagToEdit;
        this.resultingTag = resultingTag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredPersonList();
        Optional<Student> optionalStudentToEdit = lastShownList.stream().filter(
                student -> student.getId().equals(studentToEditId)
        ).findFirst();
        if (optionalStudentToEdit.isEmpty()) {
            throw new CommandException(MESSAGE_PERSON_NOTFOUND);
        }
        Student studentToEdit = optionalStudentToEdit.get();
        Set<Tag> originalTags = studentToEdit.getTags();
        Set<Tag> editedTags = new HashSet<>(originalTags);
        boolean isResultingTagExit = editedTags.contains(resultingTag);
        boolean isTagToEditExist = editedTags.contains(tagToEdit);
        if (isResultingTagExit && !isTagToEditExist) {
            throw new CommandException(MESSAGE_DUPLICATE_AND_NOT_FOUND);
        }
        if (!isTagToEditExist) {
            throw new CommandException(MESSAGE_TAG_NOT_FOUND);
        }
        if (isResultingTagExit) {
            throw new CommandException(MESSAGE_DUPLICATE);
        }
        editedTags.remove(tagToEdit);
        editedTags.add(resultingTag);
        Student editedStudent = new Student(studentToEdit.getId(), studentToEdit.getMajor(), studentToEdit.getIntake(),
            studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
            studentToEdit.getAddress(), editedTags, studentToEdit.getGrades());

        model.setPerson(studentToEdit, editedStudent);
        return new CommandResult(String.format(MESSAGE_EDIT_TAG_SUCCESS, studentToEditId, tagToEdit, resultingTag));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditTagCommand)) {
            return false;
        }

        EditTagCommand otherTagCommand = (EditTagCommand) other;
        boolean isStudentIdEqual = this.studentToEditId.equals(otherTagCommand.studentToEditId);
        boolean isTagToEditEqual = this.tagToEdit.equals(otherTagCommand.tagToEdit);
        boolean isResultingTagEqual = this.resultingTag.equals(otherTagCommand.resultingTag);
        return (isStudentIdEqual && isResultingTagEqual && isTagToEditEqual);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("Id", this.studentToEditId)
            .add("TagToEdit", this.tagToEdit)
            .add("ResultingTag", this.resultingTag)
            .toString();
    }
}
