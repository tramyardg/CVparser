### Table of Contents
- [Usage](#usage)
- [Dependencies](#dependencies)
- [Screenshots](#screenshots)
- [UML Class Diagram](#uml-class-diagram)

## Usage
1. Extract each resume information from the public folder.
2. Saving the data as objects. 

## Screenshots
![GUI](https://github.com/tramyardg/CVparser/blob/master/GUI_1.PNG)
![toString output](https://github.com/tramyardg/CVparser/blob/master/toStringOutput.PNG)

## UML Class Diagram

### Factory Method implemented for parsing
![UML class diagram - Factory Method](https://github.com/tramyardg/CVparser/blob/master/src/main/java/com/cv/parser/factorymethod/img_factory_method_uml.jpg)

### Builder implemented for resume viewer
![UML class diagram - Builder](https://github.com/tramyardg/CVparser/blob/master/src/main/java/com/cv/parser/builder/img_builder_uml.png)

## Dependencies
- SLF4J: for logging
- Apache PDFBox: for reading PDF files
- Apache POI: for reading Microsoft Word files
- SWT: graphical widget toolkit used for building GUI
