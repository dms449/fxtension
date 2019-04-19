package dms449.settings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;

import java.util.Arrays;

/**
 *
 */
public abstract class Setting<T extends Setting> {
  public AnchorPane content;
  public String name;

  public ObservableList<T> children;

  /**
   * Constructor for a simple setting
   * @param content An AnchorPane containing the settings GUI
   * @param name The name of this settings object

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
  public void add(T setting){
    children.add(setting);
  }

  /**
   * Add multiple {@link Setting}s as a child to this object
   * @param settings
   */
  public void addAll(T... settings){
    Arrays.asList(settings).forEach(this::add);
  }

  /**
   * Remove a {@link Setting} child from this object
   * @param setting
   */
  public void remove(T setting){
    children.remove(setting);
  }

  /**
   * Remove multiple {@link Setting}s
   * @param settings
   */
  public void removeAll(T... settings){
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
