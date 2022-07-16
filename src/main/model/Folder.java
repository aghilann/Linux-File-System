package model;

import java.util.ArrayList;

public class Folder implements FolderItemInterface {
    private String name;
    ArrayList<FolderItemInterface> items;

    // EFFECTS: constructs a new Folder with the given name
    public Folder(String name) {
        this.name = name;
        this.items = new ArrayList<FolderItemInterface>();
    }

    // EFFECTS: returns the name of this Folder
    public String getName() {
        return this.name;
    }

    // REQUIRES: item is not an empty string
    // MODIFIES: this
    // EFFECTS: changed the name of the current folder to the given name
    public void setName(String name) {
        this.name = name;
    }

    public void addItem(FolderItemInterface item) {
        if (item instanceof Folder && doesNotContainItem(item.getName())) {
            Folder folderToAdd = (Folder) item;
            this.items.add(folderToAdd);
        } else if ((item instanceof File) && doesNotContainItem(item.getName())) {
            File fileToAdd = (File) item;
            this.items.add(fileToAdd);
        } else {
            System.out.println("Item already exists in this folder");
        }
    }

    // Check if item with name is in this folder
    public boolean doesNotContainItem(String name) {
        for (FolderItemInterface item : this.items) {
            if (item.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }




}
