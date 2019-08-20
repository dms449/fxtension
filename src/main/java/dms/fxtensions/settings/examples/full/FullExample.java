package dms.fxtensions.settings.examples.full;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dms.fxtensions.settings.SimpleSetting;
import dms.fxtensions.settings.examples.ConcreteSettingsDialogController;


public class FullExample extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {

    // Create the controller for the settings dialog.

    // NOTE: ConcreteSettingsDialogController is an empty concrete implementation of SimpleSettingsDialogController.
    // For your application, subclass SimpleSettingsDialogController, NOT ConcreteSettingsDialogController.
    ConcreteSettingsDialogController<SimpleSetting> dialogController = new ConcreteSettingsDialogController<>(SimpleSetting.class);

    // Add settings by providing urls to *.fxml documents and corresponding controllers.
    SimpleSetting s1 = dialogController.createAndAdd("Simple", getClass().getResource("/examples/full/simpleIndependentSettings.fxml"), new SimpleIndependentSettings());
    SimpleSetting s2 = dialogController.createAndAdd("Lists", getClass().getResource("/examples/full/listSettings.fxml"), new ListSettingsController());

    // Same as above except that these are sub-settings (belong under a more general setting) so there parent setting must be provided
    SimpleSetting s3 = dialogController.createAndAdd("Simple List",  getClass().getResource("/examples/full/simpleList.fxml"), new SimpleListController(), s2);
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
