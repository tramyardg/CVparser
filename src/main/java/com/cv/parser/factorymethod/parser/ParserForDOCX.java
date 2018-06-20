package com.cv.parser.factorymethod.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.cv.parser.FileFinderByExt;
import com.cv.parser.factorymethod.ExtensionSingleton;
import com.cv.parser.factorymethod.ParserInterface;
import com.cv.parser.factorymethod.ExtensionSingleton.Ext;

public class ParserForDOCX implements ParserInterface {

    private final Logger logger = LoggerFactory.getLogger(ParserForDOCX.class.getName());
    private FileFinderByExt find = new FileFinderByExt();

    private File[] docxFiles;
    private List<String> contents = new ArrayList<>();

    @Override
    public void setFiles() {
	this.docxFiles = find.finder(ExtensionSingleton.getInstance().get(Ext.DOCX));
    }

    @Override
    public void extractFiles() {
	for (File file : docxFiles) {
	    FileInputStream fs = null;
	    XWPFDocument msDoc = null;
	    XWPFWordExtractor we = null;
	    try {
		fs = new FileInputStream(file);
		msDoc = new XWPFDocument(fs);
		we = new XWPFWordExtractor(msDoc);
		this.contents.add(we.getText());
	    } catch (IOException | NullPointerException e) {
		logger.error(e.getMessage());
	    } finally {
		try {
		    we.close();
		    msDoc.close();
		    fs.close();
		} catch (IOException e) {
		    logger.error(e.getMessage());
		}
	    }
	}
    }

    @Override
    public List<String> getContents() {
	return contents;
    }
}
