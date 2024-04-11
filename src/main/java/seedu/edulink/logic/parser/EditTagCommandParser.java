package seedu.edulink.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.edulink.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.edulink.logic.commands.EditTagCommand;
import seedu.edulink.logic.parser.exceptions.ParseException;
import seedu.edulink.model.student.Id;
import seedu.edulink.model.tag.Tag;

/**
 * Parses input arguments and create a EditTagCommand object.
 */
public class EditTagCommandParser implements Parser<EditTagCommand> {

    /**
     * Parses the user's arguments to EditTagCommand object.
     *
     * @param args the user's argument.
     * @return the new EditTagCommand object.
     * @throws ParseException if the user's input doesn't conform the expected format.
     */
    public EditTagCommand parse(String args) throws ParseException {
        Id userId;
        Tag tagToEdit;
        Tag resultingTag;
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ID,
            PREFIX_TAG);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ID);
        if (!ParserUtil.isValidCommandFormat(argMultimap, args, PREFIX_TAG, PREFIX_ID)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagCommand.MESSAGE_USAGE));
        }
        try {
            String userIdString = argMultimap.getValue(PREFIX_ID).get();
            userId = new Id(userIdString);
            if (argMultimap.getAllValues(PREFIX_TAG).size() != 2) {
                throw new ParseException(EditTagCommand.MESSAGE_TWO_TAGS_NEED + EditTagCommand.MESSAGE_USAGE);
            }
            tagToEdit = ParserUtil.parseTag(argMultimap.getAllValues(PREFIX_TAG).get(0));
            resultingTag = ParserUtil.parseTag(argMultimap.getAllValues(PREFIX_TAG).get(1));
        } catch (IllegalArgumentException e) {
            throw new ParseException(Id.MESSAGE_CONSTRAINTS);
        }

        return new EditTagCommand(userId, tagToEdit, resultingTag);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
