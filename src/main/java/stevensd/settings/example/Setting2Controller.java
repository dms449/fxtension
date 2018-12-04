package stevensd.settings.example;

import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import stevensd.settings.controllers.AbstractSettingsController;

import java.net.URL;
import java.util.Map;
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
    addSettings(datePicker.valueProperty(), radio1.selectedProperty(), radio2.selectedProperty());
  }

  public Setting2Controller() {
    super();
  }

  @Override
  public void onChanged() {
    System.out.println("Setting 2 changed");
  }

}
