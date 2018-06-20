package com.cv.parser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class CVparserSingleton {
    private static CVparserSingleton instance;

    private CVparserSingleton() {
    }

    public static CVparserSingleton getInstance() {
	if (instance == null) {
	    instance = new CVparserSingleton();
	}
	return instance;
    }

    public String resumesStoragePath = "public";
    public final Color yellow = Display.getDefault().getSystemColor(SWT.COLOR_YELLOW);
    public final Color green = Display.getDefault().getSystemColor(SWT.COLOR_GREEN);
}
