package ui.components;

import model.Folder;
import model.MyFile;

import javax.swing.*;
import java.awt.*;

// Component for adding a new item to the file system
public class AddItem extends JPanel {
    private TextField name;
    private JButton createButton;
    private MainPanel parentFrame;
    private JCheckBox isFolder;

    // REQUIRES: parentFrame != null
    // MODIFIES: this
    // EFFECTS: constructs a new AddItem object
    public AddItem(MainPanel parentFrame) {
        this.parentFrame = parentFrame;
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

    // REQUIRES: parentFrame != null
    // MODIFIES: this
    // EFFECTS: decorates the text field
    private void decorateCheckBox() {
        isFolder.setFont(new Font("Arial", Font.BOLD, 20));
    }

    // REQUIRES: parentFrame != null
    // MODIFIES: this
    // EFFECTS: Creates the new FolderItemInterface object and adds it to the parentFrame/currentDirectory
    private void handleCreate() {
        String name = this.name.getText();
        if (parentFrame.getCurrentDirectory().doesNotContainItem(name)) {
            if (isFolder.isSelected()) {
                parentFrame.getCurrentDirectory().add(new Folder(name));
            } else {
                parentFrame.getCurrentDirectory().add(new MyFile(name, ""));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Item with name already exists");
        }
        parentFrame.removeItem();
    }

    // REQUIRES: parentFrame != null
    // MODIFIES: this
    // EFFECTS: decorates the button
    private void decorateTextField() {
        name.setText("Enter the name of the folder");
        name.setBackground(Color.WHITE);
        name.setForeground(Color.BLACK);
        name.setFont(new Font("Arial", Font.BOLD, 20));
        name.setPreferredSize(new Dimension(200, 50));
    }

    // REQUIRES: parentFrame != null
    // MODIFIES: this
    // EFFECTS: decorates the button
    private void decorateButton() {
        isFolder.setSelected(true);
        createButton.addActionListener(e -> handleCreate());
        createButton.setFont(new Font("Arial", Font.BOLD, 20));
        createButton.setBackground(new Color(0, 191, 255));
        createButton.setPreferredSize(new Dimension(100, 50));
        createButton.setOpaque(true);
        createButton.setBorderPainted(false);
    }
}
