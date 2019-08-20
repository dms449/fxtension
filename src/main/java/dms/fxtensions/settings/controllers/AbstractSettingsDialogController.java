package dms.fxtensions.settings.controllers;

import dms.fxtensions.settings.PropertyGroup;
import dms.fxtensions.settings.Setting;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class AbstractSettingsDialogController<T extends Setting<T>> extends PropertyGroup implements Initializable {

  public SettingsViewController<T> settingsViewController;

  public Pane settingsPane;


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

  public AbstractSettingsDialogController() {
    settingsViewController = new SettingsViewController<>();
    isChanged = new SimpleBooleanProperty();
  }

  public SettingsViewController<T> getSettingsViewController() {
    return settingsViewController;
  }

  public void setSettingsViewController(SettingsViewController<T> settingsViewController) {
    this.settingsViewController = settingsViewController;
  }

  public void selectFirst(){
    settingsViewController.treeView.getSelectionModel().selectFirst();
  }

  public void select(T setting){
//    settingsViewController.treeView.getSelectionModel().select(setting)
  }
}
