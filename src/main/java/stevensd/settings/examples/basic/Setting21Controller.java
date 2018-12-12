package stevensd.settings.examples.basic;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import stevensd.settings.controllers.AbstractSettingsController;

import java.net.URL;
import java.util.ResourceBundle;

public class Setting21Controller extends AbstractSettingsController {
  @FXML
  public RadioButton radio1;
  @FXML
  public RadioButton radio2;
  @FXML
  public RadioButton radio3;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    addProperties(radio1.selectedProperty(), radio2.selectedProperty(), radio3.selectedProperty());
  }

  public Setting21Controller() {
    super();
  }

  @Override
  public void onChanged() {
    System.out.println("Setting 2-1 changed");

  }

}
