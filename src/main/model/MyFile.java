package model;


public class MyFile implements FolderItemInterface {
    String name;
    String content;

    // EFFECTS: constructs a new MyFile with the given name and content
    public MyFile(String name, String content) {
        this.name = name;
        this.content = content;
    }

    // EFFECTS: returns the name of this MyFile
    public String getName() {
        return name;
    }

    // EFFECTS: returns the content of this MyFile
    public String getContent() {
        return content;
    }

    // REQUIRES: item isn't an empty string
    // MODIFIES: this
    // EFFECTS: changed the name of the current folder to the given name
    public void setName(String name) {
        this.name = name;
    }

    // REQUIRES: item isn't an empty string
    // MODIFIES: this
    // EFFECTS: changed the content of the current folder to the given content
    public void setContent(String content) {
        this.content = content;
    }
}
