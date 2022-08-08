package ui;

import model.HandleCommand;
import model.Folder;

import java.io.IOException;
import java.util.Scanner;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.components.MyFrame;

// Runs program on initialization and contains methods to output to console
public class App {
    Folder currentDirectory = new Folder("root");
    Folder root;
    Scanner terminal = new Scanner(System.in);
    private static final String JSON_STORE = "./data/FileSystem.json";
    private final JsonReader jsonReader;
    private final JsonWriter jsonWriter;
    private final MyFrame myFrame;

    // EFFECTS: constructs a new App
    public App() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        myFrame = new MyFrame(root, currentDirectory, this);
        this.root = currentDirectory;
    }

    // EFFECTS: prints the given string to the console
    public static void printGivenString(String string) {
        System.out.println(string);
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void loadFolder() {
        try {
            System.out.println("Loading workroom from file...");
            currentDirectory = jsonReader.read();
            root = currentDirectory;
            setCurrentDirectoryAll(currentDirectory);
            System.out.println("Loaded " + currentDirectory.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the current folder to file
    public void saveFolder() {
        try {
            jsonWriter.open();
            jsonWriter.write(root);
            jsonWriter.close();
            System.out.println("Saved " + root.getName() + " to " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the current directory to the given folder
    public void setCurrentDirectory(Folder currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    // EFFECTS: gets the current directory
    public Folder getCurrentDirectory() {
        return currentDirectory;
    }

    // MODIFIES: this, everything
    // EFFECTS: sets the current directory to the given folder and for all its children
    public void setCurrentDirectoryAll(Folder currentDirectory) {
        this.currentDirectory = currentDirectory;
        myFrame.setCurrentDirectory(currentDirectory);
        myFrame.getMainPanel().setCurrentDirectory(currentDirectory);
        myFrame.getMainPanel().rerenderUI();
    }
}
