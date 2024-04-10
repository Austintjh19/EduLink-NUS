package seedu.edulink.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import seedu.edulink.logic.Logic;
import seedu.edulink.logic.commands.CommandResult;
import seedu.edulink.logic.commands.exceptions.CommandException;
import seedu.edulink.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final Logic logic;
    private final MainWindow mainWindow;
    private int recentCommandCounter;
    private int detailsIndex;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor, Logic logic, MainWindow mainWindow) {
        super(FXML);
        this.logic = logic;
        this.commandExecutor = commandExecutor;
        this.mainWindow = mainWindow;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        commandTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.TAB) {
                ObservableList<String> recentCommands = logic.getRecentCommands();
                if (!recentCommands.isEmpty()) {
                    recentCommandCounter = logic.getRecentCommandsCounter();
                    String text = recentCommands.get(recentCommandCounter % recentCommands.size());
                    commandTextField.setText(text);
                    this.commandTextField.requestFocus();
                    this.commandTextField.positionCaret(text.length());
                }
                event.consume();
            } else if (event.getCode() == KeyCode.ESCAPE) {
                if (!logic.getFilteredPersonList().isEmpty()) {
                    detailsIndex = logic.getDetailsIndex();
                    mainWindow.updateStudentDetailsCard(detailsIndex % logic.getFilteredPersonList().size());
                }
                event.consume();
            }
        });
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    public TextField getCommandTextField() {
        return commandTextField;
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.edulink.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
