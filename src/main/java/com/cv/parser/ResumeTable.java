package com.cv.parser;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class ResumeTable {

    protected Shell shell;
    private Table table;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
	try {
	    ResumeTable window = new ResumeTable();
	    window.open();
	} catch (Exception e) {
	    e.printStackTrace();
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
	shell.setSize(565, 401);
	shell.setText("SWT Application");
	
	Label lblListOfApplicants = new Label(shell, SWT.NONE);
	lblListOfApplicants.setBounds(10, 10, 529, 15);
	lblListOfApplicants.setText("List of applicants");
	
	table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
	table.setBounds(10, 31, 529, 321);
	table.setHeaderVisible(true);
	table.setLinesVisible(true);
	
	TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
	tblclmnId.setWidth(38);
	tblclmnId.setText("#");
	
	TableColumn tblclmnProfile = new TableColumn(table, SWT.NONE);
	tblclmnProfile.setWidth(320);
	tblclmnProfile.setText("Profile");
	
	TableColumn tblclmnViewCompleteResume = new TableColumn(table, SWT.NONE);
	tblclmnViewCompleteResume.setWidth(142);
	tblclmnViewCompleteResume.setText("View complete resume");

    }
}
