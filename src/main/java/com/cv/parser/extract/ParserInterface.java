package com.cv.parser.extract;

import java.io.File;
import java.util.List;

public interface ParserInterface {

    void setFiles();

    void extractFiles();

    List<String> getContents();

    File[] finder(final String ext);
        
    void extractSingleFile();
    
}
