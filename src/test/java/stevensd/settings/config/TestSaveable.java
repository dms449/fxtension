package stevensd.settings.config;

import stevensd.settings.generated.TestConfig;

/**
 * A very simple implementation of a {@link Saveable} used for testing.
 */
public class TestSaveable implements Saveable<TestConfig>{
    public int loadCount = 0;
    public int saveCount = 0;

    @Override
    public void load(TestConfig config) {
        loadCount++;
    }

    @Override
    public void save(TestConfig config) {
        saveCount++;
    }
}
