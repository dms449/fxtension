package stevensd.settings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;

import java.util.Arrays;

public class Setting {
  public AnchorPane content;
  public String name;
  public ObservableList<Setting> children;

  /**
   * Constructor for a simple setting
   * @param content
   * @param name
   */
  public Setting(AnchorPane content, String name) {
    this.content = content;
    this.name = name;

    children = FXCollections.observableArrayList();
  }

  /**
   * Simple Constructor
   */
  public Setting(){
    children = FXCollections.observableArrayList();
  }

  /**
   * Add a {@link Setting} as a child to this object
   * @param setting
   */
  public void add(Setting setting){
    children.add(setting);
  }

  /**
   * Add multiple {@link Setting}s as a child to this object
   * @param settings
   */
  public void addAll(Setting... settings){
    Arrays.asList(settings).forEach(this::add);
  }

  /**
   * Remove a {@link Setting} child from this object
   * @param setting
   */
  public void remove(Setting setting){
    children.remove(setting);
  }

  /**
   * Remove multiple {@link Setting}s
   * @param settings
   */
  public void removeAll(Setting... settings){
    Arrays.asList(settings).forEach(this::remove);
  }

  public AnchorPane getContent(){
    return content;
  }

  public String getName() {
    return name;
  }

  public void setContent(AnchorPane content) {
    this.content = content;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString(){
    return name;
  }
}
