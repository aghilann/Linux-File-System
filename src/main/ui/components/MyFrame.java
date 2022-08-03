package ui.components;

import model.Folder;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    Folder root;
    Folder currentDirectory;
    MainPanel mainPanel;
    AddItem createFolderPanel;

    public MyFrame(Folder root, Folder currentDirectory) {
        super("Aghilan's File System");
        this.currentDirectory = currentDirectory;
        this.root = root;
        setLayout(new GridLayout(4, 4));
        this.mainPanel = new MainPanel(root, currentDirectory, this);
        this.createFolderPanel = new AddItem(this.currentDirectory, this.mainPanel);
        add(mainPanel);
        add(createFolderPanel);
        setVisible(true);
        pack();
    }

    public Folder getRoot() {
        return root;
    }

    public void setRoot(Folder root) {
        this.root = root;
    }
}