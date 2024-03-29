package seedu.edulink.ui;

import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of Recent Commands.
 */
public class RecentCommandPanel extends UiPart<Region> {
    private static final String FXML = "RecentCommandPanel.fxml";
    @FXML
    private ListView<String> commands;

    private final CommandBox commandBox;


    /**
     * Creates a {@code RecentCommandPanel} with the given {@code recentCommands}.
     */
    public RecentCommandPanel(ObservableList<String> recentCommands, CommandBox commandBox) {
        super(FXML);
        this.commandBox = commandBox;
        commands.setItems(recentCommands);
        commands.setCellFactory(listView -> {
            RecentCommandCell cell = new RecentCommandCell();
            cell.setOnMouseClicked(this::handleMouseClick);
            return cell;
        });
    }

    private void handleMouseClick(MouseEvent mouseEvent) {
        RecentCommandCell source = (RecentCommandCell) mouseEvent.getSource();
        Optional<Label> label = Optional.of((Label) source.getGraphic());
        label.ifPresent(value -> this.commandBox.getCommandTextField().setText(value.getText()));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code RecentCommand}
     * using a {@code Label}.
     */
    static class RecentCommandCell extends ListCell<String> {
        @Override
        protected void updateItem(String command, boolean empty) {
            super.updateItem(command, empty);

            if (empty || command == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new Label(command));
            }
        }
    }
}
