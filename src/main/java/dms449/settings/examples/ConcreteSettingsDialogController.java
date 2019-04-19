package dms449.settings.examples;

import dms449.settings.Setting;
import dms449.settings.controllers.SimpleSettingsDialogController;

import java.net.URL;
import java.util.ResourceBundle;

public class ConcreteSettingsDialogController<T extends Setting<T>> extends SimpleSettingsDialogController<T> {
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
