
package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Folder wr = new Folder("root");
            JsonWriter writer = new JsonWriter("./data/my\0notRealName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // This is expected
        }
    }

    @Test
    void testFolderEmptyItems() {
        try {
            Folder root = new Folder("root");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFolder.json");
            writer.open();
            writer.write(root);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFolder.json");
            root = reader.read();
            assertEquals("root", root.getName());
            assertEquals(0, root.getItems().size());

        } catch (IOException e) {
            fail("IOException shouldnt have happened");
        }
    }

    @Test
    void testWriterWithItems() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterWithItems.json");
            // Creating files and folders to write to the file
            Folder root = new Folder("root");
            Folder folderOne = new Folder("folderOne");
            Folder folderTwo = new Folder("folderTwo");
            MyFile fileOne = new MyFile("fileOne", "contentOne");
            MyFile fileTwo = new MyFile("fileTwo", "contentTwo");

            // Establishing the structure of the file
            folderOne.add(fileOne);
            folderOne.add(fileTwo);
            root.add(folderOne);
            root.add(folderTwo);

            // Writing the file
            writer.open();
            writer.write(root);
            writer.close();

            // Reading the file
            JsonReader reader = new JsonReader("./data/testWriterWithItems.json");
            root = reader.read();
            assertEquals("root", root.getName());
            assertEquals(2, root.getItems().size());
            assertEquals("folderOne", root.getItemByName("folderOne").getName());
            assertEquals("folderTwo", root.getItemByName("folderTwo").getName());

            // Checking the contents of the folderOne
            folderOne = (Folder) root.getItemByName("folderOne");
            assertEquals(2, folderOne.getItems().size());
            assertEquals("fileOne", folderOne.getItemByName("fileOne").getName());
            assertEquals("fileTwo", folderOne.getItemByName("fileTwo").getName());

            // Checking the contents of the folderTwo
            folderTwo = (Folder) root.getItemByName("folderTwo");
            assertEquals(0, folderTwo.getItems().size());

        } catch (IOException e) {
            fail("IOException should not have happened");
        }
    }
}


// Code was modelled to fit the needs of my project.
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// /blob/master/src/test/persistence/JsonWriterTest.java