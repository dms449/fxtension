package stevensd.settings.examples.list;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import stevensd.settings.Setting;
import stevensd.settings.examples.ConcreteSettingsDialogController;


public class ListExample extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {


        // Create the controller for the settings dialog.

        // NOTE: ConcreteSettingsDialogController is an empty concrete implementation of SimpleSettingsDialogController.
        // For your application, subclass SimpleSettingsDialogController, NOT ConcreteSettingsDialogController.
        ConcreteSettingsDialogController<Setting> dialogController = new ConcreteSettingsDialogController<>(primaryStage, Setting.class);

        // Add settings by providing urls to *.fxml documents and corresponding controllers.
        dialogController.createAndAdd("simpleList", "/examples/list/simpleList.fxml", new simpleListController());
        dialogController.createAndAdd("complexList", "/examples/list/complexList.fxml", new complexListController());

        // Show the settings pane everytime `btn` is clicked
        Scene settingsScene = new Scene(dialogController.getPane());
        Button btn = new Button("Show Settings");
        btn.setOnAction(event -> {
            Stage settingsStage = new Stage();
            dialogController.setStage(settingsStage);
            settingsStage.setScene(settingsScene);
            settingsStage.showAndWait();
        });

        // Create the main Pane that is visible when the application starts. (its just a button)
        AnchorPane pane = new AnchorPane();
        pane.getChildren().add(btn);

        // Create and show the settings dialog. (The DialogController knows how to load itself)
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Settings");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
