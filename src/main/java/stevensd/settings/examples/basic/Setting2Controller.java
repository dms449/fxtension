package stevensd.settings.examples.basic;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import stevensd.settings.controllers.AbstractSettingsController;

import java.net.URL;
import java.util.ResourceBundle;

public class Setting2Controller extends AbstractSettingsController {
  @FXML
  public DatePicker datePicker;
  @FXML
  public RadioButton radio1;
  @FXML
  public RadioButton radio2;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    addProperties(datePicker.valueProperty(), radio1.selectedProperty(), radio2.selectedProperty());
  }

  public Setting2Controller() {
    super();
  }

  @Override
  public void onChanged() {
    System.out.println("Setting 2 changed");
  }

}
