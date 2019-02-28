package com.cv.parser;

import java.io.File;

public class FileFinderByExt {

    private File resumeStorage = new File(CVparserSingleton.getInstance().resumesStoragePath);

    /**
     * It returns an array of files given an extension.
     *
     * @param ext extension
     * @return File[]
     */
    public File[] finder(final String ext) {
        return resumeStorage.listFiles((dir, filename) -> filename.endsWith(ext));
    }

}
