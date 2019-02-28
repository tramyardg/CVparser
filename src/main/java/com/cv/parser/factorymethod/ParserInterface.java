package com.cv.parser.factorymethod;

import java.io.File;
import java.util.List;

public interface ParserInterface {

    void setFiles();

    void extractFiles();

    List<String> getContents();

    File[] finder(final String ext);

}
