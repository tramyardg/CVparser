package com.cv.parser.extract;

import com.cv.parser.UnsupportedFileExtension;

public class ExtensionSingleton {

    private static ExtensionSingleton instance;

    private ExtensionSingleton() {

    }

    public static ExtensionSingleton getInstance() {
        if (instance == null) {
            instance = new ExtensionSingleton();
        }
        return instance;
    }

    public enum Ext {
        PDF, DOC, DOCX, TXT
    }

    public String get(Ext ext) {
        switch (ext) {
            case PDF:
                return ".pdf";
            case DOC:
                return ".doc";
            case DOCX:
                return ".docx";
            case TXT:
                return ".txt";
        }
        return new UnsupportedFileExtension().getMessage();
    }

}
