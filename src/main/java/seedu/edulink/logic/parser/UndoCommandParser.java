package seedu.edulink.logic.parser;

import seedu.edulink.logic.Messages;
import seedu.edulink.logic.commands.UndoCommand;
import seedu.edulink.logic.parser.exceptions.ParseException;

/**
 * Parse input arguments and create a UndoCommand object.
 */
public class UndoCommandParser implements Parser<UndoCommand> {

    @Override
    public UndoCommand parse(String args) throws ParseException {
        if (args.trim().isEmpty()) {
            return new UndoCommand();
        } else {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE));
        }
    }
}
