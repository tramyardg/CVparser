package com.cv.parser.extract;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;

import com.cv.parser.FileExtension;
import com.cv.parser.FileExtension.Ext;
import com.cv.parser.FileFinderByExt;

public class RTFExtractor implements IExtractor {

    private File[] files;
    private List<String> contents = new ArrayList<>();

    private FileExtension fileExtension = new FileExtension();
    private FileFinderByExt fileFinderByExt = new FileFinderByExt();

    @Override
    public void main() {
	setFiles();
	extractFiles();
    }

    @Override
    public void setFiles() {
	this.files = fileFinderByExt.finder(fileExtension.get(Ext.RTF));
    }

    @Override
    public void extractFiles() {
	for (File file : files) {
	    FileInputStream stream;
	    try {
		stream = new FileInputStream(file);
		RTFEditorKit kit = new RTFEditorKit();
		Document doc = kit.createDefaultDocument();
		kit.read(stream, doc, 0);
		String plainText = doc.getText(0, doc.getLength());
		this.contents.add(plainText);
	    } catch (IOException | BadLocationException e) {
		e.printStackTrace();
	    }
	}
    }

    public List<String> getContents() {
	return contents;
    }
}
