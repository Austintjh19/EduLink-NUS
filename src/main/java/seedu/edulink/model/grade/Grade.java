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
    private final Course course;
    private final Score score;

    /**
     * Constructs a {@code Major}.
     *
     * @param course A valid Course.
     * @param score A valid Score.
     */
    public Grade(Course course, Score score) {
        requireAllNonNull(course, score);
        this.grade = GradeUtil.parseGrade(score);
        this.course = course;
        this.score = score;
    }

    public Course getCourse() {
        return this.course;
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
            && this.course.equals(otherGrade.course);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(grade, course, score);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("course", course)
            .add("score", score)
            .add("grade", grade)
            .toString();
    }
}
