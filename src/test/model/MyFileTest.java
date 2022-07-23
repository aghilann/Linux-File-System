package model;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class MyFileTest {
    MyFile fileOne;
    MyFile fileTwo;

    @BeforeEach
    void setUp() {
        fileOne = new MyFile("MyFile One", "Content One");
        fileTwo = new MyFile("MyFile Two", "Content Two");
    }

    @Test
    void testConstructor() {
        assertEquals("MyFile One", fileOne.getName());
        assertEquals("Content One", fileOne.getContent());
        assertEquals("MyFile Two", fileTwo.getName());
        assertEquals("Content Two", fileTwo.getContent());
    }

    @Test
    void testGetName() {
        assertEquals("MyFile One", fileOne.getName());
        assertEquals("MyFile Two", fileTwo.getName());
    }

    @Test
    void testSetName() {
        fileOne.setName("New MyFile One");
        fileTwo.setName("New MyFile Two");
        assertEquals("New MyFile One", fileOne.getName());
        assertEquals("New MyFile Two", fileTwo.getName());
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
