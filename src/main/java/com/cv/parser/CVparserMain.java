package com.cv.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.applicant.DocumentDetails;
import com.cv.parser.factorymethod.ExtractFiles;
import com.cv.parser.read.ReadFiles;
import com.cv.parser.read.ValidateRead;

public class CVparserMain {
    static Logger logger = LoggerFactory.getLogger(CVparserMain.class);

    File resumesStoragePath = new File(CVparserSingleton.getInstance().resumesStoragePath);
    protected Display display;
    protected Shell shell;
    protected Button btnReadDir;
    protected Button btnExtractContents;
    protected Button btnSaveAsJSON;
    protected Button btnSaveInCsv;

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
	    logger.error("CVparserMain:main(...)", e);
	    MessageBox messageBox = new MessageBox(new Shell(), SWT.ICON_ERROR);
	    messageBox.setText("Error");
	    messageBox.setMessage("Please close any word document files opened.");
	    int buttonID = messageBox.open();
	    if (buttonID == SWT.OK) {
		logger.error("Word document(s) file opened!");
	    }
	}
    }

    /**
     * Open the window.
     */
    public void open() {
	display = Display.getDefault();
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
    protected void createContents() throws NullPointerException {
	shell = new Shell();
	shell.setSize(741, 544);
	shell.setText("Resume Parser Application");

	new ValidateRead(shell).validateBeforeRead();

	File[] filesInPublicDir = this.resumesStoragePath.listFiles();

	btnReadDir = new Button(shell, SWT.NONE);
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

	Table tableExtractedContent = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
	tableExtractedContent.setBounds(10, 180, 705, 128);
	tableExtractedContent.setHeaderVisible(true);
	tableExtractedContent.setLinesVisible(true);

	TableColumn tblclmnContents = new TableColumn(tableExtractedContent, SWT.NONE);
	tblclmnContents.setWidth(664);
	tblclmnContents.setText("Contents");

	btnExtractContents = new Button(shell, SWT.NONE);
	btnExtractContents.setBounds(10, 149, 705, 25);
	btnExtractContents.setText("Extract contents");
	btnExtractContents.setEnabled(false);

	btnSaveAsJSON = new Button(shell, SWT.NONE);
	btnSaveAsJSON.setBounds(10, 314, 705, 25);
	btnSaveAsJSON.setText("Save in JSON file");
	btnSaveAsJSON.setEnabled(false);

	/** read files in public directory START **/
	new ReadFiles(btnReadDir, filesInPublicDir, tableDirContent, btnExtractContents).handleButtonClick();
	/** reading END **/

	////////////////////////////////////////
	
	/*** extract file content from directory START ***/
	List<String> superList = new ArrayList<>();
	new ExtractFiles(shell, btnExtractContents, superList, tableExtractedContent, btnSaveAsJSON)
		.handleButtonClick();
	/*** extracting END ***/

	//////////////////////////////////////

	/**** saving contents START ****/
	DocumentDetails dd = new DocumentDetails(btnSaveAsJSON, superList);
	dd.handleButtonClick();
	
	btnSaveInCsv = new Button(shell, SWT.NONE);
	btnSaveInCsv.setBounds(10, 345, 705, 25);
	btnSaveInCsv.setText("Save in CSV file");
	/**** saving contents END ****/
    }
}