package seedu.edulink.model;

import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Keeps track of the Recent Commands
 * provided by Users
 */
public class RecentCommands {
    public static final int LIMIT = 5;
    private final ObservableList<String> commands;
    private int counter;

    /**
     * Initializes RecentCommands with initially empty list of commands
     * and a counter to keep track of no. of Commands Stored
     */
    public RecentCommands() {
        this.commands = FXCollections.observableArrayList();
        this.counter = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof RecentCommands)) {
            return false;
        } else {
            RecentCommands other = (RecentCommands) obj;
            return this.commands.equals(other.commands);
        }
    }


    /**
     * Adds a command to the List of Recent Commands
     *
     * @param command Command to be added in the List
     */
    public void add(String command) {
        requireNonNull(command);
        if (counter == LIMIT) {
            removeOldestCommand();
            commands.add(0, command);
        } else {
            commands.add(0, command);
            counter++;
        }
    }

    private void removeOldestCommand() {
        commands.remove(commands.size() - 1);
    }

    public ObservableList<String> getCommands() {
        return commands;
    }
}


