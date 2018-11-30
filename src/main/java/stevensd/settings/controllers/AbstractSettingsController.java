package stevensd.settings.controllers;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public abstract class AbstractSettingsController implements Initializable {
//  public ArrayList<Property> gui;
//  public ArrayList<Object> applied;
  public HashMap<Property, Object> properties;

  public SimpleBooleanProperty isChanged;

  public AbstractSettingsController() {

//    gui = new ArrayList<>();
//    applied = new ArrayList<>();
    properties = new HashMap<>();
    isChanged = new SimpleBooleanProperty(false);
  }

  /**
   * Add a {@link Property} object for monitoring.
   *
   * Once it is added, its last 'applied' state is monitored and compared to its current state. (@see isChanged())
   * @param array
   */
  public void addSettings(Property... array){
    for (Property obj: array){
      obj.addListener(observable -> isChanged.setValue(isChanged()));
      properties.put(obj, obj.getValue());
//      gui.add(obj);
//      applied.add(obj.getValue());
    }
  }

  /**
   * Tests for equivalency between values in the 'gui' ArrayList and those int the 'applied' ArrayList. If there is any
   * difference, it will return true indicating something has changed.
   * @return
   */
  public boolean isChanged(){
    boolean flag = true;
    for (Map.Entry<Property, Object> entry:properties.entrySet()){
      if (entry.getKey().getValue() != null){
        flag = flag && entry.getKey().getValue().equals(entry.getValue());
      }
    }
    return !flag;
  }

  /**
   * Applies changes made in 'gui' items to those 'applied'.
   *
   * After updating the values in 'applied' to match those in 'gui', {@link AbstractSettingsController::onChanged} is
   * called to give the user the ability to take action since something has changed.
   */
  public void apply(){
    if (isChanged()){
      // Take action since something changed
      Map<Property, Object> changed = properties.entrySet()
          .stream()
          .filter(entry -> !entry.getKey().getValue().equals(entry.getValue()))
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
      onChanged(changed);

      // Clear out the previously applied variables and set them to their new values
      for (Map.Entry<Property, Object> entry:properties.entrySet()){
        entry.setValue(entry.getKey().getValue());
      }

      // set the new value for isChanged (this should always be false)
      isChanged.setValue(isChanged());
    }

  }

  /**
   * Basically does the opposite of {@link AbstractSettingsController::apply} by updating the values in 'gui' to match
   * those in 'applied'
   */
  public void reset(){
    for (Map.Entry<Property, Object> entry:properties.entrySet()){
      entry.getKey().setValue(entry.getValue());
    }
  }

  /**
   * Concrete controller classes should implement this method to take action when something has changed and the user
   * has chosen to 'apply' the changes.
   */
  public abstract void onChanged(Map<Property, Object> changed);

}
