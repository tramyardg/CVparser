package com.cv.parser.extract;

import java.io.File;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;

public class MSExtractor extends ExtractFiles implements IExtractor {

	public MSExtractor(Button btnExtractContents, File[] filesInPublicDir, Table tableExtractedContent) {
		super(btnExtractContents, filesInPublicDir, tableExtractedContent);
	}

	public void main() {
		setFiles();
		extractFiles();
		displayIntable();
	}

	public void setFiles() {
		// TODO Auto-generated method stub
		
	}

	public void extractFiles() {
		// TODO Auto-generated method stub
		
	}

	public void displayIntable() {
		// TODO Auto-generated method stub
		
	}

}
