package ui;

import model.HandleCommand;
import model.Folder;

import java.util.Scanner;

// Runs program on initialization and contains methods to output to console
public class App {
    Folder root = new Folder("root");
    boolean isRunning = true;
    Scanner terminal = new Scanner(System.in);

    public App() {
        runApplication();
    }

    // MODIFIES: this
    // EFFECTS: runs the program
    private void runApplication() {
        System.out.println("Welcome to Aghilan's File system!");
        while (isRunning) {
            String command = terminal.nextLine();
            if (command.startsWith("exit")) {
                isRunning = false;
                continue;
            }
            HandleCommand.handleCommand(command, root);
        }
    }


    // EFFECTS: prints the given string to the console
    public static void printGivenString(String string) {
        System.out.println(string);
    }

}
