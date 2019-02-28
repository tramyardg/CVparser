package com.cv.parser.extract;

import com.cv.parser.CVParserSingleton;
import com.cv.parser.extract.ExtensionSingleton.Ext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserForDOC implements ParserInterface {
    private final static Logger LOG = LogManager.getLogger();
    private File resumeStorage = new File(CVParserSingleton.getInstance().resumesStoragePath);

    private File[] docFiles;
    private List<String> contents = new ArrayList<>();

    @Override
    public void setFiles() {
        this.docFiles = finder(ExtensionSingleton.getInstance().get(Ext.DOC));
    }

    @Override
    public void extractFiles() {
        for (File file : docFiles) {
            HWPFDocument hwpfdoc;
            WordExtractor extractor = null;
            try {
                hwpfdoc = new HWPFDocument(new FileInputStream(file));
                extractor = new WordExtractor(hwpfdoc);
                this.contents.add(extractor.getText());
            } catch (IOException e) {
                LOG.info(e.getMessage());
            } finally {
                try {
                    extractor.close();
                } catch (IOException e) {
                    LOG.warn(e.getMessage());
                }
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
