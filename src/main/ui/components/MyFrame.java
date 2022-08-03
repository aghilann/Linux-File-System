package ui.components;

import model.Folder;
import ui.App;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    Folder root;
    Folder currentDirectory;
    MainPanel mainPanel;
    AddItem createFolderPanel;
    SaveFolder saveFolderPanel;
    App app;

    public MyFrame(Folder root, Folder currentDirectory, App app) {
        super("Aghilan's File System");
        setLayout(new GridLayout(4, 4));
        this.currentDirectory = currentDirectory;
        this.root = root;
        this.app = app;
        this.mainPanel = new MainPanel(root, currentDirectory, this, app);
        this.createFolderPanel = new AddItem(this.mainPanel);
        this.saveFolderPanel = new SaveFolder(app);
        add(mainPanel);
        add(createFolderPanel);
        add(saveFolderPanel);
        setVisible(true);
        pack();
    }

    public Folder getRoot() {
        return root;
    }
}