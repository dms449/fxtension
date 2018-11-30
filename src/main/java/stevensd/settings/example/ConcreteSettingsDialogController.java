package stevensd.settings.example;

import stevensd.settings.AbstractSettingsDialogController;
import stevensd.settings.Setting;
import stevensd.settings.SettingsViewController;
import stevensd.settings.controllers.AbstractSettingsController;

import java.util.ArrayList;
import java.util.HashMap;

public class ConcreteSettingsDialogController extends AbstractSettingsDialogController {

  public ConcreteSettingsDialogController(SettingsViewController<Setting> settingsViewController) {
    super(settingsViewController);
  }




  @Override
  public void syncAppToDisk() {

  }

  @Override
  public void syncDiskToGui() {

  }

  @Override
  public void close() {

  }
}
