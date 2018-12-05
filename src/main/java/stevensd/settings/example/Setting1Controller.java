package stevensd.settings.example;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import stevensd.settings.controllers.AbstractSettingsController;

import java.net.URL;
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
    this.addProperty(textField.textProperty(), (observable, oldValue, newValue) -> System.out.println("text field changed to: "+newValue) );
    this.addProperty(combo.valueProperty(), (observable, oldValue, newValue) -> System.out.println("combo changed to: "+newValue));
    this.addProperty(btn.selectedProperty(), (observable, oldValue, newValue) -> System.out.println("button changed to: "+newValue));
  }

  public Setting1Controller() {
    super();
  }

  @Override
  public void onChanged() {
    System.out.println("Setting 1 changed");
  }
}
