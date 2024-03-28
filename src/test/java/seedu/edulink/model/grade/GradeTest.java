package seedu.edulink.model.grade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GradeTest {
    public static final Course VALID_COURSE1 = new Course("MA1522");
    public static final Score VALID_SCORE1 = new Score(80);
    public static final Grade VALID_GRADE1 = new Grade(VALID_COURSE1, VALID_SCORE1);

    public static final Course VALID_COURSE2 = new Course("CS2040");
    public static final Score VALID_SCORE2 = new Score(60);
    public static final Grade VALID_GRADE2 = new Grade(VALID_COURSE2, VALID_SCORE2);

    @Test
    public void equals_sameValues_returnsTrue() {
        Grade grade = new Grade(new Course("MA1522"), new Score(80));
        assertTrue(VALID_GRADE1.equals(grade));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        assertTrue(VALID_GRADE1.equals(VALID_GRADE1));
    }

    @Test
    public void equals_nullValues_returnsFalse() {
        assertFalse(VALID_GRADE1.equals(null));
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        assertFalse(VALID_GRADE1.equals(VALID_GRADE2));
    }

    @Test
    public void equals_differentCourse_returnsFalse() {
        Course course = new Course("MA1522");
        Score score = new Score(85);
        Grade grade = new Grade(course, score);
        assertFalse(VALID_GRADE1.equals(grade));
    }

    @Test
    public void equals_differentScore_returnsFalse() {
        Course course = new Course("CS2040");
        Score score = new Score(45);
        Grade grade = new Grade(course, score);
        assertFalse(VALID_GRADE2.equals(grade));
    }

    @Test
    public void hashCode_sameValues_returnsTrue() {
        Course course1 = new Course("MA1522");
        Score score1 = new Score(85);
        Grade grade1 = new Grade(course1, score1);

        Course course2 = new Course("MA1522");
        Score score2 = new Score(85);
        Grade grade2 = new Grade(course2, score2);

        assertTrue(grade1.hashCode() == grade2.hashCode());
    }

    @Test
    public void toStringMethod() {
        String expected = Grade.class.getCanonicalName() + "{course=" + VALID_COURSE1
            + ", score=" + VALID_SCORE1 + ", grade=" + VALID_GRADE1.getGrade() + "}";
        assertEquals(expected, VALID_GRADE1.toString());
    }
}
