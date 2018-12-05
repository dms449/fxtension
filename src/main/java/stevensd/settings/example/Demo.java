package stevensd.settings.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import stevensd.settings.Setting;
import stevensd.settings.SettingsFactory;
import stevensd.settings.SettingsViewController;
import stevensd.settings.AbstractSettingsDialogController;
import stevensd.settings.controllers.AbstractSettingsController;


public class Demo extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {

    AbstractSettingsController c1 = new Setting1Controller();
    AbstractSettingsController c2 = new Setting2Controller();
    AbstractSettingsController c21 = new Setting21Controller();
    AbstractSettingsController c22 = new Setting22Controller();

    SettingsViewController<Setting> sViewController = new SettingsViewController<>();
    SettingsFactory<Setting> factory = new SettingsFactory<>(Setting.class, sViewController);

    Setting s1 = factory.createAndAdd("setting1", "/exampleSettings/setting1.fxml", c1);
    Setting s2 = factory.createAndAdd("setting2", "/exampleSettings/setting2.fxml", c2);
    factory.createAndAdd("setting2-1", "/exampleSettings/setting2-1.fxml", c21, s2);
    factory.createAndAdd("setting2-2", "/exampleSettings/setting2-2.fxml", c22, s2);


    AbstractSettingsDialogController dialogController = new ConcreteSettingsDialogController(sViewController);
    dialogController.addControllers(c1, c2, c21, c22);
    FXMLLoader loader = new FXMLLoader();
    loader.setController(dialogController);
    loader.setLocation(Demo.class.getResource("/SettingsDialog.fxml"));

    Pane primaryPane = loader.load();
    Scene scene = new Scene(primaryPane);
    primaryStage.setScene(scene);
    primaryStage.show();
  }


}
