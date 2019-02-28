package com.cv.parser.saveas;

import com.cv.parser.CVparserSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CandidateJSON {

    private Logger logger = LoggerFactory.getLogger(CandidateJSON.class);

    public void writeToJSONfile(String data) {
        // TODO recreate the file every time application run not appending
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
