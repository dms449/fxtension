package stevensd.settings.example;

import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import stevensd.settings.controllers.AbstractSettingsController;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class Setting1Controller extends AbstractSettingsController {
  @FXML
  public TextField textField;
  @FXML
  public ComboBox combo;
  @FXML
  public ToggleButton btn;


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.addSettings(textField.textProperty(), combo.itemsProperty(), btn.selectedProperty());
  }

  public Setting1Controller() {
    super();
  }

  @Override
  public void onChanged(Map<Property, Object> changed) {
    System.out.println("Setting 1 changed");
    for (Map.Entry<Property, Object> entry: changed.entrySet()){
      System.out.println(entry.getKey() + " changed from " + entry.getValue() + "  to  " + entry.getKey().getValue());
    }
    System.out.println("\n");
  }
}
