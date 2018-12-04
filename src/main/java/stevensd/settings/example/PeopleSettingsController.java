package stevensd.settings.example;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.omg.CORBA.INTERNAL;
import stevensd.settings.controllers.AbstractSettingsController;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class PeopleSettingsController  extends AbstractSettingsController {

    public enum Level{
        LEVEL1,
        LEVEL2,
        LEVEL3
    }

    public class Person{
        public Person(String name, int age, Level level) {
            this.name = name;
            this.age = age;
            this.level = level;
        }

        public String name;
        public int age;
        public Level level;
    }


    @FXML
    public TableView<Person> tableView;
    @FXML
    public TableColumn<Person, String> nameCol;
    @FXML
    public TableColumn<Person, Integer> ageCol;
    @FXML
    public TableColumn<Person, Level> levelCol;
    @FXML
    public TextField nameField;
    @FXML
    public TextField ageField;
    @FXML
    public ComboBox<Level> levelCombo;

    public ObservableList<Person> people;
    public SimpleBooleanProperty listChangedProperty;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        levelCombo.getItems().addAll(Level.values());
        people.addAll(new Person("Dan", 22, Level.LEVEL1), new Person("Raeley", 34, Level.LEVEL3));
        ListChangeListener<Person> listener = new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                while (c.next()){

                }
            }
        };
    }

    public PeopleSettingsController() {
        this.people = FXCollections.observableArrayList();
        listChangedProperty = new SimpleBooleanProperty();
    }

    @Override
    public void onChanged() {

    }

    @FXML
    public void add(){
        people.add(new Person(nameField.getText(), Integer.valueOf(ageField.getText()), levelCombo.getSelectionModel().getSelectedItem()));
    }
}
