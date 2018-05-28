package com.cv.parser.read;

import java.io.File;
import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.CVparserSingleton;
import com.cv.parser.FileExtension;
import com.cv.parser.FileExtension.Ext;

public class ReadFiles {
    Logger logger = LoggerFactory.getLogger(ReadFiles.class);

    private Button btnReadDir;
    private File[] filesInPublicDir;
    private Table tableDirContent;

    private FileExtension fe = new FileExtension();

    public ReadFiles(Button btnReadDir, File[] filesInPublicDir, Table tableDirContent) {
	this.btnReadDir = btnReadDir;
	this.filesInPublicDir = filesInPublicDir;
	this.tableDirContent = tableDirContent;
    }

    public void handleButtonClick() {
	setEnabledExtractButton(false);
	btnReadDir.addListener(SWT.Selection, new Listener() {
	    public void handleEvent(org.eclipse.swt.widgets.Event arg0) {
		setFiles();
		showHiddenLables();
		btnReadDir.setEnabled(false);
		setEnabledExtractButton(true);
	    }
	});
    }

    protected void setFiles() {
	for (int i = 0; i < filesInPublicDir.length; i++) {
	    if (filesInPublicDir[i].isFile()) {
		String fileName = filesInPublicDir[i].getName();
		String ext = fileName.substring(fileName.indexOf('.'));
		TableItem item = new TableItem(tableDirContent, SWT.NONE);
		String[] fExts = { fe.get(Ext.PDF), fe.get(Ext.DOC), fe.get(Ext.DOCX), fe.get(Ext.TXT), 
			fe.get(Ext.RTF), fe.get(Ext.DOT), fe.get(Ext.DOTX) };
		if (Arrays.asList(fExts).contains(ext)) {
		    item.setText(new String[] { (i + 1) + "", ext, fileName });
		} else {
		    item.setText(new String[] { (i + 1) + "", ext, fileName });
		    item.setBackground(CVparserSingleton.getInstance().yellow);
		}
	    }
	}
    }
    
    protected void showHiddenLables() {
	Control[] controls = btnReadDir.getParent().getChildren();
	for (Control c : controls) {
	    if (!c.getVisible()) {
		c.setVisible(true);
	    }
	}
    }
    
    protected void setEnabledExtractButton(boolean isEnabled) {
	Control[] controls = btnReadDir.getParent().getChildren();
	for (Control c : controls) {
	    if (c.toString().equals("Button {Extract contents}")) {
		c.setEnabled(isEnabled);
	    }
	}
    }
}

