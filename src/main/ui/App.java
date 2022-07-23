package ui;

import model.HandleCommand;
import model.Folder;

import java.util.Scanner;

public class App {
    Folder root = new Folder("root");
    boolean isRunning = true;
    Scanner terminal = new Scanner(System.in);

    public void runApplication() {
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

    public static void printGivenString(String string) {
        System.out.println(string);
    }

}
