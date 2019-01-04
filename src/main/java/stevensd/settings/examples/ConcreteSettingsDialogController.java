package stevensd.settings.examples;

import javafx.stage.Stage;
import stevensd.settings.Setting;
import stevensd.settings.controllers.SimpleSettingsDialogController;

import java.net.URL;
import java.util.ResourceBundle;

public class ConcreteSettingsDialogController<T extends Setting> extends SimpleSettingsDialogController<T> {
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    super.initialize(location, resources);
  }

  public ConcreteSettingsDialogController(Class<T> clazz) {
    super(clazz);
  }

  @Override
  public void syncAppToDisk() {

  }

  @Override
  public void syncDiskToGui() {

  }

}
