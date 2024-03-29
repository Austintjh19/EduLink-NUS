package seedu.edulink.testutil;

import seedu.edulink.model.grade.Course;
import seedu.edulink.model.grade.Grade;
import seedu.edulink.model.grade.Score;

/**
 * A utility class to help with building Grade objects.
 */
public class GradeBuilder {
    public static final String DEFAULT_COURSE = "CS2103T";
    public static final double DEFAULT_SCORE = 0;

    private Course course;
    private Score score;

    /**
     * Creates a {@code GradeBuilder} with the default details.
     */
    public GradeBuilder() {
        course = new Course(DEFAULT_COURSE);
        score = new Score(DEFAULT_SCORE);
    }

    /**
     * Initializes the GradeBuilder with the data of {@code gradeToCopy}.
     */
    public GradeBuilder(Grade gradeToCopy) {
        course = gradeToCopy.getCourse();
        score = gradeToCopy.getScore();
    }

    /**
     * Sets the {@code Course} of the {@code Grade} that we are building.
     */
    public GradeBuilder withCourse(String courseCode) {
        this.course = new Course(courseCode);
        return this;
    }

    /**
     * Sets the {@code Score} of the {@code Grade} that we are building.
     */
    public GradeBuilder withScore(double score) {
        this.score = new Score(score);
        return this;
    }

    public Grade build() {
        return new Grade(course, score);
    }

}
