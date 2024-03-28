package seedu.edulink.commons.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.edulink.model.grade.Grades;
import seedu.edulink.model.grade.Score;

public class GradeUtilTest {
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
    @Test
    public void parseGrade_gradeA_returnsTrue() {
        Score score = new Score(95);
        assertTrue(GradeUtil.parseGrade(score).equals(Grades.A));
    }

    @Test
    public void parseGrade_gradeB_returnsTrue() {
        Score score = new Score(85);
        assertTrue(GradeUtil.parseGrade(score).equals(Grades.B));
    }

    @Test
    public void parseGrade_gradeC_returnsTrue() {
        Score score = new Score(75);
        assertTrue(GradeUtil.parseGrade(score).equals(Grades.C));
    }

    @Test
    public void parseGrade_gradeD_returnsTrue() {
        Score score = new Score(65);
        assertTrue(GradeUtil.parseGrade(score).equals(Grades.D));
    }

    @Test
    public void parseGrade_gradeF_returnsTrue() {
        Score score = new Score(55);
        assertTrue(GradeUtil.parseGrade(score).equals(Grades.F));
    }
}
