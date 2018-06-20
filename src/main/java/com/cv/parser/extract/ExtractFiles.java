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

    public ExtractFiles(Button btnExtractContents, List<String> superList, Table tableExtractedContent) {
	this.btnExtractContents = btnExtractContents;
	this.superList = superList;
	this.tableExtractedContent = tableExtractedContent;
    }

    public void handleButtonClick() {
	btnExtractContents.addListener(SWT.Selection, new Listener() {
	    public void handleEvent(org.eclipse.swt.widgets.Event arg0) {
		
		// Using Factory Method design pattern
		ParserFactory parserFactory = new ParserFactory();
		try {
		    
		    // for PDF
		    ParserInterface pdfParser = parserFactory.getContent("pdf");
		    pdfParser.setFiles();
		    pdfParser.extractFiles();
		    superList.addAll(pdfParser.getContents());
		    
		} catch (UnsupportedFileExtension e) {
		    logger.error(e.getMessage());
		}
		
		displayDocumentsInTable();
		
		btnExtractContents.setEnabled(false);
	    }

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

}
