package seedu.edulink.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of Recent Commands.
 */
public class RecentCommandPanel extends UiPart<Region> {
    private static final String FXML = "RecentCommandPanel.fxml";
    @FXML
    private ListView<String> commands;


    /**
     * Creates a {@code RecentCommandPanel} with the given {@code recentCommands}.
     */
    public RecentCommandPanel(ObservableList<String> recentCommands) {
        super(FXML);
        commands.setItems(recentCommands);
        commands.setCellFactory(listView -> new RecentCommandCell());
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
