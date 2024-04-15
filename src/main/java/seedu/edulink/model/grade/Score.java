package seedu.edulink.model.grade;

import static java.util.Objects.requireNonNull;
import static seedu.edulink.commons.util.AppUtil.checkArgument;

import java.text.DecimalFormat;

/**
 * Represents a Student's Score in the Application.
 * Guarantees: immutable; is valid as declared in {@link #isValidScore(double)}
 */
public class Score {
    public static final String MESSAGE_CONSTRAINTS =
        "Score should be a non-negative number between 0 and 100 inclusive. "
        + "Give up to 2 decimal places. Can't be left blank.";
    public static final double MIN_SCORE = 0;
    public static final double MAX_SCORE = 100;

    public final double score;

    /**
     * Constructs a {@code Score}.
     *
     * @param score A valid score.
     */
    public Score(double score) {
        requireNonNull(score);
        checkArgument(isValidScore(score), MESSAGE_CONSTRAINTS);
        this.score = score;
    }

    /**
     * Returns true if a given double is a valid score.
     */
    public static boolean isValidScore(double test) {
        return test >= MIN_SCORE && test <= MAX_SCORE
            && !isNegativeZero(test);
    }

    /**
     * Returns true if a given score = -0. -0.0, -0.00.
     */
    private static boolean isNegativeZero(double test) {
        return Double.doubleToRawLongBits(test) == Double.doubleToRawLongBits(-0.0);
    }

    public double getScore() {
        return this.score;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(score);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Score)) {
            return false;
        }

        Score otherScore = (Score) other;
        return Double.compare(this.score, otherScore.score) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(score);
    }
}
