package seedu.edulink.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.edulink.testutil.TypicalPersons.BENSON;
import static seedu.edulink.testutil.TypicalPersons.BENSON_GRADE;
import static seedu.edulink.storage.JsonAdaptedGrade.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.edulink.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.edulink.commons.exceptions.IllegalValueException;
import seedu.edulink.model.grade.Course;
import seedu.edulink.model.grade.Score;
import seedu.edulink.model.student.Id;
import seedu.edulink.model.student.Name;

public class JsonAdaptedGradeTest {
    public static final String VALID_COURSE = BENSON_GRADE.getCourse().toString();
    public static final double VALID_SCORE = BENSON_GRADE.getScore().score;

    private static final String INVALID_COURSE = "CS111";
    private static final double INVALID_SCORE = 120;

    @Test
    public void toModelType_validGradeDetails_returnsGrade() throws Exception {
        JsonAdaptedGrade grade = new JsonAdaptedGrade(BENSON.getGrade());
        assertEquals(BENSON.getGrade(), grade.toModelType());
    }

    @Test
    public void toModelType_invalidCourse_throwsIllegalValueException() {
        JsonAdaptedGrade grade =
            new JsonAdaptedGrade(INVALID_COURSE, VALID_SCORE);
        String expectedMessage = Course.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, grade::toModelType);
    }

    @Test
    public void toModelType_invalidScore_throwsIllegalValueException() {
        JsonAdaptedGrade grade =
            new JsonAdaptedGrade(VALID_COURSE, INVALID_SCORE);
        String expectedMessage = Score.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, grade::toModelType);
    }

    @Test
    public void toModelType_nullCourse_throwsIllegalValueException() {
        JsonAdaptedGrade grade = new JsonAdaptedGrade(null, VALID_SCORE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Course.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, grade::toModelType);
    }
}
