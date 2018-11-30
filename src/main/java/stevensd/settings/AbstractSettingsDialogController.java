package stevensd.settings;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import stevensd.settings.controllers.AbstractSettingsController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public abstract class AbstractSettingsDialogController implements Initializable {
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

  // =================== Non FXML properties ==================

  public SettingsViewController<Setting> settingsViewController;

  public ArrayList<AbstractSettingsController> controllers;

  public SimpleBooleanProperty isChanged;



  @Override
  public void initialize(URL location, ResourceBundle resources) {
    FXMLLoader loader = new FXMLLoader();
    loader.setController(settingsViewController);
    loader.setLocation(AbstractSettingsDialogController.class.getResource("/SettingsView.fxml"));

    try{
      Pane pane = loader.load();
      vbox.getChildren().add(0, pane);

    } catch (IOException e){
      e.printStackTrace();
    }

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


  public AbstractSettingsDialogController(SettingsViewController<Setting> settingsViewController) {
    this.settingsViewController = settingsViewController;
    controllers = new ArrayList<>();
    isChanged = new SimpleBooleanProperty();
  }


  public void syncGuiToApp() {
    controllers.forEach(AbstractSettingsController::apply);
  }

  public void syncAppToGui() {
    controllers.forEach(AbstractSettingsController::reset);
  }

  public abstract void syncAppToDisk();


  public abstract void syncDiskToGui();

  public abstract void close();

  public void addControllers(AbstractSettingsController... array){
    for (AbstractSettingsController c: array){
      controllers.add(c);
      c.isChanged.addListener((observable, oldValue, newValue) -> {
        boolean val = newValue;
        for (AbstractSettingsController controller: controllers){
          val = val || controller.isChanged.getValue();
        }
        isChanged.setValue(val);
      });
    }
  }

  public SettingsViewController<Setting> getSettingsViewController() {
    return settingsViewController;
  }
}
