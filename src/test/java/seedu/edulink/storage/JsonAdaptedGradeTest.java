package seedu.edulink.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.edulink.storage.JsonAdaptedGrade.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.edulink.testutil.Assert.assertThrows;
import static seedu.edulink.testutil.TypicalPersons.BENSON_GRADE;

import org.junit.jupiter.api.Test;

import seedu.edulink.commons.exceptions.IllegalValueException;
import seedu.edulink.model.grade.Module;
import seedu.edulink.model.grade.Score;

public class JsonAdaptedGradeTest {
    private static final String INVALID_MODULE = "CS100";
    private static final String INVALID_SCORE_1 = "120";
    private static final String INVALID_SCORE_2 = "-10";
    private static final String INVALID_SCORE_3 = "-0";

    private static final String VALID_MODULE = BENSON_GRADE.getModule().toString();
    private static final String VALID_SCORE = BENSON_GRADE.getScore().toString();

    @Test
    public void toModelType_validGradeDetails_returnsGrade() throws Exception {
        JsonAdaptedGrade grade = new JsonAdaptedGrade(BENSON_GRADE);
        assertEquals(BENSON_GRADE, grade.toModelType());
    }

    @Test
    public void toModelType_invalidModule_throwsIllegalValueException() {
        JsonAdaptedGrade grade =
            new JsonAdaptedGrade(INVALID_MODULE, Double.parseDouble(VALID_SCORE));
        String expectedMessage = Module.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, grade::toModelType);
    }

    @Test
    public void toModelType_invalidScoreMoreThanMax_throwsIllegalValueException() {
        JsonAdaptedGrade grade =
            new JsonAdaptedGrade(VALID_MODULE, Double.parseDouble(INVALID_SCORE_1));
        String expectedMessage = Score.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, grade::toModelType);
    }

    @Test
    public void toModelType_invalidScoreLessThanMin_throwsIllegalValueException() {
        // more than maximum value
        JsonAdaptedGrade grade =
            new JsonAdaptedGrade(VALID_MODULE, Double.parseDouble(INVALID_SCORE_2));
        String expectedMessage = Score.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, grade::toModelType);
    }

    @Test
    public void toModelType_invalidScoreNegativeZero_throwsIllegalValueException() {
        // more than maximum value
        JsonAdaptedGrade grade =
            new JsonAdaptedGrade(VALID_MODULE, Double.parseDouble(INVALID_SCORE_3));
        String expectedMessage = Score.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, grade::toModelType);
    }

    @Test
    public void toModelType_nullModule_throwsIllegalValueException() {
        JsonAdaptedGrade grade = new JsonAdaptedGrade(null, Double.parseDouble(VALID_SCORE));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Module.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, grade::toModelType);
    }

    @Test
    public void getModule_validInput_correctModule() throws Exception {
        JsonAdaptedGrade grade =
            new JsonAdaptedGrade(VALID_MODULE, Double.parseDouble(VALID_SCORE));
        assertEquals("CS2103T", grade.getModule());
    }

    @Test
    public void getScore_validInput_correctScore() throws Exception {
        JsonAdaptedGrade grade =
            new JsonAdaptedGrade(VALID_MODULE, Double.parseDouble(VALID_SCORE));
        assertEquals(70.0, grade.getScore());
    }
}
