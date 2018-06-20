package com.cv.parser.extract;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.factorymethod.ParserFactory;
import com.cv.parser.factorymethod.ParserInterface;
import com.cv.parser.factorymethod.UnsupportedFileExtension;

public class ExtractFiles {
    Logger logger = LoggerFactory.getLogger(ExtractFiles.class);

    Button btnExtractContents;
    List<String> superList;
    Table tableExtractedContent;

    private ParserFactory parserFactory = new ParserFactory();
	
    public ExtractFiles(Button btnExtractContents, List<String> superList, Table tableExtractedContent) {
	this.btnExtractContents = btnExtractContents;
	this.superList = superList;
	this.tableExtractedContent = tableExtractedContent;
    }

    public void handleButtonClick() {
	btnExtractContents.addListener(SWT.Selection, new Listener() {
	    public void handleEvent(org.eclipse.swt.widgets.Event arg0) {

		// Using Factory Method design pattern
		try {
		    parsePDF(parserFactory);
		    parseDOC(parserFactory);
		    parseDOCX(parserFactory);
		    parseTXT(parserFactory);
		} catch (UnsupportedFileExtension e) {
		    logger.error(e.getMessage());
		}

		displayDocumentsInTable();

		btnExtractContents.setEnabled(false);
	    }
	    
	    // display extracted documents in table
	    private void displayDocumentsInTable() {
		for (int i = 0; i < superList.size(); i++) {
		    TableItem item = new TableItem(tableExtractedContent, SWT.NONE);
		    item.setText(new String[] { superList.get(i) });
		}
	    }
	});
    }

    public List<String> getAllDocuments() {
	return superList;
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
    
    private void doParse(ParserInterface parserInterface) {
	parserInterface.setFiles();
	parserInterface.extractFiles();
	superList.addAll(parserInterface.getContents());
    }

}
