package seedu.edulink.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.edulink.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.edulink.model.grade.LetterGrades;
import seedu.edulink.model.grade.Score;

public class GradeUtilTest {
    @Test
    public void testGradeA() {
        // Test lower boundary for Grade A
        assertEquals(LetterGrades.A, GradeUtil.parseGrade(new Score(85)));

        // Test middle value for Grade A
        assertEquals(LetterGrades.A, GradeUtil.parseGrade(new Score(92.5)));

        // Test upper boundary for Grade A
        assertEquals(LetterGrades.A, GradeUtil.parseGrade(new Score(100)));

        // Test just below lower boundary for Grade A
        assertEquals(LetterGrades.B, GradeUtil.parseGrade(new Score(84.99)));

        // Test just above upper boundary for Grade A
        assertThrows(IllegalArgumentException.class, () -> GradeUtil.parseGrade(new Score(100.01)));
    }

    @Test
    public void testGradeB() {
        // Test lower boundary for Grade B
        assertEquals(LetterGrades.B, GradeUtil.parseGrade(new Score(70)));

        // Test middle value for Grade B
        assertEquals(LetterGrades.B, GradeUtil.parseGrade(new Score(77.5)));

        // Test upper boundary for Grade B
        assertEquals(LetterGrades.B, GradeUtil.parseGrade(new Score(84.99)));

        // Test just below lower boundary for Grade B
        assertEquals(LetterGrades.C, GradeUtil.parseGrade(new Score(69.99)));

        // Test just above upper boundary for Grade B
        assertEquals(LetterGrades.A, GradeUtil.parseGrade(new Score(85)));
    }

    @Test
    public void testGradeC() {
        // Test lower boundary for Grade C
        assertEquals(LetterGrades.C, GradeUtil.parseGrade(new Score(60)));

        // Test middle value for Grade C
        assertEquals(LetterGrades.C, GradeUtil.parseGrade(new Score(65)));

        // Test upper boundary for Grade C
        assertEquals(LetterGrades.C, GradeUtil.parseGrade(new Score(69.99)));

        // Test just below lower boundary for Grade C
        assertEquals(LetterGrades.D, GradeUtil.parseGrade(new Score(59.99)));

        // Test just above upper boundary for Grade C
        assertEquals(LetterGrades.B, GradeUtil.parseGrade(new Score(70)));
    }

    @Test
    public void testGradeD() {
        // Test lower boundary for Grade D
        assertEquals(LetterGrades.D, GradeUtil.parseGrade(new Score(50)));

        // Test middle value for Grade D
        assertEquals(LetterGrades.D, GradeUtil.parseGrade(new Score(55)));

        // Test upper boundary for Grade D
        assertEquals(LetterGrades.D, GradeUtil.parseGrade(new Score(59.99)));

        // Test just below lower boundary for Grade D
        assertEquals(LetterGrades.F, GradeUtil.parseGrade(new Score(49.99)));

        // Test just above upper boundary for Grade D
        assertEquals(LetterGrades.C, GradeUtil.parseGrade(new Score(60)));
    }

    @Test
    public void testGradeF() {
        // Test lower boundary for Grade F
        assertEquals(LetterGrades.F, GradeUtil.parseGrade(new Score(0.01)));

        // Test middle value for Grade F
        assertEquals(LetterGrades.F, GradeUtil.parseGrade(new Score(25)));

        // Test upper boundary for Grade F
        assertEquals(LetterGrades.F, GradeUtil.parseGrade(new Score(49.99)));

        // Test just below lower boundary for Grade F
        assertEquals(LetterGrades.N, GradeUtil.parseGrade(new Score(0)));

        // Test just above upper boundary for Grade F
        assertEquals(LetterGrades.D, GradeUtil.parseGrade(new Score(50)));
    }

    @Test
    public void testGradeN() {
        // Test score outside grade range for Grade N
        assertThrows(IllegalArgumentException.class, () -> GradeUtil.parseGrade(new Score(-0.0)));
        assertThrows(IllegalArgumentException.class, () -> GradeUtil.parseGrade(new Score(-10)));
        assertThrows(IllegalArgumentException.class, () -> GradeUtil.parseGrade(new Score(105)));
    }
}
