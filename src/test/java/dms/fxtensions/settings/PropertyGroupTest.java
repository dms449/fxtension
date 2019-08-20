package dms.fxtensions.settings;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.junit.*;

import javax.xml.bind.JAXBException;

import static org.junit.Assert.*;

public class PropertyGroupTest {

    public PropertyGroup group;

    /**
     * A custom listener that simply maintains its own list
     * @param <T>
     */
    public class CustomListListener<T> implements ListChangeListener<T>{
        public int count;

        @Override
        public void onChanged(Change<? extends T> c) {
            while(c.next()){
                if (c.wasAdded() || c.wasRemoved()){
                    count++;
                }
            }
        }

        public int getCount(){
            return count;
        }
    }

    /**
     * Happens once before any tests
     */
    @BeforeClass
    public static void first(){
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
        group = new PropertyGroup();
    }
    /**
     * Happens after each test.
     */
    @After
    public void afterTest(){
    }

    @Test
    public void testAddListProperty(){
        ObservableList<Integer> list1 = FXCollections.observableArrayList();
        SimpleListProperty<Integer> listProperty1 = new SimpleListProperty<>(list1);
        ObservableList<String> list2 = FXCollections.observableArrayList();
        SimpleListProperty<String> listProperty2 = new SimpleListProperty<>(list2);

        // begin adding things and testing
        group.addListProperty(listProperty1);
        assertTrue(group.listPropertyMap.containsKey(listProperty1));
        assertNotNull(group.listPropertyMap.get(listProperty1));
        assertFalse(group.isChanged());

        // add something to the added list and verify that the group detected the change
        list1.add(1);
        assertTrue(group.isChanged());

//        // Add an additional property
//        group.addListProperty(listProperty2);
//        assertTrue(group.listPropertyMap.containsKey(listProperty1));
//        assertTrue(group.listPropertyMap.containsKey(listProperty2));
//        assertNotNull(group.listPropertyMap.get(listProperty1));
//        assertNotNull(group.listPropertyMap.get(listProperty2));
    }

    @Test
    public void testAddListPropertyWithListener(){
        ObservableList<Integer> list1 = FXCollections.observableArrayList();
        SimpleListProperty<Integer> listProperty1 = new SimpleListProperty<>(list1);
//        ObservableList<String> list2 = FXCollections.observableArrayList();
//        SimpleListProperty<String> listProperty2 = new SimpleListProperty<>(list2);

        CustomListListener<Integer> beforeListener = new CustomListListener<>();
        CustomListListener<Integer> afterListener = new CustomListListener<>();
//        CustomListListener<String> stringListener = new CustomListListener<>();

        // this listener will be fired as soon as something is added to the list (before the change is applied)
        listProperty1.addListener(beforeListener);
        group.addListProperty(listProperty1, afterListener);


        assertFalse(group.isChanged());

        // add two items
        list1.addAll(1);

        // verify that the group is still false

        // the listener shouldn't have been activated yet
        assertEquals(1, beforeListener.getCount());
        assertEquals(0, afterListener.getCount());
        assertTrue(group.isChanged());

        // now apply the changes
        group.apply();

        // now check the listeners
        assertFalse(group.isChanged());
        assertEquals(1, beforeListener.getCount());
        assertEquals(1, afterListener.getCount());

    }
}
