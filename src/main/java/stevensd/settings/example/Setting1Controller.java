package stevensd.settings.example;

import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
  public ComboBox<Items> combo;
  @FXML
  public ToggleButton btn;

  public enum Items{
    ITEM1,
    ITEM2,
    ITEM3
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    combo.getItems().addAll(Items.values());
    this.addSettings(textField.textProperty(), (observable, oldValue, newValue) -> System.out.println("text field changed to: "+newValue) );
    this.addSettings(combo.valueProperty(), (observable, oldValue, newValue) -> System.out.println("combo changed to: "+newValue));
    this.addSettings(btn.selectedProperty(), (observable, oldValue, newValue) -> System.out.println("button changed to: "+newValue));
  }

  public Setting1Controller() {
    super();
  }

  @Override
  public void onChanged() {
    System.out.println("Setting 1 changed");
  }
}
