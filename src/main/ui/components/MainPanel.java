package ui.components;

import model.Folder;
import model.FolderItemInterface;
import ui.App;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    Folder root;
    Folder currentDirectory;
    MyFrame frame;
    App app;

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

    public void addAllItems() {
        if (currentDirectory == null) {
            System.out.println("Current directory is null");
            return;
        }

        for (FolderItemInterface item : currentDirectory.getItems()) {
            add(new Item(item.getName(), item, currentDirectory, this, app));
        }
    }

    public void changeDirectory(Folder newFolder) {
        this.currentDirectory = newFolder;
        removeAll();
        rerenderUI();
    }

    public void removeItem() {
        removeAll();
        rerenderUI();
    }

    public void rerenderUI() {
        addAllItems();
        repaint();
    }

}
