package dms.fxtensions.settings.controllers;

import dms.fxtensions.settings.PropertyGroup;
import dms.fxtensions.settings.Setting;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class SimpleSettingsDialogController<T extends Setting<T>> extends SettingsDialogFactoryController<T> {
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


  // =================== Non FXML propertyMap ==================

  public Pane mainPane;

  public Stage stage;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    super.initialize(location, resources);
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
      close();
    });

    // The apply button should be enabled anytime something is changed
    isChanged.addListener( (observable, oldValue, newValue) -> applyBtn.setDisable(!newValue));
  }

  public SimpleSettingsDialogController(Class<T> clazz) {
    super(clazz);
  }

  public void syncGuiToApp() {
    children.forEach(PropertyGroup::apply);
  }

  public void syncAppToGui() {
    children.forEach(PropertyGroup::reset);
  }

  public abstract void syncAppToDisk();

  public abstract void syncDiskToGui();

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

  public void close(){
    if (stage != null){
      stage.close();
    }
  }

  public Pane getPane(){
    if (mainPane == null){
      load();
    }
    return mainPane;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }
}
