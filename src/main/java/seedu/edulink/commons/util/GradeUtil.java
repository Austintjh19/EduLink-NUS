package seedu.edulink.commons.util;

import java.util.function.Predicate;

import seedu.edulink.model.grade.Grades;
import seedu.edulink.model.grade.Score;

/**
 * Helper function for parsing scores into grades based on predefined grade intervals.
 */
public class GradeUtil {

    // Define predicates for different grade intervals
    private static final Predicate<Double> isGradeA = score -> score >= 90 && score <= 100;
    private static final Predicate<Double> isGradeB = score -> score >= 80 && score < 90;
    private static final Predicate<Double> isGradeC = score -> score >= 70 && score < 80;
    private static final Predicate<Double> isGradeD = score -> score >= 60 && score < 70;
    private static final Predicate<Double> isGradeF = score -> score >= 0 && score < 60;

    /**
     * Parses a given score into a corresponding grade.
     *
     * @param score The score to parse.
     * @return The grade corresponding to the score.
     */
    public static Grades parseGrade(Score score) {
        double scoreValue = score.score;

        // Check the score against the predicates and return the corresponding grade
        if (isGradeA.test(scoreValue)) {
            return Grades.A;
        } else if (isGradeB.test(scoreValue)) {
            return Grades.B;
        } else if (isGradeC.test(scoreValue)) {
            return Grades.C;
        } else if (isGradeD.test(scoreValue)) {
            return Grades.D;
        } else if (isGradeF.test(scoreValue)) {
            return Grades.F;
        } else {
            return Grades.N;
        }
    }
}
