package stevensd.settings.controllers;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import stevensd.settings.Setting;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * The controller that will display a different pane based on the selected TreeItem.
 *
 *<p> All Setting objects are added to this object</p>
 * @param <T>
 */
public class SettingsViewController<T extends Setting> implements Initializable {
  @FXML
  public TreeView<T> treeView;
  @FXML
  public AnchorPane contentContainer;


  public TreeItem<T> root;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.treeView.setRoot(this.root);
    this.root.setExpanded(true);
    this.treeView.setShowRoot(false);
    this.treeView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
      this.contentContainer.getChildren().clear();
      AnchorPane pane = newValue.getValue().getContent();
      this.contentContainer.getChildren().add(pane);
      AnchorPane.setBottomAnchor(pane, -1.0D);
      AnchorPane.setTopAnchor(pane, -1.0D);
      AnchorPane.setLeftAnchor(pane, -1.0D);
      AnchorPane.setRightAnchor(pane, -1.0D);
    }));
  }

  /**
   * Constructor that creates an empty TreeItem for root
   */
  public SettingsViewController() {
    this.root = new TreeItem<>();
  }

  /**
   * Constructor that takes a TreeItem which will be used for root
   * @param root
   */
  public SettingsViewController(TreeItem<T> root) {
    this.root = root;
  }

  /**
   * Add a {@link Setting} object
   * @param setting
   */
  public void addSetting(T setting){
    TreeItem item = new TreeItem<>(setting);
    setting.children.forEach(s -> this.addSetting((T)s, item));
    this.root.getChildren().add(item);

    setting.children.addListener(new ListChangeListener<Setting>() {
      @Override
      public void onChanged(Change<? extends Setting> c) {
        while(c.next()){
          if (c.wasAdded()){
            c.getAddedSubList().forEach(setting -> SettingsViewController.this.addSetting((T)setting, item));
          }
        }
      }
    });
  }

  /**
   * Add multiple Settings
   * @param setting
   */
  public void addSettings(T... setting){
    Arrays.asList(setting).forEach(this::addSetting);
  }

  public void addSetting(T setting, TreeItem<T> treeItem){
    TreeItem<T> item = new TreeItem<>(setting);
    setting.children.forEach(s -> this.addSetting((T)s, item));
    treeItem.getChildren().add(item);
  }

  /**
   * Remove a {@link Setting} object
   * @param setting A Setting object which supposedly resides on the tree
   */
  public void removeSetting(T setting){
    this.root.getChildren().removeIf(treeItem -> treeItem.getValue()==setting);
  }

  /**
   * Remove a {@link Setting} object which is looked up by its String name
   * @param s The String name of the Setting object
   */
  public void removeSetting(String s){
    this.root.getChildren().removeIf(treeItem -> treeItem.getValue().getName().equals(s));
  }

  public void createAndAdd(String name, String resource,  Initializable controller){

  }
}
