package seedu.edulink.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.edulink.testutil.Assert.assertThrows;

import java.util.ArrayList;

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
    public void add_null_command_throwsException() {
        assertThrows(NullPointerException.class, () -> recentCommands.add(null));
    }

    @Test
    public void commandsReturns_recent() {
        ArrayList<String> acutal =  new ArrayList<>();
        acutal.add("list");
        acutal.add("delete 1");
        assertEquals(acutal, recentCommands.getCommands());
    }

    @Test
    public void equals_test() {
        RecentCommands incorrect = new RecentCommands();
        incorrect.add("delete 2");
        incorrect.add("list");
        RecentCommands correct= new RecentCommands();
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
}
