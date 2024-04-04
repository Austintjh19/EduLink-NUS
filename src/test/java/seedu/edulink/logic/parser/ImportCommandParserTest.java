package seedu.edulink.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.edulink.logic.commands.ImportCommand;
import seedu.edulink.logic.parser.exceptions.ParseException;
import seedu.edulink.storage.JsonAddressBookStorage;
import seedu.edulink.storage.JsonUserPrefsStorage;
import seedu.edulink.storage.StorageManager;

class ImportCommandParserTest {

    private static final String VALID_FILENAME = "test";
    private static final String INVALID_FILENAME = "";

    @TempDir
    Path tempDir;

    @Test
    void parse_validArgs_returnsImportCommand() throws Exception {
        Path validFilePath = tempDir.resolve(VALID_FILENAME);
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(tempDir.resolve("addressbook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(tempDir.resolve("userPrefs.json"));
        StorageManager storageManager = new StorageManager(addressBookStorage, userPrefsStorage);
        ImportCommandParser parser = new ImportCommandParser(storageManager);

        String userInput = " f/" + VALID_FILENAME;
        ImportCommand command = parser.parse(userInput);

        assertEquals(VALID_FILENAME, command.getFilename());
    }

    @Test
    void parse_invalidArgs_throwsParseException() {
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(tempDir.resolve("addressbook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(tempDir.resolve("userPrefs.json"));
        StorageManager storageManager = new StorageManager(addressBookStorage, userPrefsStorage);
        ImportCommandParser parser = new ImportCommandParser(storageManager);

        String userInput = " f/" + INVALID_FILENAME;
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}
