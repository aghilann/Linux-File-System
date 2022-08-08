package ui.components;

import model.Folder;
import model.FolderItemInterface;
import ui.App;

import javax.swing.*;
import java.awt.*;

// Component for displaying file/folder names and allowing the user to create new files/folders
public class Item extends JPanel {
    private final String name;
    private final FolderItemInterface item;
    private final JLabel labelName;
    private final Folder currentDirectory;
    private final MainPanel parentFrame;
    private final App app;
    // EFFECTS: constructs an Item with the given name, item, currentDirectory, parentFrame, and app
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

    // REQUIRES: labelName is not null
    // MODIFIES: this
    // EFFECTS: configures the UI for the name of the item
    private void configUIName() {
        labelName.setFont(new Font("Arial", Font.BOLD, 20));
        labelName.setForeground(new java.awt.Color(0, 0, 0));
        if (item instanceof Folder) {
            ImageIcon icon = new ImageIcon("static/folder.png");
            Image imageResized = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            add(new JLabel(new ImageIcon(imageResized)));
        } else {
            ImageIcon icon = new ImageIcon("static/file.png");
            Image imageResized = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            add(new JLabel(new ImageIcon(imageResized)));
        }
        add(labelName);
    }

    // REQUIRES: labelName is not null
    // MODIFIES: this
    // EFFECTS: adds the buttons to the item
    private void addButtons() {
        if (item instanceof Folder) {
            addChangeDirectoryButton();
        }
        addRemoveButton();
    }

    // REQUIRES: labelName is not null
    // MODIFIES: this
    // EFFECTS: adds the change directory button to the item
    private void addChangeDirectoryButton() {
        JButton button = new JButton("Open");
        button.addActionListener(e -> handleChangeDirectory(button));
        add(button);
    }

    // REQUIRES: labelName is not null
    // MODIFIES: this
    // EFFECTS: adds the remove button to the item
    private void addRemoveButton() {
        JButton button = new JButton("Remove");
        button.addActionListener(e -> handleRemove(button));
        button.setBackground(Color.RED);
        add(button);
    }

    // REQUIRES: button is not null
    // MODIFIES: this
    // EFFECTS: handles the change directory button being clicked
    private void handleChangeDirectory(JButton button) {
        Folder folder = (Folder) item;
        System.out.println("Opening folder: " + folder.getName() + " from " + currentDirectory.getName());
        Folder newFolder = folder.changeDirectory(name, currentDirectory);
        System.out.println("New folder: " + currentDirectory.getName());
        app.setCurrentDirectory(newFolder);
        parentFrame.changeDirectory(newFolder);
        System.out.println("Updated UI");
    }

    // REQUIRES: button is not null
    // MODIFIES: this
    // EFFECTS: handles the remove button being clicked
    private void handleRemove(JButton button) {
        System.out.println("Removing item: " + item.getName() + " from " + currentDirectory.getName());
        currentDirectory.remove(currentDirectory.getItemByName(name));
        parentFrame.removeItem();
        System.out.println("Updated UI");
    }
}
