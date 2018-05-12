package com.cv.parser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class Constants {
	private static Constants instance;

	private Constants() {
	}

	public static Constants getInstance() {
		if (instance == null) {
			instance = new Constants();
		}
		return instance;
	}

	String resumesStoragePath = "public";
	public final Color yellow = Display.getDefault().getSystemColor(SWT.COLOR_YELLOW);
	public final Color green = Display.getDefault().getSystemColor(SWT.COLOR_GREEN);
}
