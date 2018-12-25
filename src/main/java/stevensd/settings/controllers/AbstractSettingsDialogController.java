package stevensd.settings.controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import stevensd.settings.PropertyGroup;
import stevensd.settings.Setting;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public abstract class AbstractSettingsDialogController<T extends Setting> extends PropertyGroup implements Initializable {

  public SettingsViewController<T> settingsViewController;

//  public SimpleBooleanProperty isChanged;

  public Pane settingsPane;

  public Stage stage;


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    FXMLLoader loader = new FXMLLoader();
    loader.setController(settingsViewController);
    loader.setLocation(AbstractSettingsDialogController.class.getResource("/SettingsView.fxml"));

    try{
      settingsPane = loader.load();
    } catch (IOException e){
      e.printStackTrace();
    }
  }

  public AbstractSettingsDialogController(Stage stage) {
    setStage(stage);
    settingsViewController = new SettingsViewController<>();
    isChanged = new SimpleBooleanProperty();
  }


  public void close(){
    stage.close();
  }

  public Stage getStage() {
    return stage;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  public SettingsViewController<T> getSettingsViewController() {
    return settingsViewController;
  }

  public void setSettingsViewController(SettingsViewController<T> settingsViewController) {
    this.settingsViewController = settingsViewController;
  }
}
