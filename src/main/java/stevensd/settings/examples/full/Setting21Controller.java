package stevensd.settings.examples.full;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import stevensd.settings.PropertyGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class Setting21Controller extends PropertyGroup implements Initializable {
  @FXML
  public RadioButton radio1;
  @FXML
  public RadioButton radio2;
  @FXML
  public RadioButton radio3;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    addProperties(radio1.selectedProperty(), radio2.selectedProperty(), radio3.selectedProperty());
  }

  public Setting21Controller() {
    super();
  }

//  @Override
//  public void onChanged() {
//    System.out.println("Setting 2-1 changed");

//  }

}
