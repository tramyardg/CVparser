package com.cv.parser.extract;

import com.cv.parser.CVParserSingleton;
import com.cv.parser.extract.ExtensionSingleton.Ext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserForTXT implements ParserInterface {
    private final static Logger LOG = LogManager.getLogger();
    private File resumeStorage = new File(CVParserSingleton.getInstance().resumesStoragePath);

    private File[] txtFiles;
    private List<String> contents = new ArrayList<>();

    @Override
    public void setFiles() {
        this.txtFiles = finder(ExtensionSingleton.getInstance().get(Ext.TXT));
    }

    @Override
    public void extractFiles() {
        String line;
        for (File file : txtFiles) {
            BufferedReader br;
            try {
                FileReader fileReader = new FileReader(file);
                List<String> content = new ArrayList<>();
                br = new BufferedReader(fileReader);
                while ((line = br.readLine()) != null) {
                    content.add(line);
                }
                this.contents.add(String.join(" ", content));
                br.close();
                fileReader.close();
            } catch (IOException e) {
                LOG.error(e.getMessage());
            }
        }
    }

    @Override
    public List<String> getContents() {
        return contents;
    }

    @Override
    public File[] finder(String ext) {
        return resumeStorage.listFiles((dir, filename) -> filename.endsWith(ext));
    }
}
