package stevensd.settings.config;

import org.junit.Before;
import org.junit.Test;
import stevensd.settings.generated.TestConfig;

import java.io.File;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ConfigManagerTest {

    /**
     * A very simple implementation of the abstract class {@link ConfigManager}.
     *
     * It will be used to test the default functionality of the class.
     */
    public static class TestConfigManager extends ConfigManager<TestConfig>{

        /**
         * N/A
         * @param file
         * @return
         */
        @Override
        public boolean save(File file) {
            return false;
        }

                /**
         * N/A
         * @return
         */
        @Override
        public boolean save() {
            return false;
        }

                /**
         * N/A
         * @param file
         * @return
         */
        @Override
        public boolean load(File file) {
            return false;
        }

                /**
         * N/A
         * @return
         */
        @Override
        public boolean load() {
            return false;
        }
    }


    /**
     * The config manager instance to use for testing
     */
    public ConfigManager<TestConfig> configManager;

    /**
     * Before each test.
     */
    @Before
    public void beforeTest(){
        configManager = new TestConfigManager();
    }

    /**
     * Test that saveables are properly added and removed from a config manager
     */
    @Test
    public void testAddAndRemoveSaveable(){
        TestSaveable saveable1 = new TestSaveable();
        TestSaveable saveable2 = new TestSaveable();

        // add
        configManager.addSaveable(saveable1);
        assertTrue(configManager.saveables.contains(saveable1));
        assertFalse(configManager.saveables.contains(saveable2));

        // remove
        configManager.removeSaveable(saveable1);
        assertFalse(configManager.saveables.contains(saveable1));
        assertFalse(configManager.saveables.contains(saveable2));

        // add multiple
        configManager.addSaveables(saveable1, saveable2);
        assertTrue(configManager.saveables.contains(saveable2));
        assertTrue(configManager.saveables.contains(saveable1));

        // remove multiple
        configManager.removeSaveables(saveable1, saveable2);
        assertFalse(configManager.saveables.contains(saveable2));
        assertFalse(configManager.saveables.contains(saveable1));


        // remove all
        configManager.addSaveables(saveable1, saveable2);
        configManager.removeAllSaveables();
        assertFalse(configManager.saveables.contains(saveable2));
        assertFalse(configManager.saveables.contains(saveable1));
    }

    /**
     * The {@link JaxbConfigManager::load} method is responsible for calling load on each of its {@link Saveable}
     * objects.
     */
    @Test
    public void testSaveAndLoad(){
        TestSaveable saveable1 = new TestSaveable();
        TestSaveable saveable2 = new TestSaveable();

        TestConfig config = new TestConfig();


        // first load and save
        configManager.addSaveable(saveable1);
        configManager.load(config);
        configManager.save(config);
        assertEquals(1, saveable1.loadCount);
        assertEquals(0, saveable2.loadCount);
        assertEquals(1, saveable1.saveCount);
        assertEquals(0, saveable2.saveCount);

        // second load
        configManager.addSaveable(saveable2);
        configManager.load(config);
        configManager.save(config);
        assertEquals(2, saveable1.loadCount);
        assertEquals(1, saveable2.loadCount);
        assertEquals(2, saveable1.saveCount);
        assertEquals(1, saveable2.saveCount);

        // third load
        configManager.removeSaveable(saveable1);
        configManager.removeSaveable(saveable2);
        configManager.load(config);
        configManager.save(config);
        assertEquals(2, saveable1.loadCount);
        assertEquals(1, saveable2.loadCount);
        assertEquals(2, saveable1.saveCount);
        assertEquals(1, saveable2.saveCount);
    }


}
