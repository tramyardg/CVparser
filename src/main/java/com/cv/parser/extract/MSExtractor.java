package com.cv.parser.extract;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.FileExtension;
import com.cv.parser.FileExtension.Ext;
import com.cv.parser.FileFinderByExt;

/**
 * Supports MS Word 2004+ file extension .doc and .docx.
 * 
 * @author RAYMARTHINKPAD
 *
 */
public class MSExtractor implements IExtractor {
    Logger logger = LoggerFactory.getLogger(MSExtractor.class);

    private FileExtension fileExtension = new FileExtension();
    private FileFinderByExt fileFinderByExt = new FileFinderByExt();

    private File[] docxFiles;
    private File[] docFiles;
    private List<String> contents = new ArrayList<String>();

    public void main() {
	setFiles();
	extractFiles();
    }

    public void setFiles() {
	this.docxFiles = fileFinderByExt.finder(fileExtension.get(Ext.DOCX));
	this.docFiles = fileFinderByExt.finder(fileExtension.get(Ext.DOC));
    }

    public void extractFiles() {
	extractDocxFiles();
	extractDocFiles();
    }

    public void extractDocxFiles() {
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

    public void extractDocFiles() {
	for (File file : docFiles) {
	    HWPFDocument hwpfdoc;
	    WordExtractor extractor;
	    try {
		hwpfdoc = new HWPFDocument(new FileInputStream(file));
		extractor = new WordExtractor(hwpfdoc);
		this.contents.add(extractor.getText());
		extractor.close();
	    } catch (IOException e) {
		logger.info(e.getMessage());
	    }
	}
    }

    public List<String> getContents() {
	return contents;
    }
}
