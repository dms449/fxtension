package stevensd.settings.controllers;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import stevensd.settings.Setting;
import stevensd.settings.SettingsFactory;

public abstract class SettingsDialogFactoryController<T extends Setting, C extends AbstractSettingsController> extends AbstractSettingsDialogController<T, C> {
  public SettingsFactory<T> factory;

  public SettingsDialogFactoryController(Stage stage, Class<T> clazz) {
    super(stage);
    this.factory = new SettingsFactory<>(clazz ,getSettingsViewController());
  }

  /**
   * Create and add a setting to the Settings TreeView.
   * @param name The name of the Setting. This will be displayed in the GUI.
   * @param resource The path to the *.fxml resource
   * @param controller The controller that goes with the setting
   * @return
   */
  public T createAndAdd(String name, String resource, C controller){
    addControllers(controller);
    return factory.createAndAdd(name, resource, controller);
  }

  /**
   * Create and add a setting as a sub-setting to another setting
   * @param name The name of the Setting. This will be displayed in the GUI.
   * @param resource The path to the *.fxml resource
   * @param controller The controller that goes with the setting
   * @param parent A {@link Setting} which is the parent of this new setting.
   * @return The newly created setting
   */
  public T createAndAdd(String name, String resource, C controller, T parent){
    addControllers(controller);
    return factory.createAndAdd(name, resource, controller, parent);
  }
}
