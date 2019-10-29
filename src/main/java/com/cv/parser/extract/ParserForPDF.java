package com.cv.parser.extract;

import com.cv.parser.CVParserSingleton;
import com.cv.parser.extract.ExtensionSingleton.Ext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserForPDF implements ParserInterface {
    private final static Logger LOG = LogManager.getLogger();
    private File resumeStorage = new File(CVParserSingleton.getInstance().resumesStoragePath);

    private File[] pdfFiles;
    private List<String> contents = new ArrayList<>();

    private File singleFile;

    public ParserForPDF() {
    }

    public ParserForPDF(File file) {
	this.singleFile = file;
    }

    @Override
    public void setFiles() {
	this.pdfFiles = finder(ExtensionSingleton.getInstance().get(Ext.PDF));
    }

    @Override
    public void extractFiles() {
	for (File file : pdfFiles) {
	    try {
		PDDocument document = PDDocument.load(file);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String removedPageNumberRegex = "\\bPage\\d\\b";
		this.contents.add(pdfStripper.getText(document).replaceAll(removedPageNumberRegex, ""));
		document.close();
	    } catch (IOException e) {
		LOG.error(e.getMessage());
	    }
	}
    }

    @Override
    public void extractSingleFile() {
	try {
	    PDDocument document = PDDocument.load(this.singleFile);
	    PDFTextStripper pdfStripper = new PDFTextStripper();
	    String removedPageNumberRegex = "\\bPage\\d\\b";
	    this.contents.add(pdfStripper.getText(document).replaceAll(removedPageNumberRegex, ""));
	    document.close();
	} catch (IOException e) {
	    LOG.error(e.getMessage());
	}
    }

    @Override
    public List<String> getContents() {
	return contents;
    }

    @Override
    public File[] finder(String ext) {
	return resumeStorage.listFiles((dir, filename) -> filename.endsWith(ext));
    }
}
