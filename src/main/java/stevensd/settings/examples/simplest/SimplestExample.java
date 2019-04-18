package stevensd.settings.examples.simplest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import stevensd.settings.Setting;
import stevensd.settings.SimpleSetting;
import stevensd.settings.controllers.AbstractSettingsDialogController;
import stevensd.settings.controllers.SettingsViewController;

import java.io.IOException;

/**
 * This examples only demonstrates the Settings TreeView for displaying Setting panes.
 * It does not demonstrate the use of PropertyGroups or ConfigManager
 *
 * Emulating this approach will probably not be sufficient for most applications.
 */
public class SimplestExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // This is the controller behind the SettingsView. This is NOT the controller behind each individual settings
        // pane. In this very simple examples, the individual setting panes do not have a controller.
        // The SettingsViewController doesn't need to know or interact with the individual setting controllers.
        SettingsViewController<SimpleSetting> svc = new SettingsViewController<>();

        // setting pane 1
        AnchorPane p1 = new AnchorPane();
        p1.getChildren().add(new Button("I'm a button"));
        svc.addSetting(new SimpleSetting(p1, "Setting 1"));

        // setting pane 2
        AnchorPane p2 = new AnchorPane();
        p2.getChildren().add(new TableView<>());
        svc.addSetting(new SimpleSetting(p2, "Setting 2"));


        try{
            // load the settings view
            FXMLLoader loader = new FXMLLoader();
            loader.setController(svc);
            loader.setLocation(AbstractSettingsDialogController.class.getResource("/SettingsView.fxml"));
            Pane settingsPane = loader.load();


            // create the scene and show the stage
            Scene scene = new Scene(settingsPane);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (
                IOException e){
            e.printStackTrace();
        }
    }

}
