package com.cv.parser.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class SaveAsMenu {
    private Menu menu;
    private MenuItem menuItemJSON;
    private MenuItem menuItemCSV;

    public SaveAsMenu(Menu menu) {
	this.menu = menu;
    }

    public MenuItem getMenuItemJSON() {
	return menuItemJSON;
    }

    public MenuItem getMenuItemCSV() {
	return menuItemCSV;
    }

    public void create() {
	MenuItem mntmSave = new MenuItem(menu, SWT.CASCADE);
	mntmSave.setText("Save As...");
	Menu cascadeSaveAsMenuItem = new Menu(mntmSave);
	mntmSave.setMenu(cascadeSaveAsMenuItem);
	menuItemJSON = new MenuItem(cascadeSaveAsMenuItem, SWT.NONE);
	menuItemJSON.setText("JSON");
	menuItemJSON.setEnabled(false);
	menuItemCSV = new MenuItem(cascadeSaveAsMenuItem, SWT.NONE);
	menuItemCSV.setText("CSV");
	menuItemCSV.setEnabled(false);
    }

}
