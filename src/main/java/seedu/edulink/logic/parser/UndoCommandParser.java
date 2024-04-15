package seedu.edulink.logic.parser;

import seedu.edulink.logic.commands.UndoCommand;
import seedu.edulink.logic.parser.exceptions.ParseException;

/**
 * Parse input arguments and create a UndoCommand object.
 */
public class UndoCommandParser implements Parser<UndoCommand> {

    @Override
    public UndoCommand parse(String args) throws ParseException {
        return new UndoCommand();
    }
}
