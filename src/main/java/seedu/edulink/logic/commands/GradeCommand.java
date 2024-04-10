package seedu.edulink.logic.commands;

import static seedu.edulink.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_SCORE;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.edulink.commons.util.ToStringBuilder;
import seedu.edulink.logic.Messages;
import seedu.edulink.logic.commands.exceptions.CommandException;
import seedu.edulink.model.Model;
import seedu.edulink.model.grade.Grade;
import seedu.edulink.model.student.Id;
import seedu.edulink.model.student.Student;

/**
 * Adds grade to a student.
 */
public class GradeCommand extends Command {

    public static final String COMMAND_WORD = "grade";

    public static final String MESSAGE_PERSON_NOTFOUND = "Can't find the person you specified.";
    public static final String MESSAGE_ADD_GRADE_SUCCESS = "Added Grade: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds grade to a Student in the address book. "
            + "Parameters: " + PREFIX_ID + "ID " + PREFIX_MODULE + "MODULE " + PREFIX_SCORE + "SCORE"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "A0265901E " + PREFIX_MODULE + "CS2103T " + PREFIX_SCORE + "80";
    public static final String MESSAGE_EDIT_GRADE_SUCCESS = "Edited Grade: "
            + "%1$s: score for module %2$s changed to %3$s";

    private final Id studentToGradeId;
    private final Grade grade;

    /**
     * Creates a GradeCommand to add grade to a student.
     *
     * @param studentToGradeId the ID of the student user add grade to.
     * @param grade2           grade that the user wish to add to the student.
     */
    public GradeCommand(Id studentToGradeId, Grade grade2) {
        requireAllNonNull(studentToGradeId, grade2);

        this.studentToGradeId = studentToGradeId;
        this.grade = grade2;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredPersonList();
        Optional<Student> optionalStudentToEdit = lastShownList.stream().filter(
                student -> student.getId().equals(studentToGradeId)
        ).findFirst();
        if (optionalStudentToEdit.isEmpty()) {
            throw new CommandException(MESSAGE_PERSON_NOTFOUND);
        }
        Student studentToEdit = optionalStudentToEdit.get();
        Set<Grade> originalGrades = studentToEdit.getGrades();
        Set<Grade> editedGrades = new HashSet<>(originalGrades);

        Optional<Grade> optionalGradeToEdit = editedGrades.stream().filter(
                grade -> grade.getModule().equals(this.grade.getModule())
        ).findFirst();
        optionalGradeToEdit.ifPresent(existingGrade -> {
            editedGrades.remove(existingGrade);
        });
        editedGrades.add(this.grade);

        Student editedGrade = new Student(studentToEdit.getId(), studentToEdit.getMajor(), studentToEdit.getIntake(),
                studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
                studentToEdit.getAddress(), studentToEdit.getTags(), editedGrades);
        model.setPerson(studentToEdit, editedGrade);

        if (optionalGradeToEdit.isEmpty()) {
            return new CommandResult(String.format(MESSAGE_ADD_GRADE_SUCCESS, Messages.format(grade)));
        }

        return new CommandResult(String.format(MESSAGE_EDIT_GRADE_SUCCESS, studentToGradeId,
                grade.getModule(), Messages.format(grade.getScore())));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof GradeCommand)) {
            return false;
        }

        GradeCommand otherGradeCommand = (GradeCommand) other;
        boolean isStudentIdEqual = this.studentToGradeId.equals(otherGradeCommand.studentToGradeId);
        boolean isGradeEqual = this.grade.equals(otherGradeCommand.grade);
        return (isStudentIdEqual && isGradeEqual);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("Id", this.studentToGradeId)
            .add("grade", this.grade)
            .toString();
    }
}
