package seedu.edulink.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.edulink.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.stream.Stream;

import seedu.edulink.logic.commands.DeleteGradeCommand;
import seedu.edulink.logic.parser.exceptions.ParseException;
import seedu.edulink.model.grade.Module;
import seedu.edulink.model.student.Id;


/**
 * Parse input arguments and create a DeleteGradeCommand object.
 */
public class DeleteGradeCommandParser implements Parser<DeleteGradeCommand> {

    /**
     * Parses the user's arguments to DeleteGradeCommand object.
     *
     * @param args the user's argument.
     * @return the new DeleteGradeCommand object.
     * @throws ParseException if the user's input doesn't conform the expected format.
     */
    public DeleteGradeCommand parse(String args) throws ParseException {
        Id userId;
        Module module;
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_MODULE);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_MODULE)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGradeCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ID, PREFIX_MODULE);
        try {
            String userIdString = argMultimap.getValue(PREFIX_ID).get();
            userId = new Id(userIdString);
            module = ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE).get());
        } catch (IllegalArgumentException e) {
            throw new ParseException(Id.MESSAGE_CONSTRAINTS);
        }

        return new DeleteGradeCommand(userId, module);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
