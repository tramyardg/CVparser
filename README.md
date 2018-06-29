### Dependencies
- SLF4J: for logging
- Apache PDFBox: for reading PDF files
- Apache POI: for reading Microsoft Word files
- SWT: graphical widget toolkit used for building GUI

### UML class diagram
##### Factory Method implemented for parsing
![UML class diagram - Factory Method](https://github.com/tramyardg/CVparser/blob/master/src/main/java/com/cv/parser/factorymethod/img_factory_method_uml.jpg)
##### Usage
```java

	private void doParse(ParserInterface parserInterface) {
		parserInterface.setFiles();
		parserInterface.extractFiles();
		superList.addAll(parserInterface.getContents());
    }

    private void parsePDF(ParserFactory factory) throws UnsupportedFileExtension {
		doParse(factory.getContent("pdf"));
    }

    private void parseDOC(ParserFactory factory) throws UnsupportedFileExtension {
		doParse(factory.getContent("doc"));
    }

    private void parseDOCX(ParserFactory factory) throws UnsupportedFileExtension {
		doParse(factory.getContent("docx"));
    }

    private void parseTXT(ParserFactory factory) throws UnsupportedFileExtension {
		doParse(factory.getContent("txt"));
    }
    
```
```java

		try {
		    parsePDF(parserFactory);
		    parseDOC(parserFactory);
		    parseDOCX(parserFactory);
		    parseTXT(parserFactory);
		} catch (UnsupportedFileExtension e) {
		    logger.error(e.getMessage());
		}
		
```

##### Builder implemented for resume viewer
![UML class diagram - Builder](https://github.com/tramyardg/CVparser/blob/master/src/main/java/com/cv/parser/builder/img_builder_uml.png)

### Screenshot
![GUI](https://github.com/tramyardg/CVparser/blob/master/GUI_1.PNG)
