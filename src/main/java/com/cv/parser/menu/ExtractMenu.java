package com.cv.parser.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class ExtractMenu {
    private Menu menu;
    private MenuItem mntmExtractPublicDirectory;

    public ExtractMenu(Menu menu) {
	this.menu = menu;
    }

    public void create() {
	MenuItem mntmExtract = new MenuItem(menu, SWT.CASCADE);
	mntmExtract.setText("Extract");
	Menu casecadeExtractMenuItem = new Menu(mntmExtract);
	mntmExtract.setMenu(casecadeExtractMenuItem);
	mntmExtractPublicDirectory = new MenuItem(casecadeExtractMenuItem, SWT.NONE);
	mntmExtractPublicDirectory.setText("Extract public directory");
	mntmExtractPublicDirectory.setEnabled(false);
    }

    public MenuItem getMntmExtractPublicDirectory() {
	return mntmExtractPublicDirectory;
    }
}
