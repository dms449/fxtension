package stevensd.settings;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PropertyGroup {
    public HashMap<Property, Property> propertyMap = new HashMap<>();
    public ArrayList<PropertyGroup> children = new ArrayList<>();

    public SimpleBooleanProperty isChanged = new SimpleBooleanProperty(false);

    public ArrayList<PropertyGroupListener> listeners = new ArrayList<>();

    /**
     * Contsruct a new PropertyGroup from a list of properties
     * @param properties
     */
    public PropertyGroup(Property... properties) {
        addProperties(properties);
    }

    /**
     * Default constructor.
     */
    public PropertyGroup() {
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
     * Remove a property so that it will no longer be observed for changes
     * @param property
     */
    public void removeProperty(Property property){
        propertyMap.remove(property);
    }

    /**
     * Add a grou
     * @param group
     */
    public void addGroup(PropertyGroup group){
        group.isChanged.addListener(observable -> isChanged.setValue(isChanged()));
        children.add(group);
    }

    public void removeGroup(PropertyGroup group){
        children.remove(group);
    }

    public void addListeners(PropertyGroupListener... listeners){
        this.listeners.addAll(Arrays.asList(listeners));
    }

    /**
     * Add any number of listeners to this group.
     * @param listeners
     */
    public void removeListeners(PropertyGroupListener... listeners){
        this.listeners.removeAll(Arrays.asList(listeners));
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
     * Add a ChangeListener to an auto-generated property which is mapped to a provided property.
     *
     * @param property The provided property which acts as a key to the applied property.
     * @param listener The listener to be added to the applied property
     * @param <T>
     */
    public <T> void addListenerToProperty(Property<T> property, ChangeListener<T> listener){
        propertyMap.get(property).addListener(listener);
    }

    /**
     * Tests for equivalency between values in the 'gui' ArrayList and those int the 'applied' ArrayList. If there is any
     * difference, it will return true indicating something has changed.
     * @return
     */
    public boolean isChanged(){
        // Check if any of the properties have changed
        for (Map.Entry<Property, Property> entry: propertyMap.entrySet()){
            if (entry.getKey().getValue() != null){
                if (!entry.getKey().getValue().equals(entry.getValue().getValue())){
                    return true;
                }
            } else {
                if (entry.getValue().getValue() != null){
                    return true;
                }
            }
        }

        // check if any of the groups have changed
        return children.stream().anyMatch(PropertyGroup::isChanged);
    }

    /**
     * Applies changes made in 'gui' items to those 'applied'.
     *
     * After updating the values in 'applied' to match those in 'gui', {@link AbstractSettingsController ::onChanged} is
     * called to give the user the ability to take action since something has changed.
     */
    public void apply(){
        if (isChanged.getValue()){
            // Clear out the previously applied variables and set them to their new values
            for (Map.Entry<Property, Property> entry: propertyMap.entrySet()){
                entry.getValue().setValue(entry.getKey().getValue());
            }

            // call this method on all of its children groups
            children.forEach(PropertyGroup::apply);

            // set the new value for isChanged. If this is not false, something has gone wrong
            isChanged.setValue(isChanged());
            if (isChanged.getValue()){
                System.err.println("PropertyGroup apply failed!");
            }

            // notify the listeners
            listeners.forEach(PropertyGroupListener::onChanged);
        }
    }

    /**
     * Basically does the opposite of {@link AbstractSettingsController::apply} by updating the key values with the value
     * values.
     */
    public void reset(){
        // reset the properties
        for (Map.Entry<Property, Property> entry: propertyMap.entrySet()){
            entry.getKey().setValue(entry.getValue().getValue());
        }

        // reset all children groups
        children.forEach(PropertyGroup::reset);
    }
}