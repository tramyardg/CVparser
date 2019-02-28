package com.cv.parser.read;

import com.cv.parser.CVParserSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import java.io.File;

public class ValidateRead {
    private final static Logger LOG = LogManager.getLogger();
    private File resumesStoragePath = new File(CVParserSingleton.getInstance().resumesStoragePath);
    private Shell shell;

    public ValidateRead(Shell shell) {
        this.shell = shell;
    }

    private void isPublicDirContainsResumes() {
        if (this.resumesStoragePath.listFiles().length == 0) {
            MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING);
            messageBox.setText("Warning");
            messageBox.setMessage("No resume found in public directory.");

            int buttonID = messageBox.open();
            if (buttonID == SWT.OK) {
                LOG.error("No resumes found");
            }
        }
    }

    public void validateBeforeRead() {
        if (!this.resumesStoragePath.exists()) {
            MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR);
            messageBox.setText("Error");
            messageBox.setMessage("Directory public not found!");

            int buttonID = messageBox.open();
            if (buttonID == SWT.OK) {
                LOG.error("Directory public not found!");
            }
        } else {
            isPublicDirContainsResumes();
        }
    }
}
