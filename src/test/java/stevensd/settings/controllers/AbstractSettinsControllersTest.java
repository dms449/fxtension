package stevensd.settings.controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test AbstractSettingsController's base functionality
 */
public class AbstractSettinsControllersTest {

  /**
   * A very simple concrete implementation
   */
  public class ConcreteSettingsController extends AbstractSettingsController{
    public ConcreteSettingsController() {
      super();
    }

    @Override
    public void onChanged() {
      System.out.println("Something changed");
    }
  }

  public ConcreteSettingsController controller;
  public SimpleStringProperty string;
  public SimpleIntegerProperty integer;
  public SimpleBooleanProperty bool;

  /**
   * Sets up static test fixtures.
   * Called once before the class has been instantiated.
   */
  @BeforeClass
  public static void first(){
  }

  /**
   * Tears down static test fixtures
   * Called last after everything else
   */
  @AfterClass
  public static void last(){}

  /**
   * Called before every test
   */
  @Before
  public void setUp(){
    controller = new ConcreteSettingsController();

    string = new SimpleStringProperty("hey");
    integer = new SimpleIntegerProperty(5);
    bool = new SimpleBooleanProperty(false);

    controller.addSettings(string, integer, bool);
  }

  /**
   * Called after every test
   */
  @After
  public void tearDown(){

  }

  // =============================================== Tests ========================================

  /**
   * Method {@link AbstractSettingsController::isChanged} returns boolean depending on whether or not an added setting
   * has changed its value since the last time {@link AbstractSettingsController::apply} was called.
   */
  @Test
  public void testIsChanged(){
    assertFalse(controller.isChanged());
    string.setValue("hello");
    assertTrue(controller.isChanged());
    string.setValue("hey");
    integer.setValue(4);
    assertTrue(controller.isChanged());
    integer.setValue(5);
    assertFalse(controller.isChanged());
  }

  /**
   * Method {{@link AbstractSettingsController::apply}} takes changes made to added settings and applies them to the
   * application
   */
  @Test
  public void testApply(){
    assertFalse(controller.isChanged());
    integer.setValue(4);
    assertTrue(controller.isChanged());
    controller.apply();
    assertFalse(controller.isChanged());
    integer.setValue(5);
    assertTrue(controller.isChanged());
  }

  /**
   * Method {{@link AbstractSettingsController::reset}} basically does the opposite of
   * {{@link AbstractSettingsController::apply}} and changes the values in the added settings properties to match the
   * last applied values.
   */
  @Test
  public void testReset(){
    assertFalse(controller.isChanged());
    bool.setValue(true);
    assertTrue(controller.isChanged());
    controller.reset();
    assertFalse(controller.isChanged());
  }
}
