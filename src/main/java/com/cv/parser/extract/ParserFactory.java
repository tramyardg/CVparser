package com.cv.parser.extract;

import java.io.File;

import com.cv.parser.UnsupportedFileExtension;

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

    public ParserInterface getContent(String fileExtension, File file) throws UnsupportedFileExtension {

	ParserInterface parser;

	if (fileExtension == null) {
	    return null;
	}

	switch (fileExtension) {
	case "pdf":
	    parser = new ParserForPDF(file);
	    break;
	case "doc":
	    parser = new ParserForDOC(file);
	    break;
	case "docx":
	    parser = new ParserForDOCX(file);
	    break;
	case "txt":
	    parser = new ParserForTXT(file);
	    break;
	default:
	    throw new UnsupportedFileExtension();
	}

	return parser;
    }

}
