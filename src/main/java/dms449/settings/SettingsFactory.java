package dms449.settings;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import dms449.settings.controllers.SettingsViewController;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.function.Consumer;

/**
 * A factory class to simplify the making of the Settings and adding them to the appropriate TreeItem
 * @param <T>
 */
public class SettingsFactory<T extends Setting<T>> {

  /**
   * The class used to create the Settings
   */
  public Class<T> clazz;

  /**
   * The controller that holds the TreeView
   */
  public SettingsViewController<T> settingsController;
  public Consumer<T> onCreate;

  /**
   *
   * @param clazz The class for the {@link Setting} object that will be created.
   * @param settingsController An {@link Initializable} object
   * @param consumer
   */
  SettingsFactory(Class<T> clazz, SettingsViewController<T> settingsController, Consumer<T> consumer){
    this.clazz = clazz;
    this.settingsController = settingsController;
    this.onCreate = consumer;
  }

  /**
   * Constructor
   * @param clazz The class for the {@link Setting} object that will be created.
   * @param settingsController An {@link Initializable} object
   */
  public SettingsFactory(Class<T> clazz, SettingsViewController<T> settingsController) {
    this(clazz, settingsController, null);
  }

  /**
   *
   * @param clazz The class for the {@link Setting} object that will be created.
   */
  public SettingsFactory(Class<T> clazz){
    this(clazz, new SettingsViewController<>(), null);
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
  public T create(String name, URL resource, Initializable controller){
    try{
      FXMLLoader loader = new FXMLLoader();
      loader.setController(controller);
      loader.setLocation(resource);

      AnchorPane anchorPane = loader.load();

      // Create the setting and add the content and name
      Constructor<T> constructor = clazz.getConstructor(new Class[]{AnchorPane.class,String.class});
      T setting = constructor.newInstance(anchorPane, name);

      // notifiy the consumer if it exists
      if (onCreate != null){
        onCreate.accept(setting);
      }
      return setting;

    } catch (IOException | InstantiationException | IllegalAccessException e){
      e.printStackTrace();
    } catch (NoSuchMethodException | InvocationTargetException e2){
      e2.printStackTrace();
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
  public T createAndAdd(String name, URL resource, Initializable controller){
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
  public T createAndAdd(String name, URL resource, Initializable controller, T parent){
    T setting = create(name, resource, controller);
    parent.add(setting);
    return setting;
  }

  /**
   *
   * @param onCreate
   */
  public void setOnCreate(Consumer<T> onCreate) {
    this.onCreate = onCreate;
  }
}
