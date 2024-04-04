package seedu.edulink.model.grade;

import static seedu.edulink.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.edulink.commons.util.GradeUtil;
import seedu.edulink.commons.util.ToStringBuilder;

/**
 * Represents a Student's Grade in the Application.
 * Guarantees: immutable; is valid as declared in {@link #isValidGrade(String)}
 */
public class Grade {

    private final Grades grade;
    private final Module module;
    private final Score score;

    /**
     * Constructs a {@code Major}.
     *
     * @param module A valid module.
     * @param score A valid Score.
     */
    public Grade(Module module, Score score) {
        requireAllNonNull(module, score);
        this.grade = GradeUtil.parseGrade(score);
        this.module = module;
        this.score = score;
    }

    public Module getModule() {
        return this.module;
    }

    public Score getScore() {
        return this.score;
    }

    public Grades getGrade() {
        return this.grade;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Grade)) {
            return false;
        }

        Grade otherGrade = (Grade) other;
        return this.grade.equals(otherGrade.grade)
            && this.score.equals(otherGrade.score)
            && this.module.equals(otherGrade.module);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(grade, module, score);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("module", module)
            .add("score", score)
            .add("grade", grade)
            .toString();
    }
}
