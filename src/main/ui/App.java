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
    boolean isRunning = true;
    Scanner terminal = new Scanner(System.in);
    private static final String JSON_STORE = "./data/FileSystem.json";
    private final JsonReader jsonReader;
    private final JsonWriter jsonWriter;

    // EFFECTS: constructs a new App
    public App() {
        this.root = currentDirectory;
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        shouldLoad(1); // TODO: Move the line and change from 1
        new MyFrame(this.root, currentDirectory);
    }

    // MODIFIES: this
    // EFFECTS: runs the program
    private void runApplication() {
        System.out.println("Welcome to Aghilan's File system!");
        Folder unchangedRoot = Folder.cloneDirectory(root);
        while (isRunning) {
            String command = terminal.nextLine();
            if (command.startsWith("exit")) {
                isRunning = false;
            } else if (command.startsWith("save")) {
                saveFolder();
            } else if (!command.equals("")) {
                HandleCommand.handleCommand(command, currentDirectory, root);
            }

        }
    }

    // EFFECTS: prints the given string to the console
    public static void printGivenString(String string) {
        System.out.println(string);
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadFolder() {
        try {
            System.out.println("Loading workroom from file...");
            currentDirectory = jsonReader.read();
            System.out.println("Loaded " + currentDirectory.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: determines if the user wants to start from a saved state or start from scratch
    private void shouldLoad(int choice) {
        if (choice == 1) {
            loadFolder();
        }
    }

    // EFFECTS: saves the current folder to file
    private void saveFolder() {
        try {
            jsonWriter.open();
            jsonWriter.write(currentDirectory);
            jsonWriter.close();
            System.out.println("Saved " + currentDirectory.getName() + " to " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}
