package com.cv.parser.extract;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.FileExtension;
import com.cv.parser.FileExtension.Ext;
import com.cv.parser.FileFinderByExt;

public class PDFExtractor extends ExtractFiles implements IExtractor {
    Logger logger = LoggerFactory.getLogger(PDFExtractor.class);

    FileExtension fe = new FileExtension();
    FileFinderByExt find = new FileFinderByExt();

    File[] pdfFiles;
    List<String> contents = new ArrayList<String>();

    public PDFExtractor(Button btnExtractContents, File[] filesInPublicDir, Table tableExtractedContent) {
	super(btnExtractContents, filesInPublicDir, tableExtractedContent);
    }

    public void main() {
	setFiles();
	extractFiles();
    }

    public void setFiles() {
	this.pdfFiles = find.finder(fe.get(Ext.PDF));
    }

    public void extractFiles() {
	for (File file : pdfFiles) {
	    try {
		PDDocument document = PDDocument.load(file);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		this.contents.add(pdfStripper.getText(document));
		logger.info(pdfStripper.getText(document)); // for debugging
		document.close();
	    } catch (IOException e) {
		logger.error(e.toString());
	    }
	}
    }
    
    public List<String> getContents() {
	return contents;
    }
}
