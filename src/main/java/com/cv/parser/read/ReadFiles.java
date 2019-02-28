package com.cv.parser.read;

import com.cv.parser.CVparserSingleton;
import com.cv.parser.factorymethod.ExtensionSingleton;
import com.cv.parser.factorymethod.ExtensionSingleton.Ext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import java.io.File;
import java.util.Arrays;

public class ReadFiles {
    private Button btnReadDir;
    private File[] filesInPublicDir;
    private Table tableDirContent;

    private Button extractBtn;

    public ReadFiles(Button btnReadDir, File[] filesInPublicDir, Table tableDirContent, Button btnExtractContents) {
        this.btnReadDir = btnReadDir;
        this.filesInPublicDir = filesInPublicDir;
        this.tableDirContent = tableDirContent;
        this.extractBtn = btnExtractContents;
    }

    public void handleButtonClick() {
        btnReadDir.addListener(SWT.Selection, arg0 -> {
            setFiles();
            btnReadDir.setEnabled(false);
            extractBtn.setEnabled(true);
        });
    }

    private void setFiles() {
        for (int i = 0; i < filesInPublicDir.length; i++) {
            if (filesInPublicDir[i].isFile()) {
                String fileName = filesInPublicDir[i].getName();
                String ext = fileName.substring(fileName.indexOf('.'));
                TableItem item = new TableItem(tableDirContent, SWT.NONE);
                String[] fExts = {ExtensionSingleton.getInstance().get(Ext.TXT),
                        ExtensionSingleton.getInstance().get(Ext.PDF), ExtensionSingleton.getInstance().get(Ext.DOCX),
                        ExtensionSingleton.getInstance().get(Ext.DOC)};
                if (Arrays.asList(fExts).contains(ext)) {
                    item.setText(new String[]{(i + 1) + "", ext, fileName});
                } else {
                    item.setText(new String[]{(i + 1) + "", ext, fileName});
                    item.setBackground(CVparserSingleton.getInstance().yellow);
                }
            }
        }
    }

}
