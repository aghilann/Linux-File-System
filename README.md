# File System

## User Stories

This application is made to mimic the **functionality of a file system**.
There is no real target audience for this project, 
but it is a good starting point for learning how to use the **file system** works.
This project interests me since I think the recursive nature of the file system would make 
this project difficult to implement and it will teach me a lot aboout Object Oriented Programming.


## User Features:
 - As a User I can create and delete files
 - As a User I can create folders which are also allowed to contain folders anf files
 - As a User I can rename files and folders
 - As a User I can use common terminal commands like ls, cd, mkdir, rm with the exeption of cd .. which allows the user to go up in the file system.

## User Stories for Phase 0 and 1:
- As a User I can create a file within a folder.
- As a User I can create a folder within a folder.
- As a User I can enter into a folder
- As a User I can list the folders
- As a User I can remove a file/folder

## User Stories for Phase 2:
- As a user, I want to be able to save my file system at any time (overrides prior).
- As a user, I want to be able to load my file system at the beginning or start from scratch.


## Instructions to use Application - Phase 2
- Assumes basic knowledge of file system and it's command
- "cd <FolderName>" changes your directory to the directory with the given name if it exists
- "ls" just prints the names of all the folders/files in the current directory
- "mkdir <FolderName>" Creates a folder with the given name if it doesnt already exist
- "rm" <ItemName> removed item with given name if exists
- "touch <FileName> creates a file with given name if it doesnt already exist"
- Select 1 at the beginning of the program to load a file system or 2 to create a new file system
- Write "save" at any moment to write the current file system to the saved file system

## Instructions for Grader - Phase 3

 - You can press load at any time to load a saved file system
 - You can press save at any time to save the current file system
 - You can view the files and folders in your current directory
 - You can press open to "cd" into a folder
 - You can press remove to delete a file or folder (and all of its contents)
   - You must press save if you want this change to persist
 - You can add files/folders to your current directory
 - To find the images used in the project, go to static folder. (They are file/folder icons)
    - The graphics I used were a popup with a giant green checkmark image
    - The file/folder also have icons which are just png images

## Phase 4: Task 2
- The Events are Logged when new Folders/Files are loaded/created
  - ```
    Wed Aug 10 12:30:10 PDT 2022
    Added Folder Homework to root
    Wed Aug 10 12:30:10 PDT 2022
    Added File Math to Homework
    ``` 
- The Events are logged when Folders/Files are removed
  - ```
    Wed Aug 10 12:33:53 PDT 2022
    Removed Math from Homework
    Wed Aug 10 12:33:58 PDT 2022
    Removed Business Ethics from Business
    ```

## Phase 4: Task 3
Currently, I pass down the current directory as an argument to all components I would instead use the Singleton 
Design Pattern so that the current directory is only ever one object, 
and I can reduce how much data I have to drill down the component hierarchy. It would act
as a Single Source of Truth for the current directory.

I would also use Observer Pattern to notify the components when a change occurs, so they can
update themselves by accessing the current directory which is located in the Singleton.

I would also create helper functions to do a lot of the initializing and setting up of the components
in some Components which have many children like MyFrame.

I would also try to remove a lot of the repeated code and abstract it into helper functions
so it will be a lot easier to refactor if needed.