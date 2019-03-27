package stevensd.settings.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public abstract class ConfigManager<T> {
    public ArrayList<Saveable<T>> saveables = new ArrayList<>();

    protected void save(T config){
        saveables.forEach(each -> each.save(config));
    }

    protected void load(T config){
        saveables.forEach(each -> each.load(config));
    }

    public void loadDefault(T config){
        load(config);
    }

    public void addSaveable(Saveable<T> saveable){
        saveables.add(saveable);
    }

    public void addSaveables(Saveable<T>... saveables){
        this.saveables.addAll(Arrays.asList(saveables));
    }

    public void removeSaveable(Saveable<T> saveable){
        saveables.remove(saveable);
    }

    public abstract boolean save(File file);

    public abstract boolean save();

    public abstract boolean load(File file);

    public abstract boolean load();

}
