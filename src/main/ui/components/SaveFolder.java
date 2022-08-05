package ui.components;

import ui.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Button to save the current folder
public class SaveFolder extends JPanel implements MouseListener {
    JButton saveButton;
    App app;

    // REQUIRES: app != null
    // EFFECTS: constructs a SaveFolder object
    public SaveFolder(App app) {
        this.app = app;
        saveButton = new JButton("Save");
        saveButton.addMouseListener(this);
        decorateButton();
        add(saveButton);
    }

    // REQUIRES: saveButton != null
    // EFFECTS: decorates the save button
    public void decorateButton() {
        saveButton.setBackground(Color.GREEN);
        saveButton.setForeground(Color.BLACK);
        saveButton.setFont(new Font("Arial", Font.BOLD, 20));
        saveButton.setPreferredSize(new Dimension(200, 50));
    }

    // REQUIRES: app != null
    // MODIFIES: this, app
    // EFFECTS: saves the current directory
    @Override
    public void mouseClicked(MouseEvent e) {
        app.saveFolder();
        JOptionPane.showMessageDialog(this, "Folder saved!");
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
