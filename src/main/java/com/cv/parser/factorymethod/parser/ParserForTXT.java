package com.cv.parser.factorymethod.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.FileFinderByExt;
import com.cv.parser.factorymethod.ExtensionSingleton;
import com.cv.parser.factorymethod.ExtensionSingleton.Ext;
import com.cv.parser.factorymethod.ParserInterface;

public class ParserForTXT implements ParserInterface {

    private final Logger logger = LoggerFactory.getLogger(ParserForTXT.class.getName());
    private FileFinderByExt find = new FileFinderByExt();

    private File[] txtFiles;
    private List<String> contents = new ArrayList<>();

    @Override
    public void setFiles() {
	this.txtFiles = find.finder(ExtensionSingleton.getInstance().get(Ext.TXT));
    }

    @Override
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

    @Override
    public List<String> getContents() {
	return contents;
    }
}
