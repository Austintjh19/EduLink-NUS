package seedu.edulink.logic.parser;

import static seedu.edulink.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edulink.logic.parser.CliSyntax.*;

import seedu.edulink.commons.util.AppUtil;
import seedu.edulink.logic.commands.AddCommand;
import seedu.edulink.logic.commands.FindCommand;
import seedu.edulink.logic.parser.exceptions.ParseException;
import seedu.edulink.model.student.IdAndNameContainsQueryIdAndNamePredicate;
import seedu.edulink.model.student.IdContainsQueryIdPredicate;
import seedu.edulink.model.student.NameContainsQueryNamePredicate;

import java.util.stream.Stream;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    public static final String QUERY_ID_VALIDATION_REGEX = "\\p{Alnum}+";

    public static final String QUERY_NAME_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public static final String QUERY_ID_MESSAGE_CONSTRAINTS =
            "Query ID should only contain alphanumeric characters with no spaces, and it should not be blank.";

    public static final String QUERY_NAME_MESSAGE_CONSTRAINTS =
            "Query Names should only contain alphanumeric characters and spaces, and it should not be blank.";

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ID);

        if (argMultimap.getValue(PREFIX_ID).isPresent() && argMultimap.getValue(PREFIX_NAME).isPresent()) {
            return parseIdAndName(argMultimap);
        }

        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            return parseId(argMultimap);
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            return parseName(argMultimap);
        }

        throw new ParseException(
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    private FindCommand parseIdAndName(ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ID) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String queryId = argMultimap.getValue(PREFIX_ID).get().trim();
        String queryName = argMultimap.getValue(PREFIX_NAME).get().trim();

        if (!queryId.matches(QUERY_ID_VALIDATION_REGEX)) {
            throw new ParseException(QUERY_ID_MESSAGE_CONSTRAINTS);
        }

        if (!queryName.matches(QUERY_NAME_VALIDATION_REGEX)) {
            throw new ParseException(QUERY_NAME_MESSAGE_CONSTRAINTS);
        }

        return new FindCommand(new IdAndNameContainsQueryIdAndNamePredicate(queryId, queryName));
    }

    private FindCommand parseId(ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_ID) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String queryId = argMultimap.getValue(PREFIX_ID).get().trim();

        if (!queryId.matches(QUERY_ID_VALIDATION_REGEX)) {
            throw new ParseException(QUERY_ID_MESSAGE_CONSTRAINTS);
        }

        return new FindCommand(new IdContainsQueryIdPredicate(queryId));
    }

    private FindCommand parseName(ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String queryName = argMultimap.getValue(PREFIX_NAME).get().trim();

        if (!queryName.matches(QUERY_NAME_VALIDATION_REGEX)) {
            throw new ParseException(QUERY_NAME_MESSAGE_CONSTRAINTS);
        }

        return new FindCommand(new NameContainsQueryNamePredicate(queryName));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
