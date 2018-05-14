package com.cv.parser.extract;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.applicant.DocumentDetails;

public class ExtractFiles {
    Logger logger = LoggerFactory.getLogger(ExtractFiles.class);

    Button btnExtractContents;
    File[] filesInPublicDir;
    Table tableExtractedContent;

    List<String> allContents = new ArrayList<String>(); // contains ALL the
							// contents extracted

    public ExtractFiles(Button btnExtractContents, File[] filesInPublicDir, Table tableExtractedContent) {
	this.btnExtractContents = btnExtractContents;
	this.filesInPublicDir = filesInPublicDir;
	this.tableExtractedContent = tableExtractedContent;
    }

    public void run() {
	btnExtractContents.addListener(SWT.Selection, new Listener() {
	    public void handleEvent(org.eclipse.swt.widgets.Event arg0) {
		List<String> contents = new ArrayList<String>();

		PDFExtractor pdf = new PDFExtractor(btnExtractContents, filesInPublicDir, tableExtractedContent);
		pdf.main();
		contents.addAll(pdf.getContents());

		MSExtractor ms = new MSExtractor(btnExtractContents, filesInPublicDir, tableExtractedContent);
		ms.main();
		contents.addAll(ms.getContents());

		TXTExtractor txt = new TXTExtractor(btnExtractContents, filesInPublicDir, tableExtractedContent);
		txt.main();
		contents.addAll(txt.getContents());

		setAllContents(contents); // combined all types of documents
					  // into one list for parsing
		displayIntable();

		DocumentDetails dd = new DocumentDetails(getAllContents());
		dd.main();
		// logger.info(dd.getAppDocList().get(0).toString());

		btnExtractContents.setEnabled(false);
	    }

	    public void displayIntable() {
		for (int i = 0; i < allContents.size(); i++) {
		    TableItem item = new TableItem(tableExtractedContent, SWT.NONE);
		    item.setText(new String[] { allContents.get(i) });
		}
	    }
	});
    }

    public List<String> getAllContents() {
	return allContents;
    }

    public void setAllContents(List<String> allContents) {
	this.allContents = allContents;
	// logger.info(allContents.toString());
    }

}
