package com.cv.parser.saveas;

import com.cv.parser.CVparserSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CandidateJSON {

    private Logger logger = LoggerFactory.getLogger(CandidateJSON.class);

    private Map<String, List<CandidateBean>> map = new HashMap<>();

    public void setMap(Map<String, List<CandidateBean>> map) {
        this.map = map;
    }

    public void writeToJSONfile(String data) {
        File jsonFile = new File(CVparserSingleton.getInstance().resumesStoragePath, "candidates.json");
        FileWriter writer;
        try {
            writer = new FileWriter(jsonFile);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            logger.error("Exception found!", e);
        }
    }

}
