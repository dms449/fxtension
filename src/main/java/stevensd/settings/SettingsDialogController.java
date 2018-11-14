package stevensd.settings;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsDialogController implements Initializable {
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

  public SettingsController<Setting> settingsController;


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    FXMLLoader loader = new FXMLLoader();
    loader.setController(settingsController);
    loader.setLocation(SettingsDialogController.class.getResource("/Settings.fxml"));

    try{
      Pane pane = loader.load();
      vbox.getChildren().add(0, pane);

    } catch (IOException e){
      e.printStackTrace();
    }

    okBtn.setOnAction(event -> System.out.println("ok"));
    cancelBtn.setOnAction(event -> System.out.println("cancel"));
    applyBtn.setOnAction(event -> System.out.println("apply"));
    defaultBtn.setOnAction(event -> System.out.println("default"));

  }

  public SettingsDialogController(SettingsController<Setting> settingsController) {
    this.settingsController = settingsController;
  }


}
