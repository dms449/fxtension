package stevensd.settings.examples.full;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import stevensd.settings.PropertyGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class SimpleIndependentSettings extends PropertyGroup implements Initializable {
  @FXML  public TextField textField;
  @FXML  public ComboBox<Items> combo;
  @FXML  public ToggleButton btn;
  @FXML  public Spinner<Integer> intSpinner;
  @FXML  public Spinner<Double> doubleSpinner;



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
    group.addListener(()->System.out.println("Group was changed"));
    addGroup(group);

    this.addListener(()->System.out.println("Setting 1 changed"));
  }

  public SimpleIndependentSettings() {
    super();
  }

}
