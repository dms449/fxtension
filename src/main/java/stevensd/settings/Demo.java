package stevensd.settings;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import stevensd.settings.example.setting1Controller;


public class Demo extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    SettingsDialogController dialogController = new SettingsDialogController(initSettings());
    FXMLLoader loader = new FXMLLoader();
    loader.setController(dialogController);
    loader.setLocation(Demo.class.getResource("/SettingsDialog.fxml"));

    Pane primaryPane = loader.load();
    Scene scene = new Scene(primaryPane);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public SettingsController<Setting> initSettings(){
    SettingsController<Setting> settingsController = new SettingsController<>();
    SettingsFactory<Setting> factory = new SettingsFactory<>(Setting.class, settingsController);

    Setting s1 = factory.createAndAdd("setting1", "/exampleSettings/setting1.fxml", new setting1Controller());
    Setting s2 = factory.createAndAdd("setting2", "/exampleSettings/setting2.fxml", new setting1Controller());
    factory.createAndAdd("setting2-1", "/exampleSettings/setting2-1.fxml", new setting1Controller(), s2);
    factory.createAndAdd("setting2-2", "/exampleSettings/setting2-2.fxml", new setting1Controller(), s2);


    return settingsController;
  }

}
