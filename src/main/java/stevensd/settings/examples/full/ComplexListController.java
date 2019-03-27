package stevensd.settings.examples.full;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import stevensd.settings.PropertyGroup;

import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ComplexListController extends PropertyGroup implements Initializable {
    @FXML
    public VBox vbox;
    @FXML
    public Button addBtn;

    public ObservableList<Connection> connections;

    public SimpleListProperty<Connection> listProperty;


    public ArrayList<StringProperty> nameProperties;
    public ArrayList<ObjectProperty<InetAddress>> ipProperties;
    public ArrayList<StringProperty> portProperties;
    public ArrayList<BooleanProperty> onProperties;

    public static class Connection{
        public String name;
        public InetAddress ip;
        public int port;
        public boolean on;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addBtn.setOnAction(event -> addRow());
    }

    public ComplexListController() {
        connections = FXCollections.emptyObservableList();
        listProperty = new SimpleListProperty<>(connections);
    }

    public void addRow(){

        // nameField
        TextField nameField = new TextField();
        nameProperties.add(nameField.textProperty());

        // ip combo
        ComboBox<InetAddress> ipCombo = new ComboBox<>();
        ipProperties.add(ipCombo.valueProperty());

        // port field
        TextField portField = new TextField();
        portProperties.add(nameField.textProperty());


        // delete button
        ToggleButton deleteBtn =  new ToggleButton("-");
//        deleteBtn.setOnAction(event -> removeRow());
        onProperties.add(deleteBtn.selectedProperty());

        addProperties();

        HBox hbox = new HBox(nameField, ipCombo, portField, deleteBtn);
        hbox.setSpacing(5.0);
        vbox.getChildren().add(hbox);
    }

//    public void removeRow(int row){
//        gridPane.getChildren().remove(row);
//        rowNum -= 1;
//    }
}
