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


## Instructions to use Application
- Assumes basic knowledge of file system and it's command
- "cd <FolderName>" changes your directory to the directory with the given name if it exists
- "ls" just prints the names of all the folders/files in the current directory
- "mkdir <FolderName>" Creates a folder with the given name if it doesnt already exist
- "rm" <ItemName> removed item with given name if exists
- "touch <FileName> creates a file with given name if it doesnt already exist"
- Select 1 at the beginning of the program to load a file system or 2 to create a new file system
- Write "save" at any moment to write the current file system to the saved file system