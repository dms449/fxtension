package stevensd.settings;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * A factory class to make the creation of the Settings and them being added to the TreeView simpler.
 * @param <T>
 */
public class SettingsFactory<T extends Setting> {
  public Class<T> clazz;
  public SettingsController<T> settingsController;

  /**
   * Constructor
   * @param clazz The class for the {@link Setting} object that will be created.
   * @param settingsController An {@link Initializable} object
   */
  public SettingsFactory(Class<T> clazz, SettingsController<T> settingsController) {
    this.clazz = clazz;
    this.settingsController = settingsController;
  }

  /**
   * Create a setting and add it to the controller.
   *
   * <p>Also return the new setting</p>
   * @param name The name of the Setting. This will be displayed in the GUI.
   * @param resource The path to the *.fxml resource
   * @param controller The controller that goes with the setting
   * @return
   */
  public T create(String name, String resource, Initializable controller){
    try{
      FXMLLoader loader = new FXMLLoader();
      loader.setController(controller);
      loader.setLocation(getClass().getResource(resource));

      AnchorPane anchorPane = loader.load();

      // Create the setting and add the content and name
      T setting = clazz.newInstance();
      setting.setContent(anchorPane);
      setting.setName(name);
      return setting;

    } catch (IOException | InstantiationException | IllegalAccessException e){
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Create and add a setting to the Settings TreeView.
   * @param name The name of the Setting. This will be displayed in the GUI.
   * @param resource The path to the *.fxml resource
   * @param controller The controller that goes with the setting
   * @return
   */
  public T createAndAdd(String name, String resource, Initializable controller){
    T setting = create(name, resource, controller);
    settingsController.addSetting(setting);
    return setting;
  }

  /**
   * Create and add a setting as a sub-setting to another setting
   * @param name The name of the Setting. This will be displayed in the GUI.
   * @param resource The path to the *.fxml resource
   * @param controller The controller that goes with the setting
   * @param parent A {@link Setting} which is the parent of this new setting.
   * @return The newly created setting
   */
  public T createAndAdd(String name, String resource, Initializable controller, T parent){
    T setting = create(name, resource, controller);
    parent.add(setting);
    return setting;
  }
}
