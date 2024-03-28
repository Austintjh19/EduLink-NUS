package seedu.edulink.model.grade;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CourseTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Course(null));
    }

    @Test
    public void isValidCourseCode_null_returnsFalse() {
        assertFalse(Course.isValidCourseCode(null));
    }

    @Test
    public void isValidCourseCode_invalidFormats_returnsFalse() {
        assertFalse(Course.isValidCourseCode("CS101")); // Invalid digit format
        assertFalse(Course.isValidCourseCode("CS10101")); // More than 4 digits
        assertFalse(Course.isValidCourseCode("CS")); // Insufficient characters
        assertFalse(Course.isValidCourseCode("CSA101")); // More than 2 letters at the start
        assertFalse(Course.isValidCourseCode("1234AB")); // Starts with digits
        assertFalse(Course.isValidCourseCode("ABCEDF")); // All alphabets
    }

    @Test
    public void isValidCourseCode_validFormats_returnsTrue() {
        assertTrue(Course.isValidCourseCode("CS1101")); // Standard format
        assertTrue(Course.isValidCourseCode("MA1522A")); // With optional letter at the end
        assertTrue(Course.isValidCourseCode("ma1522")); // With lowercase letters
    }

    @Test
    public void stringTest() {
        Course course = new Course("MA1522");
        assertTrue(course.toString().equals("MA1522"));
        assertFalse(course.toString().equals("MA1521"));
    }

    @Test
    public void equals() {
        Course course1 = new Course("CS1010");
        Course course2 = new Course("MA1522");

        // same values -> returns true
        assertTrue(course1.equals(new Course("CS1010")));

        // same object -> returns true
        assertTrue(course1.equals(course1));

        // null -> returns false
        assertFalse(course1.equals(null));

        // different values -> returns false
        assertFalse(course1.equals(course2));
    }

    @Test
    public void hashCode_sameValues_returnsSameHashCode() {
        Course course1 = new Course("MA1522");
        Course course2 = new Course("ma1522");
        assertTrue(course1.hashCode() == course2.hashCode());
    }

    @Test
    public void hashCode_differentValues_returnsDifferentHashCode() {
        Course course1 = new Course("CS3230");
        Course course2 = new Course("MA1522");
        assertFalse(course1.hashCode() == course2.hashCode());
    }
}
