package model;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


// Contains tests for the Folder class.
class FolderTest {
    Folder folderOne;
    Folder folderTwo;
    MyFile fileOne;
    MyFile fileTwo;

    @BeforeEach
    void setUp() {
        fileOne = new MyFile("File One", "Content One");
        fileTwo = new MyFile("File Two", "Content Two");
        folderOne = new Folder("Folder One");
        folderTwo = new Folder("Folder Two");
    }


    @Test
    void testConstructor() {
        assertEquals("Folder One", folderOne.getName());
        assertEquals(0, folderOne.getItems().size());
        assertEquals("Folder Two", folderTwo.getName());
        assertEquals(0, folderTwo.getItems().size());
    }


    @Test
    void testGetItemByName() {
        folderOne.add(fileOne);
        folderOne.add(fileTwo);
        assertEquals(fileOne, folderOne.getItemByName("File One"));
        assertEquals(fileTwo, folderOne.getItemByName("File Two"));
        assertNull(folderOne.getItemByName("File Three which doesnt exist"));
    }

    @Test
    void testGetItems() {
        folderOne.add(fileOne);
        folderOne.add(fileTwo);
        assertFalse(folderOne.add(fileOne));
        assertEquals(2, folderOne.getItems().size());
    }

    @Test
    void testGetName() {
        assertEquals("Folder One", folderOne.getName());
    }

    @Test
    void testSetName() {
        folderOne.setName("New Folder One");
        assertEquals("New Folder One", folderOne.getName());
    }

    @Test
    void testAddItem() {
        assertTrue(folderOne.add(fileOne));
        assertFalse(folderOne.add(fileOne));
        assertTrue(folderOne.add(fileTwo));
        assertFalse(folderOne.add(fileTwo));

        assertEquals(2, folderOne.getItems().size());
        assertEquals(fileOne, folderOne.getItemByName("File One"));
        assertEquals(fileTwo, folderOne.getItemByName("File Two"));
    }

    @Test
    void changeDirectory() {
        folderOne.changeDirectory("I don't exist", folderOne);
        folderOne.changeDirectory("Folder Two", folderOne);
        assertEquals(0, folderOne.getItems().size());
        assertEquals("Folder One", folderOne.getName());
        folderOne.add(folderTwo);
        folderOne.changeDirectory("Folder Two", folderOne);
        assertEquals("Folder Two", folderTwo.getName());
        assertEquals(0, folderTwo.getItems().size());
    }

    @Test
    void changeDirectory2() {
        folderOne.add(folderTwo);
        folderOne.changeDirectory("Folder Two", folderOne);
        assertEquals("Folder Two", folderTwo.getName());
        assertEquals(0, folderTwo.getItems().size());
    }

    @Test
    void testRemove() {
        folderOne.add(fileOne);
        folderOne.add(fileTwo);
        folderOne.remove(fileOne);
        assertEquals(1, folderOne.getItems().size());
        assertEquals(fileTwo, folderOne.getItemByName("File Two"));
    }

    @Test void testDoesNotContainItem() {
        folderOne.add(fileOne);
        folderOne.add(fileTwo);
        assertTrue(folderOne.doesNotContainItem("File Million"));
        assertFalse(folderOne.doesNotContainItem("File One"));
    }

    @Test
    void testCloneDirectory() {
        assertNotEquals(folderOne, Folder.cloneDirectory(folderOne));
        assertNotEquals(Folder.cloneDirectory(folderOne), Folder.cloneDirectory(folderOne));
        assertEquals(folderOne.getName(), Folder.cloneDirectory(folderOne).getName());
        assertEquals(folderOne.getItems().size(), Folder.cloneDirectory(folderOne).getItems().size());

        // Test to see if the children are cloned as well.
        folderOne.add(folderTwo);
        folderOne.add(fileOne);
        folderOne.add(fileTwo);
        assertNotEquals(folderOne, Folder.cloneDirectory(folderOne));
        assertNotEquals(Folder.cloneDirectory(folderOne), Folder.cloneDirectory(folderOne));
        assertEquals(folderOne.getName(), Folder.cloneDirectory(folderOne).getName());
        assertEquals(folderOne.getItems().size(), Folder.cloneDirectory(folderOne).getItems().size());
        Folder childOne = (Folder) Folder.cloneDirectory(folderOne).getItemByName("Folder Two");
        assertEquals(folderTwo.getName(), childOne.getName());
        assertEquals(folderTwo.getItems().size(), childOne.getItems().size());
        assertEquals(fileOne.getName(), Folder.cloneDirectory(folderOne).getItemByName("File One").getName());
    }

    @Test
    void testToJson() {
        folderOne.add(fileOne);
        folderOne.add(fileTwo);
        assertTrue(folderOne.toJson(folderOne) instanceof JSONObject);
    }
}


// Code was copied from the following URL and changed to fit the needs of this project:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// /blob/master/src/main/model/WorkRoom.java