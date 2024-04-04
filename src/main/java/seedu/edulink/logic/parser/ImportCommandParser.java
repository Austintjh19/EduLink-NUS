package seedu.edulink.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_FILENAME;

import seedu.edulink.logic.Messages;
import seedu.edulink.logic.commands.ImportCommand;
import seedu.edulink.logic.parser.exceptions.ParseException;
import seedu.edulink.storage.Storage;

/**
 * Parses input arguments and creates a new ExportCommand Object
 */
public class ImportCommandParser implements Parser<ImportCommand> {

    private Storage storage;

    public ImportCommandParser(Storage storage) {
        this.storage = storage;
    }

    public ImportCommandParser() {

    }

    @Override
    public ImportCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILENAME);

        if (argMultimap.getValue(PREFIX_FILENAME).isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                ImportCommand.MESSAGE_USAGE));
        }
        String filename = argMultimap.getValue(PREFIX_FILENAME).get();
        return new ImportCommand(ParserUtil.parseFilename(filename), storage);
    }
}
