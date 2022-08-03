package ui.components;

import model.Folder;
import model.FolderItemInterface;

import javax.swing.*;
import java.awt.*;

public class Item extends JPanel {
    private String name;
    private FolderItemInterface item;
    private JLabel UIName;
    private Folder currentDirectory;
    private MainPanel parentFrame;

    public Item(String name, FolderItemInterface item, Folder currentDirectory, MainPanel parentFrame) {
        this.item = item;
        this.name = name;
        this.UIName = new JLabel(name);
        this.currentDirectory = currentDirectory;
        this.parentFrame = parentFrame;
        configUIName();
        addButtons();
    }

    private void configUIName() {
        UIName.setFont(new Font("Arial", Font.BOLD, 20));
        UIName.setForeground(new java.awt.Color(0, 0, 0));
        add(UIName);
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

    private void decorate() {
        setLayout(new BorderLayout());
        add(UIName, BorderLayout.CENTER);
        setPreferredSize(new Dimension(200, 50));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void handleChangeDirectory(JButton button) {
        Folder folder = (Folder) item;
        System.out.println("Opening folder: " + folder.getName() + " from " + currentDirectory.getName());
        Folder newFolder = folder.changeDirectory(name, currentDirectory);
        System.out.println("New folder: " + currentDirectory.getName());
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
