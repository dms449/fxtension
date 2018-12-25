package stevensd.settings.examples.basic;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import stevensd.settings.PropertyGroup;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Setting2Controller extends PropertyGroup implements Initializable {
  @FXML
  public DatePicker datePicker;
  @FXML
  public CheckBox check1;
  @FXML
  public RadioButton radio1;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    addProperties(radio1.selectedProperty(), check1.selectedProperty());
    addProperty(datePicker.valueProperty(), ((observable, oldValue, newValue) ->
            System.out.println(String.format("date updated to: " + newValue.format(DateTimeFormatter.BASIC_ISO_DATE)))));
  }

  public Setting2Controller() {
    super();
  }

//  @Override
//  public void onChanged() {
//    System.out.println("At least one property has been changed");
//  }

}
