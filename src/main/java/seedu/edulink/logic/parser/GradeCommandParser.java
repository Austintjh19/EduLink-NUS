package seedu.edulink.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.edulink.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_SCORE;

import java.util.stream.Stream;

import seedu.edulink.logic.commands.GradeCommand;
import seedu.edulink.logic.parser.exceptions.ParseException;
import seedu.edulink.model.grade.Grade;
import seedu.edulink.model.grade.Module;
import seedu.edulink.model.grade.Score;
import seedu.edulink.model.student.Id;


/**
 * Parse input arguments and create a GradeCommand object.
 */
public class GradeCommandParser implements Parser<GradeCommand> {

    /**
     * Parses the user's arguments to GradeCommand object.
     *
     * @param args the user's argument.
     * @return the new GradeCommand object.
     * @throws ParseException if the user's input doesn't conform the expected format.
     */
    public GradeCommand parse(String args) throws ParseException {
        Id userId;
        Grade grade;
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_MODULE, PREFIX_SCORE);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_MODULE, PREFIX_SCORE)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ID, PREFIX_MODULE, PREFIX_SCORE);
        try {
            String userIdString = argMultimap.getValue(PREFIX_ID).get();
            userId = new Id(userIdString);
            Module module = ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE).get());
            Score score = ParserUtil.parseScore(argMultimap.getValue(PREFIX_SCORE).get());
            grade = new Grade(module, score);
        } catch (IllegalArgumentException e) {
            throw new ParseException(Id.MESSAGE_CONSTRAINTS);
        }

        return new GradeCommand(userId, grade);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
