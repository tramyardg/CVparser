package com.cv.parser.extract;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtractFiles {

	Logger logger = LoggerFactory.getLogger(ExtractFiles.class);

	Button btnExtractContents;
	File[] filesInPublicDir;
	Table tableExtractedContent;

	public ExtractFiles(Button btnExtractContents, File[] filesInPublicDir, Table tableExtractedContent) {
		this.btnExtractContents = btnExtractContents;
		this.filesInPublicDir = filesInPublicDir;
		this.tableExtractedContent = tableExtractedContent;
	}

	public void run() {
		btnExtractContents.addListener(SWT.Selection, new Listener() {
			public void handleEvent(org.eclipse.swt.widgets.Event arg0) {
				new PDFExtractor(btnExtractContents, filesInPublicDir, tableExtractedContent).main();
				new MSExtractor(btnExtractContents, filesInPublicDir, tableExtractedContent).main();
				new TXTExtractor(btnExtractContents, filesInPublicDir, tableExtractedContent).main();
				btnExtractContents.setEnabled(false);
			}
		});
	}
}
