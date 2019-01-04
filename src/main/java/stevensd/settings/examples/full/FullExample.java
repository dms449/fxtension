package stevensd.settings.examples.full;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import stevensd.settings.Setting;
import stevensd.settings.examples.ConcreteSettingsDialogController;

import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;


public class FullExample extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {

    // Create the controller for the settings dialog.

    // NOTE: ConcreteSettingsDialogController is an empty concrete implementation of SimpleSettingsDialogController.
    // For your application, subclass SimpleSettingsDialogController, NOT ConcreteSettingsDialogController.
    ConcreteSettingsDialogController<Setting> dialogController = new ConcreteSettingsDialogController<>(Setting.class);

    // Add settings by providing urls to *.fxml documents and corresponding controllers.
    Setting s1 = dialogController.createAndAdd("Simple", getClass().getResource("/examples/full/simpleIndependentSettings.fxml"), new SimpleIndependentSettings());
    Setting s2 = dialogController.createAndAdd("Lists", getClass().getResource("/examples/full/listSettings.fxml"), new ListSettingsController());

    // Same as above except that these are sub-settings (belong under a more general setting) so there parent setting must be provided
    dialogController.createAndAdd("Simple List",  getClass().getResource("/examples/full/simpleList.fxml"), new SimpleListController(), s2);
    dialogController.createAndAdd("Complex List",  getClass().getResource("/examples/full/complexList.fxml"), new ComplexListController(), s2);

    // groups
//    dialogController.createAndAdd("Groups", "/examples/full/setting2-2.fxml", new Setting22Controller(), s2);

    // Show the settings pane everytime `btn` is clicked
    Scene settingsScene = new Scene(dialogController.getPane());
    Button btn = new Button("Show Settings");

    // the stage
    Stage stage = new Stage();

    // this allows the dialog to close the stage when it wants (when 'Cancel' is clicked for example).
    dialogController.setStage(stage);

    stage.setScene(settingsScene);
    btn.setOnAction(event -> {
      stage.showAndWait();
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
