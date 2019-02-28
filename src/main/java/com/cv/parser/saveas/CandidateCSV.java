package com.cv.parser.saveas;

import com.opencsv.CSVWriter;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.file.StandardOpenOption.CREATE;

public class CandidateCSV {

    private final Logger logger = Logger.getLogger(CandidateCSV.class.getName());
    private static final String IO_EXCEPTION_FOUND = "IO Exception found!";
    private static final String CSV_DATA_PATH = "./public/candidates.csv";

    private List<String[]> toStringArray(List<CandidateBean> candidateList) {
        List<String[]> records = new ArrayList<>();
        Iterator<CandidateBean> it = candidateList.iterator();
        while (it.hasNext()) {
            CandidateBean candidate = it.next();
            records.add(new String[]{candidate.getProfile(), candidate.getEducation(), candidate.getExperiences(),
                    candidate.getSkills()});
        }
        return records;
    }

    public void saveDataInCSVfile(List<CandidateBean> candidateList) {
        StringWriter writer = new StringWriter();
        try {
            CSVWriter csvWriter = new CSVWriter(writer, ',');
            List<String[]> data = toStringArray(candidateList);
            csvWriter.writeAll(data);
            csvWriter.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, IO_EXCEPTION_FOUND, e);
        }
        byte[] data = writer.toString().getBytes();
        Path p = Paths.get(CSV_DATA_PATH);
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE))) {
            out.write(data, 0, data.length);
        } catch (IOException | NullPointerException x) {
            logger.log(Level.WARNING, IO_EXCEPTION_FOUND, x);
        }
    }
}
