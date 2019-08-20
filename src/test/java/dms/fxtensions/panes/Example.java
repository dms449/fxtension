package dms.fxtensions.panes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Example extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        root.setPrefSize(600, 600);
        Scene scene = new Scene(root, 600, 600);

        AnchorPane draggable = new AnchorPane();
        draggable.getChildren().add(new VBox(new Button("button"), new CheckBox("box")));
        draggable.setPrefSize(100, 200);
        draggable.setMaxSize(100, 100);
        root.getChildren().add(draggable);
        draggable.setStyle("-fx-background-color: red");
        root.setStyle("-fx-background-color: green");

        DragToResize.apply(draggable);

        stage.setTitle("My JavaFX Application");
        stage.setScene(scene);
        stage.show();
    }
}
