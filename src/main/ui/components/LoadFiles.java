package ui.components;

import ui.App;

import javax.swing.*;

// Bug Button that allows the user to load in the saved files
public class LoadFiles extends JButton {

    // REQUIRES: App != null
    // EFFECTS: constructs a LoadFiles object
    public LoadFiles(App app, MyFrame myFrame) {
        super("Load Files");
        addActionListener(e -> {
            app.loadFolder();
        });
    }
}
