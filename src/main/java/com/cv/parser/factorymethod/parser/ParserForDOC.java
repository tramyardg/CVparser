package com.cv.parser.factorymethod.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.FileFinderByExt;
import com.cv.parser.factorymethod.ExtensionSingleton;
import com.cv.parser.factorymethod.ParserInterface;
import com.cv.parser.factorymethod.ExtensionSingleton.Ext;

public class ParserForDOC implements ParserInterface {

    private final Logger logger = LoggerFactory.getLogger(ParserForDOC.class.getName());
    private FileFinderByExt find = new FileFinderByExt();

    private File[] docFiles;
    private List<String> contents = new ArrayList<>();

    @Override
    public void setFiles() {
	this.docFiles = find.finder(ExtensionSingleton.getInstance().get(Ext.DOC));
    }

    @Override
    public void extractFiles() {
	for (File file : docFiles) {
	    HWPFDocument hwpfdoc;
	    WordExtractor extractor = null;
	    try {
		hwpfdoc = new HWPFDocument(new FileInputStream(file));
		extractor = new WordExtractor(hwpfdoc);
		this.contents.add(extractor.getText());
	    } catch (IOException e) {
		logger.info(e.getMessage());
	    } finally {
		try {
		    extractor.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    @Override
    public List<String> getContents() {
	return contents;
    }
}
