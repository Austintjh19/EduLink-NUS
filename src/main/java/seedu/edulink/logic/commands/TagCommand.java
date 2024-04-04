package seedu.edulink.logic.commands;

import static seedu.edulink.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
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
 * Adds tags to a student.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_PERSON_NOTFOUND = "Can't find the student you specified.";
    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added Tags: %1$s";
    public static final String MESSAGE_USAGE = "Usage: " + COMMAND_WORD + " " + PREFIX_ID + "ID " + PREFIX_TAG + "Tag";
    public static final String MESSAGE_DUPLICATE = "Invalid Command: "
            + "one or more tags you input are already there. ";

    private final Id studentToEditId;
    private final Set<Tag> tags;

    /**
     * Creates a TagCommand to add tags to a student.
     *
     * @param studentToEditId the ID of the student user add tags to.
     * @param tags           a set of tags that the user wish to add to the student.
     */
    public TagCommand(Id studentToEditId, Set<Tag> tags) {
        requireAllNonNull(studentToEditId, tags);

        this.studentToEditId = studentToEditId;
        this.tags = tags;
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
        Set<Tag> mergedSet = new HashSet<>(originalTags);
        boolean isSetDisjoint = Collections.disjoint(mergedSet, tags);
        if (!isSetDisjoint) {
            throw new CommandException(MESSAGE_DUPLICATE);
        }
        mergedSet.addAll(tags);

        Student editedStudent = new Student(studentToEdit.getId(), studentToEdit.getMajor(), studentToEdit.getIntake(),
            studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
            studentToEdit.getAddress(), mergedSet, studentToEdit.getGrades());

        model.setPerson(studentToEdit, editedStudent);
        return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS, tags));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TagCommand)) {
            return false;
        }

        TagCommand otherTagCommand = (TagCommand) other;
        boolean isStudentIdEqual = this.studentToEditId.equals(otherTagCommand.studentToEditId);
        boolean isTagListEqual = this.tags.equals(otherTagCommand.tags);
        return (isStudentIdEqual && isTagListEqual);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("Id", this.studentToEditId)
            .add("Tags", this.tags)
            .toString();
    }
}
