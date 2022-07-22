package ui;

import model.HandleCommand;
import model.MyFile;
import model.Folder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Folder root = new Folder("root");
        boolean isRunning = true;
        Scanner terminal = new Scanner(System.in);
        System.out.println("Welcome to Aghilan File System");

        while (isRunning) {
            String command = terminal.nextLine();
            if (command.startsWith("exit")) {
                isRunning = false;
            }
            HandleCommand.handleCommand(command, root);
        }
    }
}
