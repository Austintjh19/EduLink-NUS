package seedu.edulink.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;


public class RecentCommands {
    private final String[] commands;
    public static final int LIMIT = 5;
    private int counter;

    public RecentCommands () {
        this.commands = new String[LIMIT];
        this.counter = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        } else if (!(obj instanceof RecentCommands)) {
            return false;
        } else{
            RecentCommands other = (RecentCommands) obj;
            return Arrays.equals(this.commands, other.commands);
        }
    }

    public void add(String command) {
        requireNonNull(command);
        if (counter == LIMIT) {
            for(int i = 0; i < counter - 1; i++){
                commands[i] = commands[i + 1];
            }
            commands[LIMIT - 1] = command;
        } else {
            commands[counter] = command;
            counter++;
        }
    }

    public ArrayList<String> getCommands() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = counter - 1; i >= 0; i--) {
            result.add(commands[i]);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < counter; i++){
            if(i == counter - 1){
                sb.append(commands[i]);
            } else {
                sb.append(commands[i]).append(" ");
            }
        }
        return sb.toString();
    }
}
