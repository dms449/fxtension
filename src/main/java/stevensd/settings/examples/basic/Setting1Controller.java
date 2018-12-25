package stevensd.settings.examples.basic;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import stevensd.settings.PropertyGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class Setting1Controller extends PropertyGroup implements Initializable {
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
    this.addProperty(btn.selectedProperty(), (observable, oldValue, newValue) -> System.out.println("button changed to: "+newValue));

    PropertyGroup group = new PropertyGroup();
    group.addProperty(textField.textProperty(), (observable, oldValue, newValue) -> System.out.println("text field changed to: "+newValue));
    group.addProperty(combo.valueProperty(), (observable, oldValue, newValue) -> System.out.println("combo changed to: "+newValue));
    group.addListeners(()->System.out.println("Group was changed"));
    addGroup(group);

    this.addListeners(()->System.out.println("Setting 1 changed"));
  }

  public Setting1Controller() {
    super();
  }

}
