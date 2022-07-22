package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class HandleCommandTest {

    Folder folderOne;
    Folder folderTwo;
    MyFile fileOne;
    MyFile fileTwo;

    @BeforeEach
    public void setUp() {
        folderOne = new Folder("folderOne");
        folderTwo = new Folder("folderTwo");
        fileOne = new MyFile("MyFile One", "Content One");
        fileTwo = new MyFile("MyFile Two", "Content Two");
    }

    // TEST all the private methods called by handleCommand()

    @Test
    public void testHandleMakeDirectory() {
        String itemName = "mkdir newFolder";
        HandleCommand.handleCommand(itemName, folderOne);
        assertEquals(1, folderOne.getItems().size());
        assertTrue(folderOne.getItemByName("newFolder") instanceof Folder);

    }

    @Test
    public void testHandleTouch() {
        String itemName = "touch newFile";
        HandleCommand.handleCommand(itemName, folderOne);
        assertEquals(1, folderOne.getItems().size());
        assertTrue(folderOne.getItemByName("newFile") instanceof MyFile);
    }

    @Test
    public void testHandleList() {
        String itemName = "ls";
        HandleCommand.handleCommand(itemName, folderOne);
        // TODO: How do you test this?
    }

    @Test
    public void testHandleChangeDirectory() {
        String itemName = "cd newFolder";
        HandleCommand.handleCommand(itemName, folderOne);
        assertEquals(0, folderOne.getItems().size());

        itemName = "cd folderTwo";
        folderTwo.add(fileOne);
        folderTwo.add(fileTwo);
        folderOne.add(folderTwo);
        HandleCommand.handleCommand(itemName, folderOne);
        assertEquals(2, folderTwo.getItems().size());
        assertEquals(fileOne, folderTwo.getItemByName("MyFile One"));
        assertEquals(fileTwo, folderTwo.getItemByName("MyFile Two"));
    }




}
