package seedu.edulink.logic.commands;

import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.edulink.commons.util.ToStringBuilder;
import seedu.edulink.logic.commands.exceptions.CommandException;
import seedu.edulink.model.Model;
import seedu.edulink.model.grade.Grade;
import seedu.edulink.model.grade.Module;
import seedu.edulink.model.student.Id;
import seedu.edulink.model.student.Student;

/**
 * Deletes a grade identified using it's displayed module code and given student ID.
 */
public class DeleteGradeCommand extends Command {
    public static final String COMMAND_WORD = "dgrade";

    public static final String MESSAGE_PERSON_NOTFOUND = "Can't find the person you specified.";
    public static final String MESSAGE_GRADE_NOTFOUND = "Can't find the grade for the course you specified.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a grade for a Student in the address book. "
        + "Parameters: " + PREFIX_ID + "ID " + PREFIX_MODULE + "MODULE "
        + "Example: " + COMMAND_WORD + " " + PREFIX_ID + "A0265901E " + PREFIX_MODULE + "CS2103T ";
    public static final String MESSAGE_DELETE_GRADE_SUCCESS = "Deleted %1$s grade for student %2$s.";

    private Id studentToDelGradeId;
    private final Module module;

    /**
     * Instantiate Delete Grade Command with target module.
     */
    public DeleteGradeCommand(Id studentToDelGradeId, Module module) {
        this.studentToDelGradeId = studentToDelGradeId;
        this.module = module;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredPersonList();
        Optional<Student> optionalStudentToDelGrade = lastShownList.stream().filter(
                student -> student.getId().equals(studentToDelGradeId)
        ).findFirst();
        if (optionalStudentToDelGrade.isEmpty()) {
            throw new CommandException(MESSAGE_PERSON_NOTFOUND);
        }
        Student studentToDelGrade = optionalStudentToDelGrade.get();

        Set<Grade> originalGrades = studentToDelGrade.getGrades();
        Set<Grade> editedGrades = new HashSet<>(originalGrades);
        Optional<Grade> optionalGradeToDel = editedGrades.stream().filter(
                grade -> grade.getModule().equals(module)
        ).findFirst();
        if (optionalGradeToDel.isEmpty()) {
            throw new CommandException(MESSAGE_GRADE_NOTFOUND);
        }
        optionalGradeToDel.ifPresent(existingGrade -> {
            editedGrades.remove(existingGrade);
        });

        Student editedGrade = new Student(studentToDelGrade.getId(), studentToDelGrade.getMajor(),
                studentToDelGrade.getIntake(), studentToDelGrade.getName(), studentToDelGrade.getPhone(),
                studentToDelGrade.getEmail(), studentToDelGrade.getAddress(), studentToDelGrade.getTags(),
                editedGrades);
        model.setPerson(studentToDelGrade, editedGrade);
        return new CommandResult(String.format(MESSAGE_DELETE_GRADE_SUCCESS, module, studentToDelGrade.getId()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteGradeCommand)) {
            return false;
        }

        DeleteGradeCommand otherDelGradeCommand = (DeleteGradeCommand) other;
        return Objects.equals(module, otherDelGradeCommand.module);
    }

    @Override
    public String toString() {
        ToStringBuilder deleteToStringBuilder = new ToStringBuilder(this);
        return deleteToStringBuilder.toString();
    }
}
