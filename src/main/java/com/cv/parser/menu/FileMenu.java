package com.cv.parser.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class FileMenu {
    private Menu menu;
    private MenuItem mntmExit;
    private MenuItem menuItemReadPublicDir;
    private MenuItem menuItemOpenFile;

    public FileMenu(Menu menu) {
	this.menu = menu;
    }

    public void create() {
	MenuItem menuItemFile = new MenuItem(menu, SWT.CASCADE);
	menuItemFile.setText("File");
	Menu menuFileHeader = new Menu(menuItemFile);
	menuItemFile.setMenu(menuFileHeader);

	// processed file from public directory
	menuItemReadPublicDir = new MenuItem(menuFileHeader, SWT.NONE);
	menuItemReadPublicDir.setText("Read from Public Directory");
	// open single file menu item
	menuItemOpenFile = new MenuItem(menuFileHeader, SWT.NONE);
	menuItemOpenFile.setText("Open File...");

	// separator and Exit menu item
	new MenuItem(menuFileHeader, SWT.SEPARATOR);
	mntmExit = new MenuItem(menuFileHeader, SWT.NONE);
	mntmExit.setText("Exit");
    }

    public MenuItem getMenuItemReadPublicDir() {
	return menuItemReadPublicDir;
    }

    public MenuItem getMenuItemOpenFile() {
	return menuItemOpenFile;
    }

    public void exit() {
	mntmExit.addListener(SWT.Selection, arg0 -> {
	    System.exit(0);
	});
    }
}
