package com.cv.parser.saveas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.CVparserSingleton;

public class CandidateJSON {

    Logger logger = LoggerFactory.getLogger(CandidateJSON.class);

    private Map<String, List<CandidateBean>> map = new HashMap<>();

    public Map<String, List<CandidateBean>> getMap() {
	return map;
    }

    public void setMap(Map<String, List<CandidateBean>> map) {
	this.map = map;
    }

    public void writeToJSONfile(String data) {
	File jsonFile = new File(CVparserSingleton.getInstance().resumesStoragePath, "candidates.json");
	FileWriter writer = null;
	try {
	    writer = new FileWriter(jsonFile);
	    writer.write(data);
	    writer.close();
	    logger.debug("Write to JSON file success!");
	} catch (IOException e) {
	    logger.error("Exception found!", e);
	}
    }

}
