package ui.components;

import javax.swing.*;
import java.awt.*;

// Button to load the previous folder
public class ShoudLoad extends JCheckBox {

    // EFFECTS: constructs a ShoudLoad object giving users the option to load the folder
    public ShoudLoad() {
        super("Would you like to load your prior file system?");
    }

    // REQUIRES: this is not null
    // MODIFIES: this
    // EFFECTS: returns the value of the checkbox
    public void decorate() {
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setFont(new Font("Arial", Font.BOLD, 14));
    }
}
