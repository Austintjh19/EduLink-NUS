package seedu.edulink.model;

import static java.util.Objects.requireNonNull;

public class RecentCommand {
    private final String command;

    public RecentCommand (String command){
        requireNonNull(command);
        this.command = command;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        } else if (!(obj instanceof RecentCommand)) {
            return false;
        } else{
            RecentCommand other = (RecentCommand) obj;
            return this.command.equalsIgnoreCase(other.command);
        }
    }

    @Override
    public String toString() {
        return command;
    }
}
