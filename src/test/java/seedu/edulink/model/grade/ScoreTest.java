package seedu.edulink.model.grade;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ScoreTest {
    @Test
    public void constructor_validScore_returnTrue() {
        assertTrue(new Score(80).getScore() == Double.valueOf(80));
    }

    @Test
    public void constructor_invalidScore_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Score(-5));
        assertThrows(IllegalArgumentException.class, () -> new Score(105));
    }

    @Test
    public void isValidScore_validScores_returnTrue() {
        // valid score
        assertTrue(Score.isValidScore(0));
        assertTrue(Score.isValidScore(50));
        assertTrue(Score.isValidScore(100));

        // invalid score
        assertFalse(Score.isValidScore(-5));
        assertFalse(Score.isValidScore(105));
        assertFalse(Score.isValidScore(-100));
    }

    @Test
    public void toString_returnsCorrectString() {
        Score score = new Score(75);
        assertTrue(score.toString().equals("75"));
    }

    @Test
    public void equals_sameValues_returnTrue() {
        Score score1 = new Score(80);
        Score score2 = new Score(80);

        assertTrue(score1.equals(score2));
    }

    @Test
    public void equals() {
        Score score1 = new Score(70);
        Score score2 = new Score(70);
        Score score3 = new Score(90);

        // same values -> returns true
        assertTrue(score1.equals(score2));

        // same object -> returns true
        assertTrue(score1.equals(score1));

        // null -> returns false
        assertFalse(score1.equals(null));

        // different values -> returns false
        assertFalse(score1.equals(score3));
    }

    @Test
    public void hashCode_sameValues_returnTrue() {
        Score score1 = new Score(75);
        Score score2 = new Score(75);

        assertTrue(score1.hashCode() == score2.hashCode());
    }

    @Test
    public void hashCode_differentValues_returnFalse() {
        Score score1 = new Score(75);
        Score score2 = new Score(80);

        assertFalse(score1.hashCode() == score2.hashCode());
    }
}
