package seedu.edulink.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edulink.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RecentCommandsTest {
    private static RecentCommands recentCommands = new RecentCommands();

    @BeforeAll
    public static void setup() {
        recentCommands.add("delete 1");
        recentCommands.add("list");
    }

    @Test
    public void commandsReturns_recent() {
        ArrayList<String> actual = new ArrayList<>();
        actual.add("list");
        actual.add("delete 1");
        assertEquals(actual, recentCommands.getCommands());
    }

    @Test
    public void equals_test() {
        RecentCommands incorrect = new RecentCommands();
        incorrect.add("delete 2");
        incorrect.add("list");
        RecentCommands correct = new RecentCommands();
        correct.add("delete 1");
        correct.add("list");
        assertNotEquals(recentCommands, incorrect);
        assertEquals(recentCommands, correct);
    }

    @Test
    public void getCommands_success() {
        ArrayList<String> actual = new ArrayList<>();
        actual.add("list");
        actual.add("delete 1");
        assertEquals(actual, recentCommands.getCommands());
    }

    @Test
    public void toString_test() {
        assertNotEquals("tag i/A0265901E", recentCommands.toString());
        assertEquals("delete 1 list", recentCommands.toString());
    }

    @Test
    public void constructor_noArgs_initializesEmptyArray() {
        RecentCommands recentCommands = new RecentCommands();
        assertEquals(0, recentCommands.getCommands().size());
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        RecentCommands recentCommands = new RecentCommands();
        assertTrue(recentCommands.equals(recentCommands));
    }

    @Test
    public void equals_differentClass_returnsFalse() {
        RecentCommands recentCommands = new RecentCommands();
        assertFalse(recentCommands.equals(new Object()));
    }

    @Test
    public void equals_sameCommands_returnsTrue() {
        RecentCommands recentCommands1 = new RecentCommands();
        RecentCommands recentCommands2 = new RecentCommands();
        recentCommands1.add("command1");
        recentCommands2.add("command1");
        assertTrue(recentCommands1.equals(recentCommands2));
    }

    @Test
    public void add_nullCommand_throwsNullPointerException() {
        RecentCommands recentCommands = new RecentCommands();
        assertThrows(NullPointerException.class, () -> recentCommands.add(null));
    }

    @Test
    public void add_validCommands_addsCorrectly() {
        RecentCommands recentCommands = new RecentCommands();
        recentCommands.add("command1");
        recentCommands.add("command2");
        recentCommands.add("command3");
        ArrayList<String> expectedCommands = new ArrayList<>(Arrays.asList("command3", "command2", "command1"));
        assertEquals(expectedCommands, recentCommands.getCommands());
    }

    @Test
    public void add_exceedsLimit_removesOldestCommand() {
        RecentCommands recentCommands = new RecentCommands();
        recentCommands.add("command1");
        recentCommands.add("command2");
        recentCommands.add("command3");
        recentCommands.add("command4");
        recentCommands.add("command5");
        recentCommands.add("command6");
        ArrayList<String> expectedCommands = new ArrayList<>(
            Arrays.asList("command6", "command5", "command4", "command3", "command2"));
        assertEquals(expectedCommands, recentCommands.getCommands());
    }

    @Test
    public void toString_noCommands_returnsEmptyString() {
        RecentCommands recentCommands = new RecentCommands();
        assertEquals("", recentCommands.toString());
    }

    @Test
    public void toString_withCommands_returnsCorrectString() {
        RecentCommands recentCommands = new RecentCommands();
        recentCommands.add("command1");
        recentCommands.add("command2");
        recentCommands.add("command3");
        assertEquals("command1 command2 command3", recentCommands.toString());
    }
}
