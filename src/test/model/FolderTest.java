package model;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class FolderTest {
    Folder folderOne;
    Folder folderTwo;

    @BeforeEach
    void setUp() {
        folderOne = new Folder("Folder One");
        folderTwo = new Folder("Folder Two");
    }

    @Test
    void testGetName() {
        assertEquals("Folder One", folderOne.getName());
        assertEquals("Folder Two", folderTwo.getName());
    }

    @Test
    void testSetName() {
        folderOne.setName("New Folder One");
        folderTwo.setName("New Folder Two");
        assertEquals("New Folder One", folderOne.getName());
        assertEquals("New Folder Two", folderTwo.getName());
    }

}