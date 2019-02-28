package com.cv.parser.saveas;

import com.opencsv.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE;

public class WriteToFile {
    public WriteToFile() {
    }

    private final static Logger LOG = LogManager.getLogger();
    private static final String JSON_DATA_PATH = "./public/candidates.json";
    private static final String CSV_DATA_PATH = "./public/candidates.csv";
    private boolean hasUpdated = false;

    public boolean isHasUpdated() {
        return hasUpdated;
    }

    public void writeToJSON(String content) {
        byte[] data = content.getBytes();
        Path p = Paths.get(JSON_DATA_PATH);
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE))) {
            out.write(("").getBytes());
            out.write(data, 0, data.length);
            hasUpdated = true;
        } catch (IOException x) {
            LOG.error("Exception found!", x);
        }
    }

    private List<String[]> toStringArray(List<CandidateBean> candidateList) {
        List<String[]> records = new ArrayList<>();
        for (CandidateBean candidate : candidateList) {
            records.add(new String[]{candidate.getProfile(), candidate.getEducation(), candidate.getExperiences(),
                    candidate.getSkills()});
        }
        return records;
    }

    public void writeToCSV(List<CandidateBean> candidateList) {
        StringWriter writer = new StringWriter();
        try {
            CSVWriter csvWriter = new CSVWriter(writer, ',');
            List<String[]> data = toStringArray(candidateList);
            csvWriter.writeAll(data);
            csvWriter.close();
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        byte[] data = writer.toString().getBytes();
        Path p = Paths.get(CSV_DATA_PATH);
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE))) {
            out.write(data, 0, data.length);
            hasUpdated = true;
        } catch (IOException | NullPointerException x) {
            LOG.error(x.getMessage());
        }
    }

}
