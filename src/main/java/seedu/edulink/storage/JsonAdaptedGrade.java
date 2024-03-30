package seedu.edulink.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.edulink.commons.exceptions.IllegalValueException;
import seedu.edulink.model.grade.Course;
import seedu.edulink.model.grade.Grade;
import seedu.edulink.model.grade.Score;

/**
 * Jackson-friendly version of {@link Grade}.
 */
public class JsonAdaptedGrade {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Grade's %s field is missing!";

    private final String course;
    private final double score;

    /**
     * Constructs a {@code JsonAdaptedGrade} with the given grade details.
     */
    @JsonCreator
    public JsonAdaptedGrade(@JsonProperty("course") String course, @JsonProperty("score") double score) {
        this.course = course;
        this.score = score;
    }

    /**
     * Converts a given {@code Grade} into this class for Jackson use.
     */
    public JsonAdaptedGrade(Grade source) {
        this.course = source.getCourse().courseCode;
        this.score = source.getScore().score;
    }

    /**
     * Converts this Jackson-friendly adapted grade object into the model's {@code Grade} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted grade.
     */
    public Grade toModelType() throws IllegalValueException {
        if (course == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Course.class.getSimpleName()));
        }
        if (!Course.isValidCourseCode(course)) {
            throw new IllegalValueException(Course.MESSAGE_CONSTRAINTS);
        }
        final Course modelCourse = new Course(course);

        if (!Score.isValidScore(score)) {
            throw new IllegalValueException(Score.MESSAGE_CONSTRAINTS);
        }
        final Score modelScore = new Score(score);

        return new Grade(modelCourse, modelScore);
    }

    @JsonProperty("course")
    public String getCourse() {
        return course;
    }

    @JsonProperty("score")
    public double getScore() {
        return score;
    }
}
