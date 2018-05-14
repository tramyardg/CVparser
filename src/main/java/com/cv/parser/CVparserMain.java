package com.cv.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.applicant.DocumentDetails;
import com.cv.parser.extract.ExtractFiles;
import com.cv.parser.extract.MSExtractor;
import com.cv.parser.extract.PDFExtractor;
import com.cv.parser.extract.TXTExtractor;
import com.cv.parser.read.ReadFiles;
import com.cv.parser.read.ValidateRead;

public class CVparserMain {
    static Logger logger = LoggerFactory.getLogger(CVparserMain.class);
    
    File resumesStoragePath = new File(CVparserSingleton.getInstance().resumesStoragePath);
    protected Shell shell;

    /**
     * Launch the application.
     * 
     * @param args
     */
    public static void main(String[] args) {
	try {
	    CVparserMain window = new CVparserMain();
	    window.open();
	} catch (Exception e) {
	    logger.error("main", e);
	}
    }

    /**
     * Open the window.
     */
    public void open() {
	Display display = Display.getDefault();

	createContents();
	shell.open();
	shell.layout();
	while (!shell.isDisposed()) {
	    if (!display.readAndDispatch()) {
		display.sleep();
	    }
	}
    }

    /**
     * Create contents of the window.
     */
    protected void createContents() {
	shell = new Shell();
	shell.setSize(741, 544);
	shell.setText("Resume Parser Application");

	new ValidateRead(shell).validateBeforeRead();

	File[] filesInPublicDir = this.resumesStoragePath.listFiles();

	Button btnReadDir = new Button(shell, SWT.NONE);
	btnReadDir.setBounds(10, 10, 127, 25);
	btnReadDir.setText("Read files from public");

	Label lblExtensions = new Label(shell, SWT.NONE);
	lblExtensions.setBounds(426, 15, 289, 15);
	lblExtensions.setText("Acceptable extensions include .pdf, .doc, .docx");

	Table tableDirContent = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
	tableDirContent.setBounds(10, 41, 705, 102);
	tableDirContent.setHeaderVisible(true);
	tableDirContent.setLinesVisible(true);

	TableColumn tblclmnFileCount = new TableColumn(tableDirContent, SWT.NONE);
	tblclmnFileCount.setWidth(57);
	tblclmnFileCount.setText("#");

	TableColumn tblclmnFileType = new TableColumn(tableDirContent, SWT.NONE);
	tblclmnFileType.setWidth(72);
	tblclmnFileType.setText("Extension");

	TableColumn tblclmnFileName = new TableColumn(tableDirContent, SWT.NONE);
	tblclmnFileName.setWidth(535);
	tblclmnFileName.setText("File name");

	Label lblTotalFiles = new Label(shell, SWT.NONE);
	lblTotalFiles.setToolTipText("total number of files ");
	lblTotalFiles.setBounds(10, 149, 152, 15);
	lblTotalFiles.setText("Total number of files: " + filesInPublicDir.length);
	lblTotalFiles.setVisible(false);

	Label lblNumAcceptableFiles = new Label(shell, SWT.NONE);
	lblNumAcceptableFiles.setBounds(426, 149, 289, 15);
	lblNumAcceptableFiles
		.setText("Number of .pdf, .doc, .docx, .txt files: " + new FileFinderByExt().countAcceptableFiles());
	lblNumAcceptableFiles.setVisible(false);

	Button btnExtractContents = new Button(shell, SWT.NONE);
	btnExtractContents.setBounds(10, 170, 705, 25);
	btnExtractContents.setText("Extract contents");

	/** read files in public directory START **/
	new ReadFiles(btnReadDir, filesInPublicDir, tableDirContent).handleButtonClick();

	Table tableExtractedContent = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
	tableExtractedContent.setBounds(10, 201, 705, 107);
	tableExtractedContent.setHeaderVisible(true);
	tableExtractedContent.setLinesVisible(true);

	TableColumn tblclmnContents = new TableColumn(tableExtractedContent, SWT.NONE);
	tblclmnContents.setWidth(664);
	tblclmnContents.setText("Contents");
	/** reading END **/
	
	////////////////////////////////////////
	
	/*** extract file content from directory START ***/
	List<String> superList = new ArrayList<String>();
	
	PDFExtractor pdf = new PDFExtractor(filesInPublicDir);
	pdf.main();
	superList.addAll(pdf.getContents());
	
	MSExtractor ms = new MSExtractor(filesInPublicDir);
	ms.main();
	superList.addAll(ms.getContents());
	
	TXTExtractor txt = new TXTExtractor(filesInPublicDir);
	txt.main();
	superList.addAll(txt.getContents());
	
	// display extracted documents in table
	new ExtractFiles(btnExtractContents, superList, tableExtractedContent).handleButtonClick();
	/*** extracting END ***/
	
	//////////////////////////////////////
	
	/**** saving to database START ****/
	Button btnSaveDocumentsToDb = new Button(shell, SWT.NONE);
	btnSaveDocumentsToDb.setBounds(10, 314, 705, 25);
	btnSaveDocumentsToDb.setText("Save documents to database");
	
	DocumentDetails dd = new DocumentDetails(btnSaveDocumentsToDb, superList);
	dd.handleButtonClick();
	/**** saving to database END ****/
    }
}
