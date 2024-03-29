package seedu.edulink.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RecentCommandsTest {
    private RecentCommands recentCommands;

    @BeforeEach
    void setUp() {
        recentCommands = new RecentCommands();
    }

    @Test
    void testConstructor() {
        assertTrue(recentCommands.getCommands().isEmpty());
    }

    @Test
    void testEquals() {
        RecentCommands other = new RecentCommands();
        assertTrue(recentCommands.equals(other));

        recentCommands.add("command1");
        assertFalse(recentCommands.equals(other));

        other.add("command1");
        assertTrue(recentCommands.equals(other));
    }

    @Test
    void testAdd() {
        recentCommands.add("command1");
        ObservableList<String> expected = FXCollections.observableArrayList("command1");
        assertEquals(expected, recentCommands.getCommands());

        recentCommands.add("command2");
        expected = FXCollections.observableArrayList("command2", "command1");
        assertEquals(expected, recentCommands.getCommands());
    }

    @Test
    void testAddWithLimit() {
        ObservableList<String> expected = FXCollections.observableArrayList(
            Arrays.asList("command5", "command4", "command3", "command2", "command1"));

        for (int i = 1; i <= RecentCommands.LIMIT; i++) {
            recentCommands.add("command" + i);
        }

        assertEquals(expected, recentCommands.getCommands());
    }
}
