package com.cv.parser.factorymethod;

import java.util.List;

public interface ParserInterface {

    void setFiles();

    void extractFiles();

    List<String> getContents();

}
