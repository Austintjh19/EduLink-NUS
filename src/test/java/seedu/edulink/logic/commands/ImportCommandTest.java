package seedu.edulink.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.edulink.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.edulink.logic.commands.exceptions.CommandException;
import seedu.edulink.model.Model;
import seedu.edulink.model.ModelManager;
import seedu.edulink.model.UserPrefs;
import seedu.edulink.storage.JsonAddressBookStorage;
import seedu.edulink.storage.JsonUserPrefsStorage;
import seedu.edulink.storage.Storage;
import seedu.edulink.storage.StorageManager;

public class ImportCommandTest {

    @TempDir
    public Path tempDir;
    private Model model;
    private Storage storage;
    private ImportCommand importCommand;



    @BeforeEach
    public void setUp() throws IOException {
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(tempDir.resolve("userPrefs.json"));
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(tempDir.resolve("addressBook.json"));
        UserPrefs userPrefs = new UserPrefs();
        storage = new StorageManager(addressBookStorage, userPrefsStorage);
        model = new ModelManager(getTypicalAddressBook(), userPrefs);
    }


    @Test
    public void execute_invalidFileName_throwsCommandException() {
        String invalidFileName = "invalid&File";
        importCommand = new ImportCommand(invalidFileName, storage);
        assertThrows(CommandException.class, () -> importCommand.execute(model));
    }

    @Test
    public void execute_fileNotFound_throwsCommandException() {
        String nonExistentFileName = "nonExistentFile";
        importCommand = new ImportCommand(nonExistentFileName, storage);
        assertThrows(CommandException.class, () -> importCommand.execute(model));
    }


    @Test
    public void equals_sameObject_true() {
        String fileName = "validFile";
        importCommand = new ImportCommand(fileName, storage);
        assertEquals(importCommand, importCommand);
    }

    @Test
    public void equals_differentObject_false() {
        String fileName1 = "validFile1";
        String fileName2 = "validFile2";
        importCommand = new ImportCommand(fileName1, storage);
        ImportCommand otherImportCommand = new ImportCommand(fileName2, storage);
        assertFalse(importCommand.equals(otherImportCommand));
    }
}
