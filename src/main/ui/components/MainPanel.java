package ui.components;

import model.Folder;
import model.FolderItemInterface;
import ui.App;

import javax.swing.*;
import java.awt.*;

// Panel which contains the files/folders with cd/rm buttons
public class MainPanel extends JPanel {
    Folder root;
    Folder currentDirectory;
    MyFrame frame;
    App app;

    // REQUIRES: root is a valid Folder object
    // MODIFIES: this
    // EFFECTS: constructs a MainPanel object with root as the root folder
    public MainPanel(Folder root, Folder currentDirectory, MyFrame frame, App app) {
        this.root = currentDirectory;
        this.currentDirectory = currentDirectory;
        this.frame = frame;
        this.app = app;
        setLayout(new GridLayout(4, 4));
        setBorder(BorderFactory.createLineBorder(Color.RED));
        addAllItems();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds all the items in the current directory to the panel
    public void addAllItems() {
        if (currentDirectory == null) {
            return;
        }

        for (FolderItemInterface item : currentDirectory.getItems()) {
            add(new Item(item.getName(), item, currentDirectory, this, app));
        }
    }

    // MODIFIES: this
    // EFFECTS: Changed the current directory to the one specified by the parameter and updates the panel
    public void changeDirectory(Folder newFolder) {
        this.currentDirectory = newFolder;
        removeAll();
        rerenderUI();
    }

    // MODIFIES: this
    // EFFECTS: removes all elements then renders the UI
    public void removeItem() {
        removeAll();
        rerenderUI();
    }

    // MODIFIES: this
    // EFFECTS: renders the panel
    public void rerenderUI() {
        addAllItems();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: returns the current directory
    public Folder getCurrentDirectory() {
        return currentDirectory;
    }

    // MODIFIES: this
    // EFFECTS: sets the current directory to the one specified by the parameter
    public void setCurrentDirectory(Folder currentDirectory) {
        this.currentDirectory = currentDirectory;
    }
}
