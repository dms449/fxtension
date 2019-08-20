package dms.fxtensions.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public abstract class ConfigManager<T> {
    public ArrayList<Saveable<T>> saveables;

    /**
     * Defualt constructor
     */
    public ConfigManager() {
        saveables = new ArrayList<>();
    }

    /**
     * Constructor with saveables included.
     * @param saveables
     */
    public ConfigManager(ArrayList<Saveable<T>> saveables) {
        this.saveables = saveables;
    }

    protected void save(T config){
        saveables.forEach(each -> each.save(config));
    }

    protected void load(T config){
        saveables.forEach(each -> each.load(config));
    }

    public void addSaveable(Saveable<T> saveable){
        saveables.add(saveable);
    }

    public void addSaveables(Saveable<T>... saveableArray){
        saveables.addAll(Arrays.asList(saveableArray));
    }

    public void removeSaveable(Saveable<T> saveable){
        saveables.remove(saveable);
    }

    public void removeSaveables(Saveable<T>... saveableArray){
        saveables.removeAll(Arrays.asList(saveableArray));
    }

    public void removeAllSaveables(){
        saveables.clear();
    }

    public abstract boolean save(File file);

    public abstract boolean save();

    public abstract boolean load(File file);

    public abstract boolean load();

}
