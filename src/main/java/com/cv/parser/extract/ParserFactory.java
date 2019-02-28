package com.cv.parser.extract;

public class ParserFactory {

    ParserInterface getContent(String fileExtension) throws UnsupportedFileExtension {

        ParserInterface parser;

        if (fileExtension == null) {
            return null;
        }

        switch (fileExtension) {
            case "pdf":
                parser = new ParserForPDF();
                break;
            case "doc":
                parser = new ParserForDOC();
                break;
            case "docx":
                parser = new ParserForDOCX();
                break;
            case "txt":
                parser = new ParserForTXT();
                break;
            default:
                throw new UnsupportedFileExtension();
        }

        return parser;
    }

}
