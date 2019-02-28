package com.cv.parser.factorymethod.parser;

import com.cv.parser.CVParserSingleton;
import com.cv.parser.factorymethod.ExtensionSingleton;
import com.cv.parser.factorymethod.ExtensionSingleton.Ext;
import com.cv.parser.factorymethod.ParserInterface;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserForDOC implements ParserInterface {
    private File resumeStorage = new File(CVParserSingleton.getInstance().resumesStoragePath);
    private final Logger logger = LoggerFactory.getLogger(ParserForDOC.class.getName());

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
                logger.info(e.getMessage());
            } finally {
                try {
                    extractor.close();
                } catch (IOException e) {
                    logger.warn(e.getMessage());
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
