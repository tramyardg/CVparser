package com.cv.parser.extract;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.FileExtension;
import com.cv.parser.FileExtension.Ext;
import com.cv.parser.FileFinderByExt;

public class TXTExtractor implements IExtractor {
    Logger logger = LoggerFactory.getLogger(TXTExtractor.class);

    private FileExtension fe = new FileExtension();
    private FileFinderByExt find = new FileFinderByExt();

    File[] txtFiles;
    List<String> contents = new ArrayList<String>();

    File[] filesInPublicDir;
    
    public TXTExtractor(File[] filesInPublicDir) {
	this.filesInPublicDir = filesInPublicDir;
    }

    public void main() {
	setFiles();
	extractFiles();
    }

    public void setFiles() {
	this.txtFiles = find.finder(fe.get(Ext.TXT));
    }

    public void extractFiles() {
	String line = null;
	for (File file : txtFiles) {
	    BufferedReader br = null;
	    try {
		FileReader fileReader = new FileReader(file);
		List<String> content = new ArrayList<String>();
		br = new BufferedReader(fileReader);
		while ((line = br.readLine()) != null) {
		    content.add(line);
		}
		this.contents.add(String.join(" ", content));
		br.close();
	    } catch (IOException e) {
		logger.error(e.getMessage());
	    }
	}
    }
    
    public List<String> getContents() {
	return contents;
    }
}
