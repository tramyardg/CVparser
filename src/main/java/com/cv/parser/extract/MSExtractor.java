package com.cv.parser.extract;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.FileExtension;
import com.cv.parser.FileExtension.Ext;
import com.cv.parser.FileFinderByExt;

/**
 * Supports MS Word 2004+.
 * 
 * @author RAYMARTHINKPAD
 *
 */
public class MSExtractor implements IExtractor {
    Logger logger = LoggerFactory.getLogger(MSExtractor.class);

    FileExtension fe = new FileExtension();
    FileFinderByExt find = new FileFinderByExt();

    File[] msDocs;
    List<String> contents = new ArrayList<String>();

    File[] filesInPublicDir;
    
    public MSExtractor(File[] filesInPublicDir) {
	this.filesInPublicDir = filesInPublicDir;
    }

    public void main() {
	setFiles();
	extractFiles();
    }

    public void setFiles() {
	File[] doc = find.finder(fe.get(Ext.DOC));
	File[] docx = find.finder(fe.get(Ext.DOCX));
	if (doc.length != 0 && docx.length != 0) {
	    this.msDocs = ArrayUtils.addAll(doc, docx);
	} else if (doc.length != 0) {
	    this.msDocs = doc;
	} else if (docx.length != 0) {
	    this.msDocs = docx;
	}
    }

    public void extractFiles() {
	for (File file : msDocs) {
	    FileInputStream fs = null;
	    XWPFDocument msDoc = null;
	    XWPFWordExtractor we = null;
	    try {
		fs = new FileInputStream(file);
		msDoc = new XWPFDocument(fs);
		we = new XWPFWordExtractor(msDoc);
		this.contents.add(we.getText());
		//logger.info(we.getText());
	    } catch (FileNotFoundException e) {
		logger.error(e.getMessage());
	    } catch (IOException e) {
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
    
    public List<String> getContents() {
	return contents;
    }
}
