package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// Contains tests to make sure the user's commands are correctly handled.
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
    @Test
    public void testHandleMakeDirectory() {
        String itemName = "mkdir newFolder";
        HandleCommand.handleCommand(itemName, folderOne, folderOne);
        assertEquals(1, folderOne.getItems().size());
        assertTrue(folderOne.getItemByName("newFolder") instanceof Folder);

    }

    @Test
    public void testHandleTouch() {
        String itemName = "touch newFile";
        HandleCommand.handleCommand(itemName, folderOne, folderOne);
        assertEquals(1, folderOne.getItems().size());
        assertTrue(folderOne.getItemByName("newFile") instanceof MyFile);
    }

    @Test
    public void testHandleList() {
        String itemName = "ls";
        HandleCommand.handleCommand(itemName, folderOne, folderOne);
        assertEquals(0, folderOne.getItems().size());
        folderOne.add(fileOne);
        folderOne.add(fileTwo);
        HandleCommand.handleCommand(itemName, folderOne, folderOne);
        assertEquals(2, folderOne.getItems().size());
    }

    @Test
    public void testHandleChangeDirectory() {
        String itemName = "cd newFolder";
        HandleCommand.handleCommand(itemName, folderOne, folderOne);
        assertEquals(0, folderOne.getItems().size());

        itemName = "cd MyFile One";
        HandleCommand.handleCommand(itemName, folderOne, folderOne);
        assertEquals(0, folderOne.getItems().size());

        itemName = "cd folderTwo";
        folderTwo.add(fileOne);
        folderTwo.add(fileTwo);
        folderOne.add(folderTwo);
        HandleCommand.handleCommand(itemName, folderOne, folderOne);
        assertEquals(2, folderTwo.getItems().size());
        assertEquals(fileOne, folderTwo.getItemByName("MyFile One"));
        assertEquals(fileTwo, folderTwo.getItemByName("MyFile Two"));
    }

    @Test
    public void testHandleRemove() {
        String itemName = "rm newFolder";
        HandleCommand.handleCommand(itemName, folderOne, folderOne);
        assertEquals(0, folderOne.getItems().size());

        folderOne.add(fileOne);
        itemName = "rm MyFile One";
        HandleCommand.handleCommand(itemName, folderOne, folderOne);
        assertEquals(0, folderOne.getItems().size());

        itemName = "rm folderTwo";
        folderOne.add(fileOne);
        folderOne.add(fileTwo);
        folderOne.add(folderTwo);
        HandleCommand.handleCommand(itemName, folderOne, folderOne);
        assertEquals(2, folderOne.getItems().size());
    }
}
