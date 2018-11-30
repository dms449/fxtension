package stevensd.settings.example;

import javafx.beans.property.Property;
import javafx.fxml.Initializable;
import stevensd.settings.controllers.AbstractSettingsController;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class Setting22Controller extends AbstractSettingsController {
  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public Setting22Controller() {
    super();
  }

  @Override
  public void onChanged(Map<Property, Object> changed) {
    System.out.println("Setting 2-2 changed");
    for (Map.Entry<Property, Object> entry: changed.entrySet()){
      System.out.println("from " + entry.getValue() + "  to  " + entry.getKey());
    }
    System.out.println("\n");
  }

}
