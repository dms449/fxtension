package stevensd.settings.controllers;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.fxml.Initializable;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSettingsController implements Initializable {
  public HashMap<Property, Property> propertyMap;

  public SimpleBooleanProperty isChanged;

  public AbstractSettingsController() {
    propertyMap = new HashMap<>();
    isChanged = new SimpleBooleanProperty(false);
  }

  /**
   * Add a {@link Property} object for monitoring.
   *
   * Creates a new {@link SimpleObjectProperty} to keep the last applied state of the provided property.
   * @param properties
   */
  public void addProperties(Property... properties){
    for (Property obj: properties){
      obj.addListener(observable -> isChanged.setValue(isChanged()));
      propertyMap.put(obj,  new SimpleObjectProperty<>(obj.getValue()));
    }
  }


  /**
   * Add a property and a listener.
   *
   * The listener will be applied to the applied SimpleObjectProperty which allows the user to be notified when a
   * specific property has been changed via apply.
   * @param property {@link Property} object to be monitored. This should be the property of the GUI object.
   * @param listener {@link ChangeListener} A listener added to the newly created SimpeObjectProperty
   * @param <T>
   */
  public <T> void addProperty(Property<T> property, ChangeListener<T> listener){
    property.addListener(observable -> isChanged.setValue(isChanged()));
    SimpleObjectProperty<T> applied = new SimpleObjectProperty<>(property.getValue());
    applied.addListener(listener);
    propertyMap.put(property, applied);
  }

  /**
   * Return the value currently stored in the "applied" property.
   *
   * @param property This is the key to the other "applied" property
   * @param <T> The return type should match the type in the provided property
   * @return
   */
  public <T> T getAppliedValue(Property<T> property){
    return (T)propertyMap.get(property).getValue();
  }

  /**
   * Remove a property so that it will no longer be observed for changes
   * @param property
   */
  public void removeProperty(Property property){
    propertyMap.remove(property);
  }

  /**
   * Tests for equivalency between values in the 'gui' ArrayList and those int the 'applied' ArrayList. If there is any
   * difference, it will return true indicating something has changed.
   * @return
   */
  public boolean isChanged(){
    boolean flag = true;
    for (Map.Entry<Property, Property> entry: propertyMap.entrySet()){
      if (entry.getKey().getValue() != null){
        flag = flag && entry.getKey().getValue().equals(entry.getValue().getValue());
      } else {
        flag = flag && entry.getValue().getValue() == null;
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

      // Clear out the previously applied variables and set them to their new values
      for (Map.Entry<Property, Property> entry: propertyMap.entrySet()){
        entry.getValue().setValue(entry.getKey().getValue());
      }

      // Take action since something changed
      onChanged();

      // set the new value for isChanged (this should always be false)
      isChanged.setValue(isChanged());
    }

  }

  /**
   * Basically does the opposite of {@link AbstractSettingsController::apply} by updating the key values with the value
   * values.
   */
  public void reset(){
    for (Map.Entry<Property, Property> entry: propertyMap.entrySet()){
      entry.getKey().setValue(entry.getValue().getValue());
    }
  }

  /**
   * Concrete controller classes should implement this method to take action when something has changed and the user
   * has chosen to 'apply' the changes.
   */
  public abstract void onChanged();

}
