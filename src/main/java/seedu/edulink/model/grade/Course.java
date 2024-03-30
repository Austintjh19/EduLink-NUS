package seedu.edulink.model.grade;

import static java.util.Objects.requireNonNull;
import static seedu.edulink.commons.util.AppUtil.checkArgument;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents a Course in the Application.
 * Guarantees: immutable; is valid as declared in {@link #isValidCourseCode(String)}
 */
public class Course {
    public static final String MESSAGE_CONSTRAINTS =
        "Course code should be in the format LLDDDD[L] where L represents a letter and D represents a digit. "
                + "\n[L] represents optional letter at the end of the code.";
    private static final Pattern COURSE_CODE_PATTERN = Pattern.compile("[a-zA-Z]{2}\\d{4}[a-zA-Z]?");

    public final String courseCode;

    /**
     * Constructs a {@code Course}.
     *
     * @param courseCode A valid course code.
     */
    public Course(String courseCode) {
        requireNonNull(courseCode);
        checkArgument(isValidCourseCode(courseCode), MESSAGE_CONSTRAINTS);
        this.courseCode = parseCourse(courseCode);
    }

    /**
     * Returns true if a given string is a valid course code.
     */
    public static boolean isValidCourseCode(String test) {
        return test != null && COURSE_CODE_PATTERN.matcher(test).matches();
    }

    public String parseCourse(String courseCode) {
        return courseCode.toUpperCase();
    }

    @Override
    public String toString() {
        return courseCode;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Course)) {
            return false;
        }

        Course otherCourse = (Course) other;
        return courseCode.equals(otherCourse.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode);
    }
}
