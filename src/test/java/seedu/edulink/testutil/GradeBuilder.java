package seedu.edulink.testutil;

import seedu.edulink.model.grade.Grade;
import seedu.edulink.model.grade.Module;
import seedu.edulink.model.grade.Score;

/**
 * A utility class to help with building Grade objects.
 */
public class GradeBuilder {
    public static final String DEFAULT_MODULE = "CS2103T";
    public static final double DEFAULT_SCORE = 0;

    private Module module;
    private Score score;

    /**
     * Creates a {@code GradeBuilder} with the default details.
     */
    public GradeBuilder() {
        module = new Module(DEFAULT_MODULE);
        score = new Score(DEFAULT_SCORE);
    }

    /**
     * Initializes the GradeBuilder with the data of {@code gradeToCopy}.
     */
    public GradeBuilder(Grade aliceGrade) {
        module = aliceGrade.getModule();
        score = aliceGrade.getScore();
    }

    /**
     * Sets the {@code module} of the {@code Grade} that we are building.
     */
    public GradeBuilder withModule(String moduleCode) {
        this.module = new Module(moduleCode);
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
        return new Grade(module, score);
    }

}
