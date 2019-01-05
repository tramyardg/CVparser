

### Table of Contents
- [Features](#features)
- [Regular expression](#regular-expression)
- [Dependencies](#dependencies)
- [Screenshots](#screenshots)
- [UML Class Diagram](#uml-class-diagram)

## Features
1. Support PDF, MS word, and text files.
2. Extract important information i.e skills, education, etc, email, phone number, etc.
3. Process multiple files at the same time.
2. Saving the data as JSON objects or CSV (in progress).

### Regular expression
Regular expression was used to extract a section of a resume. This is because an experience section might 
contain multiple instances of the word experience. 
Therefore, a strict regular expression for extracting experience section must be used.
The solution was to get the index of the word experience 
that matches exactly to the following
```Java
 EXPERIENCE ("\\b(Experience(s?)|EXPERIENCE(S?))\\b")
```
This excluded lowercase experience or experiences. The same notion applies to other section headings.

## Screenshots
![GUI](https://github.com/tramyardg/CVparser/blob/master/GUI_1.PNG)
### toString output
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

