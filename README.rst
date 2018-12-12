settings-dialog
===============

Embed a settings dialog into any JavaFX application with ease.

**NOTE: This project is still under active development and is far from completed.**

![](screenshots/setting1.pn "Logo Title Text 1")

![](screenshots/setting21.pn "Logo Title Text 1")

Installation
------------


Usage
-----


#### Setting Panes
Create a *Setting* object and add it to the *SettingsView*.

```java
// the controller behind the SettingsView holds all of the Setting objects
SettingsViewController<Setting> svc = new SettingsViewController<>();

// the root Pane of every setting should be an AnchorPane
Setting setting = new Setting(anchorPane, "setting name");
svc.addSetting(setting);

```
or use the SettingsFactory to load the pane from an FXML and automatically
add it to the *SettingsView*.

```java
SettingsViewController<Setting> svc = new SettingsViewController<>();

// create the factory by providing the class and the SettingsViewController instance
SettingsFactory<Setting> factory = new SettingsFactory<>(Setting.class, svc);

// you must provide a controller which implements the Initializable interface
factory.createAndAdd("settingName", "pathToFile.fxml", initializableController);

```


#### Setting Pane Controllers
Each setting pane will need a controller. If you plan to use the *SettingsDialog* and the
`AbstractSettingsDialogController` then your individual Setting controllers should extend `AbstractSettingController`.

This provides some very critical and helpful functionality for detecting and acting on changes made to a setting
property.

```java
public class SettingController extends AbstractSettingsController {
  @FXML
  public DatePicker datePicker;
  @FXML
  public CheckBox check;
  @FXML
  public RadioButton radio;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // Add any number of properties for monitoring
    addProperties(radio.selectedProperty(), check.selectedProperty());

    // Use this method when you would like to add a property and a listener. The listener will be called when the
    // provided property value has changed and the change has been applied.
    // For more information see, `AbstractSettingsController:addProperty`
    addProperty(datePicker.valueProperty(), ((observable, oldValue, newValue) ->
            System.out.println(String.format("date updated to: " + newValue.format(DateTimeFormatter.BASIC_ISO_DATE)))));
  }

  public Setting2Controller() {
    super();
  }

  // This method is called after at least one monitored property has been changed and the change
  // has been applied.
  @Override
  public void onChanged() {
    System.out.println("At least one property has been changed");
  }


```

