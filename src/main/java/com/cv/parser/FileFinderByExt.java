package com.cv.parser;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

import com.cv.parser.factorymethod.ExtensionSingleton;
import com.cv.parser.factorymethod.ExtensionSingleton.Ext;

public class FileFinderByExt {

    private File resumeStorage = new File(CVparserSingleton.getInstance().resumesStoragePath);

    /**
     * It returns an array of files given an extension.
     * 
     * @param ext
     *            extension
     * @return File[]
     */
    public File[] finder(final String ext) {
	return resumeStorage.listFiles(new FilenameFilter() {
	    public boolean accept(File dir, String filename) {
		return filename.endsWith(ext);
	    }
	});
    }

    /**
     * Count only the allowed extensions.
     * 
     * @return total
     */
    public int countAcceptableFiles() {
	int[] counts = { finder(ExtensionSingleton.getInstance().get(Ext.TXT)).length,
		finder(ExtensionSingleton.getInstance().get(Ext.PDF)).length,
		finder(ExtensionSingleton.getInstance().get(Ext.DOCX)).length,
		finder(ExtensionSingleton.getInstance().get(Ext.DOC)).length };
	return Arrays.stream(counts).sum();
    }
}
