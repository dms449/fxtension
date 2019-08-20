package dms.fxtensions.settings.examples.full;

import dms.fxtensions.settings.PropertyGroup;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class Setting22Controller extends PropertyGroup implements Initializable {
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

//  @Override
//  public void onChanged() {
//    System.out.println("Setting 2-2 changed");
//  }

}
