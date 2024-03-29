package seedu.edulink.storage;

import static seedu.edulink.testutil.TypicalPersons.BENSON_GRADE;

public class JsonAdaptedGradeTest {
    private static final String INVALID_COURSE = "CS111";
    private static final String INVALID_SCORE = "a";

    public static final String VALID_COURSE = BENSON_GRADE.getCourse().toString();
    public static final double VALID_SCORE = BENSON_GRADE.getScore().score;
}
