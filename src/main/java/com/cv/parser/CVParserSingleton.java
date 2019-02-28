package com.cv.parser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class CVParserSingleton {
    private static CVParserSingleton instance;

    private CVParserSingleton() {
    }

    public static CVParserSingleton getInstance() {
        if (instance == null) {
            instance = new CVParserSingleton();
        }
        return instance;
    }

    public String resumesStoragePath = "public";
    public final Color yellow = Display.getDefault().getSystemColor(SWT.COLOR_YELLOW);
}
