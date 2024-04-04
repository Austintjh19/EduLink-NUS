package seedu.edulink.model.grade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_MODULE_CS2103T;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_SCORE_89;
import static seedu.edulink.testutil.TypicalPersons.ALICE_GRADE;
import static seedu.edulink.testutil.TypicalPersons.BOB_GRADE;

import org.junit.jupiter.api.Test;

import seedu.edulink.testutil.GradeBuilder;

public class GradeTest {

    @Test
    public void equals() {
        // same values -> returns true
        Grade gradeCopy = new GradeBuilder(ALICE_GRADE).build();
        assertTrue(ALICE_GRADE.equals(gradeCopy));

        // same object -> returns true
        assertTrue(ALICE_GRADE.equals(ALICE_GRADE));

        // null -> returns false
        assertFalse(ALICE_GRADE.equals(null));

        // different type -> returns false
        assertFalse(ALICE_GRADE.equals(5));

        // different grade -> returns false
        assertFalse(ALICE_GRADE.equals(BOB_GRADE));

        // different module -> returns false
        Grade editedGrade = new GradeBuilder(ALICE_GRADE).withModule(VALID_MODULE_CS2103T).build();
        assertFalse(ALICE_GRADE.equals(editedGrade));

        // different score -> returns false
        editedGrade = new GradeBuilder(ALICE_GRADE).withScore(VALID_SCORE_89).build();
        assertFalse(ALICE_GRADE.equals(editedGrade));
    }

    @Test
    public void hashCode_sameValues_returnsTrue() {
        Module module1 = new Module("MA1522");
        Score score1 = new Score(85);
        Grade grade1 = new Grade(module1, score1);

        Module module2 = new Module("MA1522");
        Score score2 = new Score(85);
        Grade grade2 = new Grade(module2, score2);

        assertTrue(grade1.hashCode() == grade2.hashCode());
    }

    @Test
    public void toStringMethod() {
        String expected = Grade.class.getCanonicalName() + "{module=" + ALICE_GRADE.getModule()
            + ", score=" + ALICE_GRADE.getScore() + ", grade=" + ALICE_GRADE.getGrade() + "}";
        assertEquals(expected, ALICE_GRADE.toString());
    }
}
