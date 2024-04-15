package seedu.edulink.logic.parser;

import static seedu.edulink.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edulink.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.edulink.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.edulink.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static seedu.edulink.logic.commands.CommandTestUtil.MODULE_DESC_CS2103T;
import static seedu.edulink.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.edulink.logic.commands.CommandTestUtil.SCORE_DESC_89;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_MODULE_CS2103T;
import static seedu.edulink.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.edulink.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.edulink.logic.commands.DeleteGradeCommand;
import seedu.edulink.model.grade.Module;
import seedu.edulink.model.student.Id;

public class DeleteGradeCommandParserTest {

    private DeleteGradeCommandParser parser = new DeleteGradeCommandParser();

    @Test
    public void parse_validIdValidTag_returnsDeleteGradeCommand() {
        assertParseSuccess(parser, ID_DESC_AMY + MODULE_DESC_CS2103T,
            new DeleteGradeCommand(new Id(VALID_ID_AMY), new Module(VALID_MODULE_CS2103T)));
    }

    @Test
    public void parse_invalidModule_throwsParseException() {
        assertParseFailure(parser, ID_DESC_AMY + INVALID_MODULE_DESC,
            Module.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidId_throwsParseException() {
        assertParseFailure(parser, INVALID_ID_DESC + MODULE_DESC_CS2103T,
            Id.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidPrefix_failure() {
        // wrong prefix
        assertParseFailure(parser, ID_DESC_AMY + NAME_DESC_AMY, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DeleteGradeCommand.MESSAGE_USAGE));

        // missing prefix
        assertParseFailure(parser, ID_DESC_AMY, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DeleteGradeCommand.MESSAGE_USAGE));

        // additional prefix
        assertParseFailure(parser, ID_DESC_AMY + MODULE_DESC_CS2103T + SCORE_DESC_89,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGradeCommand.MESSAGE_USAGE));
    }
}
