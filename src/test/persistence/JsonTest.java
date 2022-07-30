package persistence;

import model.Folder;
import model.FolderItemInterface;
import model.MyFile;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkItem(String name, FolderItemInterface item) {
        assertEquals(name, item.getName());
    }
}

// Code was taken from

// https://github.students.cs.ubc.ca/CPSC210
// /JsonSerializationDemo/blob/master/src/test/persistence/JsonTest.java
