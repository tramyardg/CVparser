package com.cv.parser;

import com.cv.parser.applicant.DocumentDetails;
import com.cv.parser.extract.ExtractFiles;
import com.cv.parser.read.ReadFiles;
import com.cv.parser.read.ValidateRead;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CVParserMain {
    private final static Logger LOG = LogManager.getLogger();

    private File resumesStoragePath = new File(CVParserSingleton.getInstance().resumesStoragePath);
    private Shell shell;

    public static void main(String[] args) {
	try {
	    CVParserMain window = new CVParserMain();
	    window.open();
	} catch (Exception e) {
	    LOG.error("CVParserMain:main(...)", e);
	    MessageBox messageBox = new MessageBox(new Shell(), SWT.ICON_ERROR);
	    messageBox.setText("Error");
	    messageBox.setMessage("Please close any word document files opened.");
	    int buttonID = messageBox.open();
	    if (buttonID == SWT.OK) {
		LOG.error("Word document(s) file opened!");
	    }
	}
    }

    private void open() {
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

    private void createContents() throws NullPointerException {
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

	TableColumn tableColumnFileCount = new TableColumn(tableDirContent, SWT.NONE);
	tableColumnFileCount.setWidth(57);
	tableColumnFileCount.setText("#");

	TableColumn tableColumnFileType = new TableColumn(tableDirContent, SWT.NONE);
	tableColumnFileType.setWidth(72);
	tableColumnFileType.setText("Extension");

	TableColumn tableColumnFileName = new TableColumn(tableDirContent, SWT.NONE);
	tableColumnFileName.setWidth(535);
	tableColumnFileName.setText("File name");

	Table tableExtractedContent = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
	tableExtractedContent.setBounds(10, 180, 705, 128);
	tableExtractedContent.setHeaderVisible(true);
	tableExtractedContent.setLinesVisible(true);

	TableColumn tableColumnContents = new TableColumn(tableExtractedContent, SWT.NONE);
	tableColumnContents.setWidth(664);
	tableColumnContents.setText("Contents");

	Button btnExtractContents = new Button(shell, SWT.NONE);
	btnExtractContents.setBounds(10, 149, 705, 25);
	btnExtractContents.setText("Extract contents");
	btnExtractContents.setEnabled(false);

	Button btnSaveAsJSON = new Button(shell, SWT.NONE);
	btnSaveAsJSON.setBounds(10, 314, 705, 25);
	btnSaveAsJSON.setText("Save in JSON file");
	btnSaveAsJSON.setEnabled(false);

	Button btnSaveInCsv = new Button(shell, SWT.NONE);
	btnSaveInCsv.setBounds(10, 345, 705, 25);
	btnSaveInCsv.setText("Save in CSV file");
	btnSaveInCsv.setEnabled(false);

	/* read files in public directory START */
	new ReadFiles(btnReadDir, filesInPublicDir, tableDirContent, btnExtractContents).handleButtonClick();
	/* reading END **/

	////////////////////////////////////////

	/* extract file content from directory START */
	List<String> superList = new ArrayList<>();
	new ExtractFiles(shell, btnExtractContents, superList, tableExtractedContent, btnSaveAsJSON, btnSaveInCsv)
		.handleButtonClick();
	/* extracting END */

	//////////////////////////////////////

	/* saving contents START */
	DocumentDetails dd = new DocumentDetails(btnSaveAsJSON, btnSaveInCsv, superList);
	dd.handleButtonSaveInJSON();
	dd.handleButtonSaveInCSV();
	/* saving contents END */

	Button btnOpen = new Button(shell, SWT.NONE);
	btnOpen.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent arg0) {
		// User has selected to open a single file
		FileDialog dlg = new FileDialog(shell, SWT.OPEN);
		String[] filterExt = { "*.txt", "*.doc", ".rtf", "*.*" };
		dlg.setFilterExtensions(filterExt);
		String selected = dlg.open();

		if (selected != null) {
		    File fileSelected = new File(selected);
		    String fileName = fileSelected.getName();
		    String ext = fileName.substring(fileName.indexOf('.'));

		    TableItem item = new TableItem(tableDirContent, SWT.NONE);
		    item.setText(new String[] { (tableDirContent.getItemCount()-1 + 1) + "", ext, fileName });
		    File f = new File(selected);
		    System.out.println(f.isFile() + " is file");
		    System.out.println(f.getName() + " get name");
		    System.out.println(f.canRead() + " can read");
		}

		btnOpen.setEnabled(false);
	    }
	});
	btnOpen.setBounds(149, 10, 75, 25);
	btnOpen.setText("Open...");
    }
}