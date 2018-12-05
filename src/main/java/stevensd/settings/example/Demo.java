package stevensd.settings.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import stevensd.settings.Setting;


public class Demo extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {

    ConcreteSettingsDialogController<Setting> dialogController = new ConcreteSettingsDialogController<>(primaryStage, Setting.class);

    Setting s1 = dialogController.createAndAdd("setting1", "/exampleSettings/setting1.fxml", new Setting1Controller());
    Setting s2 = dialogController.createAndAdd("setting2", "/exampleSettings/setting2.fxml", new Setting2Controller());
    dialogController.createAndAdd("setting2-1", "/exampleSettings/setting2-1.fxml", new Setting21Controller(), s2);
    dialogController.createAndAdd("setting2-2", "/exampleSettings/setting2-2.fxml", new Setting22Controller(), s2);

    Scene scene = new Scene(dialogController.getPane());
    primaryStage.setScene(scene);
    primaryStage.show();
  }


}
