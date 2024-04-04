package seedu.edulink.logic.commands;

import static seedu.edulink.logic.parser.CliSyntax.PREFIX_FILENAME;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import seedu.edulink.commons.exceptions.DataLoadingException;
import seedu.edulink.commons.util.ToStringBuilder;
import seedu.edulink.logic.commands.exceptions.CommandException;
import seedu.edulink.model.AddressBook;
import seedu.edulink.model.Model;
import seedu.edulink.model.ReadOnlyAddressBook;
import seedu.edulink.storage.JsonAddressBookStorage;
import seedu.edulink.storage.Storage;

/**
 * Import the Student data from a Json file
 */
public class ImportCommand extends Command {
    public static final String COMMAND_WORD = "import";
    public static final String IMPORT_FORMAT = ".json";
    public static final String MESSAGE_IMPORT_SUCCESS = "Imported Data from the file - %s.json";
    public static final String MESSAGE_IMPORT_FAILURE = "Unable to Import data to Application : Invalid JSON File";
    public static final String MESSAGE_USAGE = "Usage: " + COMMAND_WORD + " " + PREFIX_FILENAME + "FILENAME";
    public static final String VALIDATION_FILENAME = "^[\\w\\-]+$";

    private final String fileName;

    private final Storage storage;
    /**
     * Creates a Import to edit tags of a student.
     *
     * @param fileName the filename to import from.
     * @param storage storage object to change the filePath.
     */
    public ImportCommand(String fileName, Storage storage) {
        this.fileName = fileName;
        this.storage = storage;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            JsonAddressBookStorage addressBookStorage = getNewAddressBookStorage();
            Optional<ReadOnlyAddressBook> addressBookOptional = addressBookStorage.readAddressBook();
            if (addressBookOptional.isEmpty()) {
                throw new CommandException(String.format("File not found: %s.json", fileName));
            }
            storage.setAddressBookFilePath(addressBookStorage);
            ReadOnlyAddressBook addressBookToImport = addressBookOptional.get();
            AddressBook importedAddressBook = new AddressBook(addressBookToImport);
            model.setAddressBook(importedAddressBook);
            return new CommandResult(String.format(MESSAGE_IMPORT_SUCCESS, fileName));
        } catch (DataLoadingException e) {
            throw new CommandException(MESSAGE_IMPORT_FAILURE);
        }
    }

    public JsonAddressBookStorage getNewAddressBookStorage() {
        Path filePath = Paths.get("data/" + fileName + IMPORT_FORMAT);
        return new JsonAddressBookStorage(filePath);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ImportCommand)) {
            return false;
        }

        ImportCommand otherExportCommand = (ImportCommand) other;
        return this.fileName.equalsIgnoreCase(otherExportCommand.fileName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("filename", fileName)
            .toString();
    }

    public String getFilename() {
        return fileName;
    }
}
