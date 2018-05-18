package com.cv.parser;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.util.ImagePanel;
import com.cv.parser.util.Message;
import com.cv.parser.util.JDialogHelper;
import com.cv.parser.applicant.CVForm;
import com.cv.parser.applicant.DocumentDetails;
import com.cv.parser.extract.ExtractFiles;
import com.cv.parser.extract.MSExtractor;
import com.cv.parser.extract.PDFExtractor;
import com.cv.parser.extract.TXTExtractor;
import com.cv.parser.read.ReadFiles;
import com.cv.parser.read.ValidateRead;

public class CVparserMain {
    static Logger logger = LoggerFactory.getLogger(CVparserMain.class);
	private static Message message = new Message();
    File resumesStoragePath = new File(CVparserSingleton.getInstance().resumesStoragePath);
    protected Shell shell;

	private static BufferedImage image;
	private static JFrame mainFrame;
	protected JDialog parentDialog;
	
    /**
     * Launch the application.
     * 
     * @param args
     */
	public static void main(String[] args) {

		// Schedule a job for the event-dispatching thread: //creating and
		// Showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createGUIMenu();
			}
		});
	}
    
	/*
	 * Create and setup the Frame Windows GUI
	 */
	protected static void createGUIMenu() {
		mainFrame = new javax.swing.JFrame(message.msg("mainTitle")); // Prepare
																		// a
																		// blank
																		// frame
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new ImagePanel(); // prepare the JPanel that hosted the
											// background image
		mainFrame.add(panel);

		CVparserMain app = new CVparserMain(); // start the quiz menu

		mainFrame.setJMenuBar(app.createMenu()); // Set up the menu bar on the
													// top of the frame

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		mainFrame.setSize(screenSize.width, screenSize.height); 

		mainFrame.setVisible(true);
	}	
	
	/**
	 * Set up the menu bar, on the top of the frame.. Addition: only comments
	 * March 15 2017 @author RAYMARTHINKPAD This creates the menus in the frame.
	 * The menu includes items that are event driven on click the action will be
	 * performed.
	 */
	public JMenuBar createMenu() {
		JMenuBar menuBar = new JMenuBar(); // create the menu bar at the top of

		// Add your testing under the first Menu.. 
		JMenu fileMenu = new JMenu("Test Parsing using JSON"); // First group will handle master
		fileMenu.setMnemonic(KeyEvent.VK_F); // The shortcut is ALT + M (Master
		menuBar.add(fileMenu); // add to the menu bar

		// Thje menu item for your test code is here...
		JMenuItem parseMenuTest1 = new JMenuItem("Regex JSON style", KeyEvent.VK_D);
		parseMenuTest1.setToolTipText("Test parsing Document");
		parseMenuTest1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				parseMenuTest1MenuItemActionPerformed(evt);
				// entryUserMenuItemActionPerformed(evt);
			}
		});
		
		fileMenu.add(parseMenuTest1);
		

		// Thje menu item for your test code is here...
		JMenuItem parseMenuTest2 = new JMenuItem("You GUI JPanel here", KeyEvent.VK_D);
		parseMenuTest2.setToolTipText("Test parsing Document");
		parseMenuTest2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				parseMenuTest2MenuItemActionPerformed(evt);
			}
		});
		
		fileMenu.add(parseMenuTest2);

		
		
		
		/************  This menu is for my menu test  40046196 *************/
		
		// Third Menu in Menu Bar is Window About and Exit
		JMenu menu2 = new JMenu("Parse Doc 2 Test"); 
		menu2.setMnemonic(KeyEvent.VK_H); // Shortcut is ALT + E
		menuBar.add(menu2); // add exit menu to menu bar

		// First menu item for windows is About
		JMenuItem uploadDocumentToForm = new JMenuItem("Parse Doc to Form", KeyEvent.VK_A);
		uploadDocumentToForm.setToolTipText("Parse Doc to Form");
		uploadDocumentToForm.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				uploadDocumentToFormActionPerformed(evt);
			}
		});

		menu2.add(uploadDocumentToForm); // add about menu item to exit MEnu


		return menuBar;
	}	
	
	/**
	 * The events when user click UserForm
	 * Note from me:  
	 *  Should porting the forms in JPanel like previous projects, manageable control form Tree Menu Structure. 
	 * Also using JPanel, would learn how to advance the use of  JTable, position,  etc. 
	 *  For now the menu only call your design procedure directly.. 
	 * 
	 * @param evt
	 */
	private void parseMenuTest1MenuItemActionPerformed(java.awt.event.ActionEvent evt) {

		try {
			this.open();
		} catch (Exception e) {
			logger.error("main", e);
		}
		
		
//		UserForm userForm = new UserForm(); // create instance UserForm
//		JDialog dialog = new JDialog(this.mainFrame, message.msg("userFormTitle"), true); 
//
//		dialog.add(userForm);
//		dialog.pack();
//
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
//		Dimension dialogSize = dialog.getSize(); // get your frame size
//		dialog.setLocation(new Point((screenSize.width - dialogSize.width) / 2, 
//				(screenSize.height - dialogSize.height) / 2)); // to the center
//																// of screen
//		dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
//		JDialogHelper.setJDialogTree(dialog, null, null);
//		dialog.setVisible(true); // When setVisible this program waiting, until
//									// you close the dialog.
//		dialog.dispose();

	}	
	
	private void parseMenuTest2MenuItemActionPerformed(java.awt.event.ActionEvent evt) {

		com.cv.parser.About cvForm = new com.cv.parser.About(); // create instance UserForm
		JDialog dialog = new JDialog(this.mainFrame, "CV Form", true); 

		dialog.add(cvForm);
		dialog.pack();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		Dimension dialogSize = dialog.getSize(); // get your frame size
		dialog.setLocation(new Point((screenSize.width - dialogSize.width) / 2, 
				(screenSize.height - dialogSize.height) / 2)); // to the center
																// of screen
		dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		JDialogHelper.setJDialogTree(dialog, null, null);
		dialog.setVisible(true); // When setVisible this program waiting, until
									// you close the dialog.
		dialog.dispose();

	}	
	
	private void uploadDocumentToFormActionPerformed(java.awt.event.ActionEvent evt) {

		com.cv.parser.applicant.CVForm cvForm = new com.cv.parser.applicant.CVForm(); // create instance UserForm
		JDialog dialog = new JDialog(this.mainFrame, "CV Form" , true); 

		dialog.add(cvForm);
		dialog.pack();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		Dimension dialogSize = dialog.getSize(); // get your frame size
		dialog.setLocation(new Point((screenSize.width - dialogSize.width) / 2, 
				(screenSize.height - dialogSize.height) / 2)); // to the center
																// of screen
		dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		JDialogHelper.setJDialogTree(dialog, null, null);
		dialog.setVisible(true); // When setVisible this program waiting, until
									// you close the dialog.
		dialog.dispose();

	}		
	
    
//    public static void main(String[] args) {
//	try {
//	    CVparserMain window = new CVparserMain();
//	    window.open();
//	} catch (Exception e) {
//	    logger.error("main", e);
//	}
//    }

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
