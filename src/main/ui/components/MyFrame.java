package ui.components;

import model.Event;
import model.EventLog;
import model.Folder;
import ui.App;

import javax.swing.*;
import java.awt.*;

// Frame which contains the MainPanel and all other components of the program
public class MyFrame extends JFrame {
    Folder root;
    Folder currentDirectory;
    MainPanel mainPanel;
    AddItem createFolderPanel;
    SaveFolder saveFolderPanel;
    App app;
    LoadFiles loadFilesPanel;

    // REQUIRES: root is a valid Folder object
    // MODIFIES: this
    // EFFECTS: constructs a MyFrame object with root as the root folder and all the panels
    public MyFrame(Folder root, Folder currentDirectory, App app) {
        super("Aghilan's File System");
        setLayout(new GridLayout(4, 4));
        this.loadFilesPanel = new LoadFiles(app, this);
        this.currentDirectory = currentDirectory;
        this.root = root;
        this.app = app;
        this.mainPanel = new MainPanel(root, currentDirectory, this, app);
        this.createFolderPanel = new AddItem(this.mainPanel);
        this.saveFolderPanel = new SaveFolder(app);
        add(loadFilesPanel);
        add(mainPanel);
        add(createFolderPanel);
        add(saveFolderPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    // EFFECTS: returns the root folder
    public Folder getRoot() {
        return root;
    }

    // MODIFIES: this
    // EFFECTS: changes the current directory to the one specified by the parameter and updates the panel
    public void setCurrentDirectory(Folder currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    // EFFECTS: returns the current directory
    public MainPanel getMainPanel() {
        return mainPanel;
    }

    // EFFECTS: returns the createFolderPanel
    public AddItem getCreateFolderPanel() {
        return createFolderPanel;
    }

    // EFFECTS: returns the saveFolderPanel
    public SaveFolder getSaveFolderPanel() {
        return saveFolderPanel;
    }

    public void onClose() {
        EventLog.getInstance().iterator().forEachRemaining(Event -> System.out.println(Event.toString()));
    }
}