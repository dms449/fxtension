package stevensd.settings.example;

import javafx.beans.property.Property;
import javafx.fxml.Initializable;
import stevensd.settings.controllers.AbstractSettingsController;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class Setting21Controller extends AbstractSettingsController {
  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public Setting21Controller() {
    super();
  }

  @Override
  public void onChanged() {
    System.out.println("Setting 2-1 changed");

  }

}
