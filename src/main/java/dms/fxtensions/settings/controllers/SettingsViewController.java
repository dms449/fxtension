package dms.fxtensions.settings.controllers;

import dms.fxtensions.settings.Setting;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * The controller that will display a different pane based on the selected TreeItem.
 *
 *<p> All Setting objects are added to this object</p>
 * @param <T>
 */
public class SettingsViewController<T extends Setting<T>> implements Initializable {
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

      if (!newValue.getChildren().isEmpty()){
        newValue.setExpanded(true);
      }

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
    TreeItem<T> item = new TreeItem<>(setting);
    setting.children.forEach(s -> this.addSetting(s, item));
    this.root.getChildren().add(item);

    setting.children.addListener(new ListChangeListener<T>() {
      @Override
      public void onChanged(Change<? extends T> c) {
        while(c.next()){
          if (c.wasAdded()){
            c.getAddedSubList().forEach(setting -> SettingsViewController.this.addSetting(setting, item));
          }

          if (c.wasRemoved()){
            c.getRemoved().forEach(setting -> SettingsViewController.this.removeSetting(setting));
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

  /**
   *
   * @param setting
   * @param treeItem
   */
  public void addSetting(T setting, TreeItem<T> treeItem){
    TreeItem<T> item = new TreeItem<>(setting);
    setting.children.forEach(s -> this.addSetting((T)s, item));
    treeItem.getChildren().add(item);
  }

  /**
   * Remove a {@link Setting} object
   *
   * @param setting A Setting object which supposedly resides on the tree
   */
  public void removeSetting(T setting){
    root.getChildren().forEach(each -> removeSetting(setting, each));
    this.root.getChildren().removeIf(treeItem -> treeItem.getValue()==setting);
  }

  /**
   * Remove all occurances of the setting object in the provided TreeItem
   */
  public void removeSetting(T setting, TreeItem<T> treeItem){
    treeItem.getChildren().removeIf(treeItem1 -> treeItem1.getValue().equals(setting));
    treeItem.getChildren().forEach(each -> removeSetting(setting, each));
  }

  /**
   * Attempt to get and return the tree item associated with this setting
   * @param setting
   * @return
   */
//  public TreeItem<T> getTreeItem(T setting){
//    root.getChildren().forEach(each->getTreeItem(setting, each));
//
//  }

  /**
   * Iteratively attempt to find the TreeItem which corresponds to the setting
   *
   * @param setting
   * @param treeItem
   * @return
   */
  public TreeItem<T> getTreeItem(T setting, TreeItem<T> treeItem){
    if (treeItem.getValue().equals(setting)){
      return treeItem;
    }else {
      treeItem.getChildren().forEach(each -> getTreeItem(setting, each));
    }
    return null;
  }

}
