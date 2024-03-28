package seedu.edulink.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.edulink.logic.commands.DeleteTagCommand;
import seedu.edulink.logic.commands.EditTagCommand;
import seedu.edulink.model.student.Id;
import seedu.edulink.model.tag.Tag;

import java.util.HashSet;
import java.util.Set;

import static seedu.edulink.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edulink.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.edulink.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class EditTagCommandParserTest {

    private EditTagCommandParser parser = new EditTagCommandParser();

    @Test
    public void parse_validArg_returnsTagCommand() {
        Tag tagToReplace = new Tag("TopStudent");
        Tag resultingTag = new Tag("friend");
        Id id = new Id("A0912124E");
        assertParseSuccess(parser, " id/A0912124E t/TopStudent t/friend",
            new EditTagCommand(id, tagToReplace, resultingTag));
    }

    @Test
    public void parse_oneTag_throwsParseException() {
        assertParseFailure(parser, " id/A9014616L t/topstudent",
                EditTagCommand.MESSAGE_TWO_TAGS_NEED + EditTagCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_noTag_throwsParseException() {
        assertParseFailure(parser, " id/A9014616L",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_moreThanTwoTag_throwsParseException() {
        assertParseFailure(parser, " id/A9014616L t/topstudent t/top t/student",
                EditTagCommand.MESSAGE_TWO_TAGS_NEED + EditTagCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_invalidId_throwsParseException() {
        assertParseFailure(parser, " id/A9016L t/topstudent t/potentialTA",
            Id.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidIdTag_throwsParseException() {
        assertParseFailure(parser, " id/A9016L t/top student",
            Id.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_noId_throwsParseException() {
        assertParseFailure(parser, " t/topstudent",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidTag_throwsParseException() {
        assertParseFailure(parser, " id/A9014616L t/top student t/potentialTA",
                Tag.MESSAGE_CONSTRAINTS);
    }

}
