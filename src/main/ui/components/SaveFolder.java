package ui.components;

import model.Folder;
import ui.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SaveFolder extends JPanel implements MouseListener {
    JButton saveButton;
    App app;

    public SaveFolder(App app) {
        this.app = app;
        saveButton = new JButton("Save");
        saveButton.addMouseListener(this);
        decorateButton();
        add(saveButton);
    }

    public void decorateButton() {
        // make the Button large and green
        saveButton.setBackground(Color.GREEN);
        saveButton.setForeground(Color.BLACK);
        saveButton.setFont(new Font("Arial", Font.BOLD, 20));
        saveButton.setPreferredSize(new Dimension(200, 50));
    }

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
