package model;

import org.json.JSONArray;
import org.json.JSONObject;
import ui.App;


import java.util.ArrayList;


// Folder class with name and children of type Folder and File
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

    // REQUIRES: Item with name in the current directory
    // MODIFIES: this
    // EFFECTS: removes the item with the given name from this Folder
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
    public Folder changeDirectory(String name, Folder currentDirectory) {
        boolean isInFolder = false;
        for (FolderItemInterface item : this.items) {
            if (item.getName().equals(name)) {
                if (item instanceof Folder) {
                    currentDirectory = cloneDirectory((Folder) item);
                    isInFolder = true;
                    App.printGivenString("Changed directory to " + item.getName());
                } else {
                    isInFolder = true;
                    App.printGivenString("Cannot change directory to a file");
                }
            }
        }
        if (!isInFolder) {
            App.printGivenString("Item does not exist in this folder");
        }
        return currentDirectory;
    }

    // EFFECTS: creates a clone of the directory and it's children
    public static Folder cloneDirectory(Folder folder) {
        Folder newFolder = new Folder(folder.name);
        for (FolderItemInterface item : folder.items) {
            if (item instanceof Folder) {
                newFolder.items.add(cloneDirectory((Folder) item));
            } else {
                newFolder.items.add(item);
            }
        }
        return newFolder;
    }

    // EFFECTS: returns a JSON representation of this Folder
    public JSONObject toJson(FolderItemInterface item) {
        JSONObject json = new JSONObject();
        if (item instanceof Folder) {
            json.put("type", "folder");
            json.put("name", item.getName());
            json.put("items", itemsToJson(((Folder) item).getItems()));
        } else {
            json.put("type", "file");
            json.put("name", item.getName());
            json.put("content", ((MyFile) item).getContent());
        }

        return json;
    }

    // EFFECTS: returns a JSON of an array of all the items in this Folder
    private JSONArray itemsToJson(ArrayList<FolderItemInterface> items) {
        JSONArray jsonArray = new JSONArray();

        for (FolderItemInterface item : items) {
            jsonArray.put(toJson(item));
        }

        return jsonArray;
    }

}


