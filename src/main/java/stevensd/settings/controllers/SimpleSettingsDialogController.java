package stevensd.settings.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import stevensd.settings.Setting;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class SimpleSettingsDialogController<T extends Setting> extends SettingsDialogFactoryController<T> {
  @FXML
  public VBox vbox;
  @FXML
  public Button okBtn;
  @FXML
  public Button applyBtn;
  @FXML
  public Button cancelBtn;
  @FXML
  public Button defaultBtn;
  @FXML
  public ToolBar toolBar;
  @FXML
  public HBox hbox;

  // =================== Non FXML propertyMap ==================

  public Pane mainPane;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    super.initialize(location, resources);
    // this makes most of the buttons to sit at the right hand side of the toolbar
    toolBar.widthProperty().addListener((observable, oldValue, newValue) -> hbox.setPrefWidth(newValue.doubleValue()-15.0));
    // add the settings pane to this pane
    vbox.getChildren().add(0,settingsPane);

    // Define the button actions

    // Ok Button
    // Save the settings and close the window
    okBtn.setOnAction(event -> {
      syncGuiToApp();
      syncAppToDisk();
      close();
    });

    applyBtn.setOnAction(event -> {
      syncGuiToApp();
      syncAppToDisk();
    });

    cancelBtn.setOnAction(event -> {
      syncAppToGui();
    });

    // Default Button
    // load the default config
    defaultBtn.setOnAction(event -> {
    });

    // The apply button should be enabled anytime something is changed
    isChanged.addListener( (observable, oldValue, newValue) -> applyBtn.setDisable(!newValue));
  }

  public SimpleSettingsDialogController(Stage stage, Class<T> clazz) {
    super(stage, clazz);
  }

  public void load(){
    try{
      FXMLLoader loader = new FXMLLoader();
      loader.setController(this);
      loader.setLocation(Setting.class.getResource("/SettingsDialog.fxml"));
      mainPane = loader.load();
    } catch (IOException e){
      e.printStackTrace();
    }
  }

  public Pane getPane(){
    if (mainPane == null){
      load();
    }
    return mainPane;
  }
}
