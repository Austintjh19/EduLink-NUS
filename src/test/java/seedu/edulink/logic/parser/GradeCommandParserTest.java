package seedu.edulink.logic.parser;

import static seedu.edulink.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edulink.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.edulink.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.edulink.logic.commands.CommandTestUtil.MODULE_DESC_CS2103T;
import static seedu.edulink.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.edulink.logic.commands.CommandTestUtil.SCORE_DESC_89;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_MODULE_CS2103T;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_SCORE_89;
import static seedu.edulink.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.edulink.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.edulink.logic.commands.GradeCommand;
import seedu.edulink.model.grade.Grade;
import seedu.edulink.model.grade.Module;
import seedu.edulink.model.grade.Score;
import seedu.edulink.model.student.Id;

public class GradeCommandParserTest {

    private GradeCommandParser parser = new GradeCommandParser();

    @Test
    public void parse_validIdValidGrade_returnsTagCommand() {
        Id validId = new Id(VALID_ID_AMY);
        Grade validGrade = new Grade(new Module(VALID_MODULE_CS2103T),
            new Score(VALID_SCORE_89));
        assertParseSuccess(parser, ID_DESC_AMY + MODULE_DESC_CS2103T + SCORE_DESC_89,
            new GradeCommand(validId, validGrade));
    }

    @Test
    public void parse_invalidId_throwsParseException() {
        assertParseFailure(parser, INVALID_ID_DESC + MODULE_DESC_CS2103T + SCORE_DESC_89,
            Id.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidPrefix_failure() {
        // wrong prefix
        assertParseFailure(parser, ID_DESC_AMY + NAME_DESC_AMY + MODULE_DESC_CS2103T,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE));

        // missing prefix
        assertParseFailure(parser, ID_DESC_AMY, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            GradeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, ID_DESC_AMY + MODULE_DESC_CS2103T, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            GradeCommand.MESSAGE_USAGE));

        // additional prefix
        assertParseFailure(parser, ID_DESC_AMY + NAME_DESC_AMY + MODULE_DESC_CS2103T + SCORE_DESC_89,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE));
    }
}
