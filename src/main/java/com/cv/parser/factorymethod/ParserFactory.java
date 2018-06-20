package com.cv.parser.factorymethod;

import com.cv.parser.factorymethod.parser.ParserForDOC;
import com.cv.parser.factorymethod.parser.ParserForDOCX;
import com.cv.parser.factorymethod.parser.ParserForPDF;
import com.cv.parser.factorymethod.parser.ParserForTXT;

public class ParserFactory {

    public ParserInterface getContent(String fileExtension) throws UnsupportedFileExtension {

	ParserInterface parser = null;

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
