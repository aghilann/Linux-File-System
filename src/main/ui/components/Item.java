package ui.components;

import model.Folder;
import model.FolderItemInterface;
import ui.App;

import javax.swing.*;
import java.awt.*;

public class Item extends JPanel {
    private final String name;
    private final FolderItemInterface item;
    private final JLabel labelName;
    private final Folder currentDirectory;
    private final MainPanel parentFrame;
    private final App app;

    public Item(String name, FolderItemInterface item, Folder currentDirectory, MainPanel parentFrame, App app) {
        this.item = item;
        this.name = name;
        this.labelName = new JLabel(name);
        this.currentDirectory = currentDirectory;
        this.parentFrame = parentFrame;
        this.app = app;
        configUIName();
        addButtons();
    }

    private void configUIName() {
        labelName.setFont(new Font("Arial", Font.BOLD, 20));
        labelName.setForeground(new java.awt.Color(0, 0, 0));
        add(labelName);
    }

    private void addButtons() {
        if (item instanceof Folder) {
            addChangeDirectoryButton();
        }
        addRemoveButton();
    }

    private void addChangeDirectoryButton() {
        JButton button = new JButton("Open");
        button.addActionListener(e -> handleChangeDirectory(button));
        add(button);
    }

    private void addRemoveButton() {
        JButton button = new JButton("Remove");
        button.addActionListener(e -> handleRemove(button));
        button.setBackground(Color.RED);
        add(button);
    }

    private void handleChangeDirectory(JButton button) {
        Folder folder = (Folder) item;
        System.out.println("Opening folder: " + folder.getName() + " from " + currentDirectory.getName());
        Folder newFolder = folder.changeDirectory(name, currentDirectory);
        System.out.println("New folder: " + currentDirectory.getName());
        app.setCurrentDirectory(newFolder);
        parentFrame.changeDirectory(newFolder);
        System.out.println("Updated UI");
    }


    private void handleRemove(JButton button) {
        System.out.println("Removing item: " + item.getName() + " from " + currentDirectory.getName());
        currentDirectory.remove(currentDirectory.getItemByName(name));
        parentFrame.removeItem();
        System.out.println("Updated UI");
    }
}
