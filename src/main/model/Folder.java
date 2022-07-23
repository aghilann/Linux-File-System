package model;

import ui.App;

import java.util.ArrayList;

public class Folder implements FolderItemInterface {
    private String name;
    private ArrayList<FolderItemInterface> items;

    // EFFECTS: constructs a new Folder with the given name
    public Folder(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    // EFFECTS: returns the name of this Folder
    public String getName() {
        return this.name;
    }

    // REQUIRES: Item with name in the current directory
    // EFFECTS: returns the item with the given name
    public FolderItemInterface getItemByName(String name) {
        for (FolderItemInterface item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    // EFFECTS: returns a list of all the items in this Folder
    public ArrayList<FolderItemInterface> getItems() {
        return items;
    }

    public void remove(FolderItemInterface item) {
        this.items.remove(item);
    }

    // REQUIRES: item is not an empty string
    // MODIFIES: this
    // EFFECTS: changed the name of the current folder to the given name
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: adds the given item to the current folder, true if successful, false if not
    public boolean add(FolderItemInterface item) {
        if (item instanceof Folder && doesNotContainItem(item.getName())) {
            Folder folderToAdd = (Folder) item;
            this.items.add(folderToAdd);
            return true;
        } else if ((item instanceof MyFile) && doesNotContainItem(item.getName())) {
            MyFile fileToAdd = (MyFile) item;
            this.items.add(fileToAdd);
            return true;
        } else {
            App.printGivenString("Item already exists in this folder");
            return false;
        }
    }

    // EFFECTS: returns true if the current folder contains an item with the given name
    public boolean doesNotContainItem(String name) {
        for (FolderItemInterface item : this.items) {
            if (item.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }


    // REQUIRES: name is in this folder
    // MODIFIES: this
    // EFFECTS: changes the current folder the user is on
    public void changeDirectory(String name) {
        for (FolderItemInterface item : this.items) {
            if (item.getName().equals(name)) {
                if (item instanceof Folder) {
                    changeAllFields((Folder) item);
                    App.printGivenString("Changed directory to " + item.getName());
                } else {
                    App.printGivenString("Cannot change directory to a file");
                }
                return;
            }
        }
        App.printGivenString("Item does not exist in this folder");
    }

    // MODIFIES: this
    // EFFECTS: changes all the fields of this folder to the given folder
    private void changeAllFields(Folder item) {
        this.name = item.name;
        this.items = item.items;
    }

}
