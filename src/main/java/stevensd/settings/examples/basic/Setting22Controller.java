package stevensd.settings.examples.basic;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import stevensd.settings.controllers.AbstractSettingsController;

import java.net.URL;
import java.util.ResourceBundle;

public class Setting22Controller extends AbstractSettingsController {
  @FXML
  public ColorPicker colorPicker;
  @FXML
  public Slider slider;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    addProperties(slider.valueProperty(), colorPicker.valueProperty());
  }

  public Setting22Controller() {
    super();
  }

  @Override
  public void onChanged() {
    System.out.println("Setting 2-2 changed");
  }

}
