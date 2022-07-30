package ui;

import model.HandleCommand;
import model.Folder;

import java.io.IOException;
import java.util.Scanner;
import persistence.JsonReader;

// Runs program on initialization and contains methods to output to console
public class App {
    Folder currentDirectory = new Folder("root");
    Folder root;
    boolean isRunning = true;
    Scanner terminal = new Scanner(System.in);
    private static final String JSON_STORE = "./data/fileData.json";
    private JsonReader jsonReader;

    public App() {
        jsonReader = new JsonReader(JSON_STORE);
        // Ask the user if they would like to start from a saved state or start from scratch
        System.out.println("Would you like to start from a saved state or start from scratch?");
        System.out.println("1. Start from saved state");
        System.out.println("2. Start from scratch");
        int choice = terminal.nextInt();
        shouldLoad(choice);
        loadFolder();
        root = currentDirectory;
        runApplication();
    }

    private void shouldLoad(int choice) {
        if (choice == 1) {
            loadFolder();
        }
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
                continue;
            }

            if (!command.equals("")) {
                HandleCommand.handleCommand(command, currentDirectory, root);
            }

        }

        System.out.println("Would you like to save your changes? (y/n)");
        String answer = terminal.nextLine();
    }

    // EFFECTS: prints the given string to the console
    public static void printGivenString(String string) {
        System.out.println(string);
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadFolder() {
        try {
            currentDirectory = jsonReader.read();
            System.out.println("Loaded " + currentDirectory.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
