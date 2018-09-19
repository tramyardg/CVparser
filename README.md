

### Table of Contents
- [Usage](#usage)
- [Regular expression](#regular-expression)
- [Dependencies](#dependencies)
- [Screenshots](#screenshots)
- [UML Class Diagram](#uml-class-diagram)

## Usage
1. Extract each resume information from the public folder.
2. Saving the data as objects. 

## Screenshots
![GUI](![GUI](https://github.com/tramyardg/CVparser/blob/master/GUI_1.PNG)
### toString output
![toString output](output](https://github.com/tramyardg/CVparser/blob/master/toStringOutput.PNG)

## UML Class Diagram

### Factory Method implemented for parsing
![UML class diagram - Factory Method](Method](https://github.com/tramyardg/CVparser/blob/master/src/main/java/com/cv/parser/factorymethod/img_factory_method_uml.jpg)

### Builder implemented for resume viewer
![UML class diagram - Builder](Builder](https://github.com/tramyardg/CVparser/blob/master/src/main/java/com/cv/parser/builder/img_builder_uml.png)

### Regular expression
These regular expressions must be strictly imposed, this is due to one particular reason:
For instance, an experience section might contain multiple instances of the word experience. 
Therefore, a strict regular expression for section headings must apply.
For experience, the solution was to get the index of the word experience 
which matches exactly to the following
Experience|Experiences|EXPERIENCE|EXPERIENCES. This excluded
experience or experiences to be valid. The same notion applies to other section headings.

## Dependencies
- SLF4J: for logging
- Apache PDFBox: for reading PDF files
- Apache POI: for reading Microsoft Word files
- SWT: graphical widget toolkit used for building GUI

