
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
}


// Code was modelled to fit the needs of my project.
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// /blob/master/src/test/persistence/JsonWriterTest.java