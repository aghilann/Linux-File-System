package model;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class FileTest {
    File fileOne;
    File fileTwo;

    @BeforeEach
    void setUp() {
        fileOne = new File("File One", "Content One");
        fileTwo = new File("File Two", "Content Two");
    }

    @Test
    void testGetName() {
        assertEquals("File One", fileOne.getName());
        assertEquals("File Two", fileTwo.getName());
    }

    @Test
    void testSetName() {
        fileOne.setName("New File One");
        fileTwo.setName("New File Two");
        assertEquals("New File One", fileOne.getName());
        assertEquals("New File Two", fileTwo.getName());
    }

    @Test
    void testGetContent() {
        assertEquals("Content One", fileOne.getContent());
        assertEquals("Content Two", fileTwo.getContent());
    }

    @Test
    void testSetContent() {
        fileOne.setContent("New Content One");
        fileTwo.setContent("New Content Two");
        assertEquals("New Content One", fileOne.getContent());
        assertEquals("New Content Two", fileTwo.getContent());
    }
}
