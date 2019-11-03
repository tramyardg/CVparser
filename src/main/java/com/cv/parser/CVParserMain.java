package com.cv.parser;

import com.cv.parser.applicant.DocumentDetails;
import com.cv.parser.extract.ExtensionSingleton;
import com.cv.parser.extract.ExtractFiles;
import com.cv.parser.extract.ParserFactory;
import com.cv.parser.extract.ParserInterface;
import com.cv.parser.extract.ExtensionSingleton.Ext;
import com.cv.parser.menu.ExtractMenu;
import com.cv.parser.menu.FileMenu;
import com.cv.parser.menu.SaveAsMenu;
import com.cv.parser.read.ReadFiles;
import com.cv.parser.read.ValidateRead;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
	shell.setSize(741, 461);
	shell.setText("Resume Parser Application");

	new ValidateRead(shell).validateBeforeRead();

	File[] filesInPublicDir = this.resumesStoragePath.listFiles();

	// read tables
	Table tableDirContent = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
	tableDirContent.setBounds(10, 10, 705, 164);
	tableDirContent.setHeaderVisible(true);
	tableDirContent.setLinesVisible(true);
	TableColumn tableColumnFileCount = new TableColumn(tableDirContent, SWT.NONE);
	tableColumnFileCount.setWidth(57);
	tableColumnFileCount.setText("#");
	TableColumn tableColumnFileType = new TableColumn(tableDirContent, SWT.NONE);
	tableColumnFileType.setWidth(72);
	tableColumnFileType.setText("Extension");
	TableColumn tableColumnFileName = new TableColumn(tableDirContent, SWT.NONE);
	tableColumnFileName.setWidth(272);
	tableColumnFileName.setText("File name");
	TableColumn tblclmnNewColumn = new TableColumn(tableDirContent, SWT.NONE);
	tblclmnNewColumn.setWidth(255);
	tblclmnNewColumn.setText("Comment");

	// extracted tables
	Table tableExtractedContent = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
	tableExtractedContent.setBounds(10, 180, 705, 206);
	tableExtractedContent.setHeaderVisible(true);
	tableExtractedContent.setLinesVisible(true);
	TableColumn tableColumnContents = new TableColumn(tableExtractedContent, SWT.NONE);
	tableColumnContents.setWidth(664);
	tableColumnContents.setText("Contents");

	//////////////////////////////////////
	// menus and menu items
	Menu menu = new Menu(shell, SWT.BAR);
	shell.setMenuBar(menu);
	FileMenu fileMenu = new FileMenu(menu);
	fileMenu.create();
	// Extract menu item
	ExtractMenu extractMenu = new ExtractMenu(menu);
	extractMenu.create();
	// Save As... menu and menu item
	SaveAsMenu saveAsMenu = new SaveAsMenu(menu);
	saveAsMenu.create();
	// end
	//////////////////////////////////////


	/* read files in public directory START */
	new ReadFiles(fileMenu.getMenuItemReadPublicDir(), filesInPublicDir, tableDirContent, extractMenu.getMntmExtractPublicDirectory()).handleMenuItemClick();
	/* reading END **/

	////////////////////////////////////////

	/* extract file content from directory START */
	List<String> superList = new ArrayList<>();
	new ExtractFiles(shell, extractMenu.getMntmExtractPublicDirectory(), superList, tableExtractedContent, saveAsMenu.getMenuItemJSON(), saveAsMenu.getMenuItemCSV())
	.handleMenuItemClick();
	/* extracting END */

	//////////////////////////////////////

	/* saving contents START */
	DocumentDetails dd = new DocumentDetails(saveAsMenu.getMenuItemJSON(), saveAsMenu.getMenuItemCSV(), superList);
	dd.handleButtonSaveInJSON();
	dd.handleButtonSaveInCSV();
	/* saving contents END */

	fileMenu.getMenuItemOpenFile().addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent arg0) {
		// User has selected to open a single file
		FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
		String selected = fileDialog.open();
		
		if (selected != null) {
		    File fileSelected = new File(selected);
		    String fileName = fileSelected.getName();
		    String ext = fileName.substring(fileName.indexOf('.'));
		    
		    String[] fExts = { ExtensionSingleton.getInstance().get(Ext.TXT),
			    ExtensionSingleton.getInstance().get(Ext.PDF),
			    ExtensionSingleton.getInstance().get(Ext.DOCX),
			    ExtensionSingleton.getInstance().get(Ext.DOC) };
		    if (Arrays.asList(fExts).contains(ext)) {
			TableItem item = new TableItem(tableDirContent, SWT.NONE);
			item.setText(new String[] { (tableDirContent.getItemCount() - 1 + 1) + "", ext, fileName });
			ParserFactory pf = new ParserFactory();
			try {
			    ParserInterface pi = pf.getContent(ext.substring(1), fileSelected);
			    pi.extractSingleFile();
			    System.out.println(pi.getContents().toString());
			} catch (UnsupportedFileExtension e) {
			    LOG.error(e.getMessage());
			}
		    }
		}

	    }
	});
	
	fileMenu.exit();
	
    }
    
}