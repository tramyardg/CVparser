package com.cv.parser.read;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.CVparserSingleton;

public class ValidateRead {
    Logger logger = LoggerFactory.getLogger(ValidateRead.class);
    
    File resumesStoragePath = new File(CVparserSingleton.getInstance().resumesStoragePath);
    Shell shell;
    
    public ValidateRead(Shell shell)  {
	this.shell = shell;
    }
    
    private boolean isPublicDirContainsResumes() {
	if (this.resumesStoragePath.listFiles().length == 0) {
	    MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING);
	    messageBox.setText("Warning");
	    messageBox.setMessage("No resume found in public directory.");

	    int buttonID = messageBox.open();
	    if (buttonID == SWT.OK) {
		logger.error("No resumes found");
	    }
	    return false;
	} else {
	    return true;
	}
    }

    public void validateBeforeRead() {
	if (!this.resumesStoragePath.exists()) {
	    MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR);
	    messageBox.setText("Error");
	    messageBox.setMessage("Directory public not found!");

	    int buttonID = messageBox.open();
	    if (buttonID == SWT.OK) {
		logger.error("Directory public not found!");
	    }
	} else {
	    if (!isPublicDirContainsResumes()) {
		return;
	    }
	}
    }
}
