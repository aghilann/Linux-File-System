package model;

public class HandleCommand {

    // REQUIRES: command is not an empty string
    // MODIFIES: currentDirectory
    // EFFECTS: performs the operation given in the command
    public static void handleCommand(String command, Folder currentDirectory) {
        if (command.startsWith("mkdir")) {
            handleMakeDirectory(command, currentDirectory);
        } else if  (command.startsWith("touch")) {
            handleTouch(command, currentDirectory);
        } else if (command.startsWith("ls")) {
            handleList(currentDirectory);
        } else if (command.startsWith("cd")) {
            handleChangeDirectory(command, currentDirectory);
        } else if (command.startsWith("rm")) {
            handleRemove(command, currentDirectory);
        } else {
            System.out.println("Invalid command");
        }
    }

    // REQUIRES: command is not an empty string
    // MODIFIES: currentDirectory
    // EFFECTS: removed the item with the given name from the current directory if exists
    private static void handleRemove(String command, Folder currentDirectory) {
        String itemName = command.substring(3);
        if (currentDirectory.doesNotContainItem(itemName)) {
            System.out.println("No such file or directory");
        } else {
            currentDirectory.remove(currentDirectory.getItemByName(itemName));
            System.out.println("Removed " + itemName);
        }
    }

    // REQUIRES: command after cd is not an empty string
    // MODIFIES: currentDirectory
    // EFFECTS: changes the current directory to the given directory
    private static void handleChangeDirectory(String command, Folder currentDirectory) {
        String folderName = command.substring(3);
        currentDirectory.changeDirectory(folderName);
    }

    // REQUIRES: command after ls is not an empty string
    // MODIFIES: currentDirectory
    // EFFECTS: prints the names of all the files in the current directory
    private static void handleList(Folder currentDirectory) {
        System.out.println("Listing files");
        currentDirectory.getItems()
                .stream()
                .map(FolderItemInterface::getName)
                .forEach(System.out::println);
    }

    // REQUIRES: command after touch is not an empty string
    // MODIFIES: currentDirectory
    // EFFECTS: creates a new file in the current directory
    private static void handleTouch(String command, Folder currentDirectory) {
        MyFile newFile = new MyFile(command.substring(6), "");
        currentDirectory.add(newFile);
        System.out.println("Creating a file named" + command.substring(5));
    }

    // REQUIRES: command after mkdir is not an empty string
    // MODIFIES: currentDirectory
    // EFFECTS: creates a new folder in the current directory
    private static void handleMakeDirectory(String command, Folder currentDirectory) {
        Folder newDirectory = new Folder(command.substring(6));
        currentDirectory.add(newDirectory);
        System.out.println("Creating a folder named" + command.substring(5));
    }
}
