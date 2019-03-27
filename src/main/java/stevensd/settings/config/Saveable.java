package stevensd.settings.config;

/**
 * This defines an object which will be saved to and loaded from a config file of some sort.
 * @param <T>
 */
public interface Saveable<T> {
    void load(T config);
    void save(T config);
}
