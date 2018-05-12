package com.cv.parser;

import java.io.File;

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

import com.cv.parser.extract.ExtractFiles;
import com.cv.parser.read.ReadFiles;

public class CVparserMain {

	File resumesStoragePath = new File(Constants.getInstance().resumesStoragePath);

	static Logger logger = LoggerFactory.getLogger(CVparserMain.class);

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
		shell.setText("SWT Application");

		checkIfDirExist();
		
		File[] filesInPublicDir = this.resumesStoragePath.listFiles();
		
		Button btnReadDir = new Button(shell, SWT.NONE);
		btnReadDir.setBounds(10, 10, 127, 25);
		btnReadDir.setText("Read files from public");

		Label lblExtensions = new Label(shell, SWT.NONE);
		lblExtensions.setBounds(426, 15, 289, 15);
		lblExtensions.setText("Acceptable extensions include .pdf, .doc, .docx");

		Table tableDirContent = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tableDirContent.setBounds(10, 41, 705, 138);
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
		lblTotalFiles.setBounds(10, 185, 152, 15);
		lblTotalFiles.setText("Total number of files: " + filesInPublicDir.length);
		lblTotalFiles.setVisible(false);

		Label lblNumAcceptableFiles = new Label(shell, SWT.NONE);
		lblNumAcceptableFiles.setBounds(426, 185, 289, 15);
		lblNumAcceptableFiles.setText("Number of .pdf, .doc, .docx, .txt files: " + new FileFinderByExt().countAcceptableFiles());
		lblNumAcceptableFiles.setVisible(false);

		Button btnExtractContents = new Button(shell, SWT.NONE);
		btnExtractContents.setBounds(10, 206, 705, 25);
		btnExtractContents.setText("Extract contents");

		// read files in public directory
		new ReadFiles(btnReadDir, filesInPublicDir, tableDirContent).run();
		
		Table tableExtractedContent = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tableExtractedContent.setBounds(10, 237, 705, 151);
		tableExtractedContent.setHeaderVisible(true);
		tableExtractedContent.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(tableExtractedContent, SWT.NONE);
		tblclmnNewColumn.setWidth(71);
		tblclmnNewColumn.setText("Type");

		TableColumn tblclmnContents = new TableColumn(tableExtractedContent, SWT.NONE);
		tblclmnContents.setWidth(592);
		tblclmnContents.setText("Contents");

		// extract file content from directory
		new ExtractFiles(btnExtractContents, filesInPublicDir, tableExtractedContent).run();

	}

	private boolean noResumesInPublicDir() {
		if (this.resumesStoragePath.listFiles().length == 0) {
			MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING);
			messageBox.setText("Warning");
			messageBox.setMessage("No resume files found in public directory.");
			
			int buttonID = messageBox.open();
			if (buttonID == SWT.OK) {
				logger.error("No resumes found");
			}
			return false;
		} else {
			return true;
		}
	}

	private void checkIfDirExist() {
		if (!this.resumesStoragePath.exists()) {
			MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR);
			messageBox.setText("Error");
			messageBox.setMessage("Directory public not found!");
			
			int buttonID = messageBox.open();
			if (buttonID == SWT.OK) {
				logger.error("Directory public not found!");
			}
		} else {
			if (!noResumesInPublicDir()) {
				return;
			}
		}
	}
}
