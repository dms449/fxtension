package stevensd.settings.config;

import org.junit.*;
import stevensd.settings.generated.TestConfig;

import javax.xml.bind.JAXBException;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class JaxbConfigManagerTest {
    public static JaxbConfigManager<TestConfig> configManager;

    /**
     * Happens once before any tests
     */
    @BeforeClass
    public static void first(){
        try{
            configManager = new JaxbConfigManager<TestConfig>(TestConfig.class);
        } catch (JAXBException e){
            e.printStackTrace();
        }
    }

    /**
     * Happens last after all tests
     */
    @AfterClass
    public static void last(){
    }

    /**
     * Happens before each test.
     */
    @Before
    public void beforeTest(){

    }
    /**
     * Happens after each test.
     */
    @After
    public void afterTest(){
    }

    @Test
    public void testSave(){

    }

    @Test
    public void testSaveFile(){

    }


    @Test
    public void testLoad(){
    }

    @Test
    public void testLoadFile(){
        configManager.load(new File(getClass().getResource("/TestConfig.xml").getFile()));

    }
}
