package dms449.settings.examples.full;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import dms449.settings.PropertyGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class SimpleListController extends PropertyGroup implements Initializable {

    /**
     * A simple Person class
     */
    public static class Person{
        public String name;
        public int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Person){
                Person person = (Person)obj;
                return (person.name.equals(this.name)) && (person.age == this.age);
            }
            return false;
        }
    }


    @FXML
    public ListView<Person> listView;
    @FXML
    public TextField nameField;
    @FXML
    public TextField ageField;

    /**
     * THe list of Person Objects to be monitored
     */
    public ObservableList<Person> personList = FXCollections.observableArrayList();

    public SimpleListProperty<Person> listProperty;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set the observable list behind the listview
        listView.setItems(personList);

        // add a custom cell to show the person objects
        listView.setCellFactory(param -> {
            ListCell<Person> cell = new ListCell<Person>(){
                @Override
                protected void updateItem(Person item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null) {
                        setText("");
                    } else {
                        setText(item.name + " (" + String.valueOf(item.age) + ")");
                    }
                }
            };
            return cell;
        });

        listProperty = new SimpleListProperty<>(personList);
        addListProperty(listProperty, c -> {
            while(c.next()){
                if (c.wasAdded()){
                    System.out.println("Added items -");
                    c.getAddedSubList().forEach(System.out::println);
                } else if (c.wasRemoved()){
                    System.out.println("Removed items -");
                    c.getRemoved().forEach(System.out::println);
                }
            }
        });
    }

    @FXML
    public void add(){
        personList.add(new Person(nameField.getText(), Integer.valueOf(ageField.getText())));
    }

    @FXML
    public void remove(){
        personList.remove(listView.getSelectionModel().getSelectedItem());
    }
}
