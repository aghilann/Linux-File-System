package persistence;

import model.MyFile;
import model.Folder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/IAmNotReal.json");
        try {
            Folder folder = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReadEmptyFolder() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFolder.json");
        try {
            Folder folder = reader.read();
            assertEquals("root", folder.getName());
            assertEquals(0, folder.getItems().size());
        } catch (IOException e) {
            fail("IOException expected");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderWithItems.json");
        try {
            Folder folder = reader.read();
            assertNotNull(folder);
            assertEquals("root", folder.getName());
            assertEquals(1, folder.getItems().size());
            assertNotNull(folder.getItemByName("File One"));
            super.checkItem("File One", folder.getItemByName("File One"));
        } catch (IOException e) {
            fail("IOException expected");
        }
    }
}


// Code was modelled after the following code:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/
// blob/master/src/test/persistence/JsonReaderTest.java