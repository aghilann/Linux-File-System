package persistence;

import model.Folder;
import model.FolderItemInterface;
import model.MyFile;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;


// Represents a reader that reads Files/Folders from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads folder from JSON file and returns it;
    // throws IOException if an error occurs reading data from file
    public Folder read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFolder(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses folder from JSON object and returns it
    private Folder parseFolder(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Folder curDir = new Folder(name);
        addItems(curDir, jsonObject.getJSONArray("items"));
        return curDir;
    }

    // MODIFIES: folder
    // EFFECTS: parses folders/files from JSON object and adds them to folder
    private void addItems(Folder curFolder, JSONArray jsonArray) {
        for (Object json : jsonArray) {
            JSONObject item = (JSONObject) json;
            addItem(curFolder, item);
        }
    }

    // MODIFIES: folder
    // EFFECTS: parses folder/file from JSON object and adds it to folder
    private void addItem(Folder currentFolder, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String type = jsonObject.getString("type");
        FolderItemInterface item;

        if (type.equals("folder")) {
            item = new Folder(name);
            currentFolder.add(item);
            // Add the items and and change the JSON object the subfolder is in
            addItems((Folder) item, (JSONArray) jsonObject.get("items"));
        } else {
            item = new MyFile(name, jsonObject.getString("content"));
            currentFolder.add(item);
        }
    }
}


// This was taken from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob
// /master/src/main/persistence/JsonReader.java