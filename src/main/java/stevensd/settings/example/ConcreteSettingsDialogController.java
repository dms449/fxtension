package stevensd.settings.example;

import javafx.stage.Stage;
import stevensd.settings.Setting;
import stevensd.settings.controllers.AbstractSettingsController;
import stevensd.settings.controllers.SimpleSettingsDialogController;

public class ConcreteSettingsDialogController<T extends Setting> extends SimpleSettingsDialogController<T, AbstractSettingsController> {


  public ConcreteSettingsDialogController(Stage stage, Class<T> clazz) {
    super(stage, clazz);
  }

  @Override
  public void syncAppToDisk() {

  }

  @Override
  public void syncDiskToGui() {

  }

}
