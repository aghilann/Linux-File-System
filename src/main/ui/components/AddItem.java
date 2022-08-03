package ui.components;

import model.Folder;
import model.FolderItemInterface;
import model.MyFile;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AddItem extends JPanel {
    private final TextField name;
    private final JButton createButton;
    private final Folder currentDirectory;
    private final MainPanel parentFrame;
    private final JCheckBox isFolder;

    public AddItem(Folder currentDirectory, MainPanel parentFrame) {
        this.parentFrame = parentFrame;
        this.currentDirectory = currentDirectory;
        name = new TextField();
        createButton = new JButton("Create");
        isFolder = new JCheckBox("Create Folder (y/n)");
        decorateCheckBox();
        setLayout(new BorderLayout());
        decorateButton();
        decorateTextField();
        add(name, BorderLayout.CENTER);
        add(createButton, BorderLayout.SOUTH);
        add(isFolder, BorderLayout.NORTH);
        setVisible(true);
    }

    private void decorateCheckBox() {
        isFolder.setFont(new Font("Arial", Font.BOLD, 20));
    }

    private void handleCreate() {
        String name = this.name.getText();
        if (currentDirectory.doesNotContainItem(name)) {
            if (isFolder.isEnabled()) {
                currentDirectory.add(new Folder(name));
            } else {
                currentDirectory.add(new MyFile(name, ""));
            }
            System.out.println("Created " + name);
        } else {
            JOptionPane.showMessageDialog(this, "Item with name already exists");
        }
        parentFrame.removeItem();
    }

    private void decorateTextField() {
        name.setText("Enter the name of the folder");
        name.setBackground(Color.WHITE);
        name.setForeground(Color.BLACK);
        name.setFont(new Font("Arial", Font.BOLD, 20));
        name.setPreferredSize(new Dimension(200, 50));
    }

    private void decorateButton() {
        createButton.addActionListener(e -> handleCreate());
        createButton.setFont(new Font("Arial", Font.BOLD, 20));
        createButton.setBackground(new Color(0, 191, 255));
        createButton.setPreferredSize(new Dimension(100, 50));
        createButton.setOpaque(true);
        createButton.setBorderPainted(false);
    }
}
