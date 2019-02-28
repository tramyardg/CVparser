package com.cv.parser.factorymethod.parser;

import com.cv.parser.CVParserSingleton;
import com.cv.parser.factorymethod.ExtensionSingleton;
import com.cv.parser.factorymethod.ExtensionSingleton.Ext;
import com.cv.parser.factorymethod.ParserInterface;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserForDOCX implements ParserInterface {
    private File resumeStorage = new File(CVParserSingleton.getInstance().resumesStoragePath);
    private final Logger logger = LoggerFactory.getLogger(ParserForDOCX.class.getName());

    private File[] docxFiles;
    private List<String> contents = new ArrayList<>();

    @Override
    public void setFiles() {
        this.docxFiles = finder(ExtensionSingleton.getInstance().get(Ext.DOCX));
    }

    @Override
    public void extractFiles() {
        for (File file : docxFiles) {
            FileInputStream fs = null;
            XWPFDocument msDoc = null;
            XWPFWordExtractor we = null;
            try {
                fs = new FileInputStream(file);
                msDoc = new XWPFDocument(fs);
                we = new XWPFWordExtractor(msDoc);
                this.contents.add(we.getText());
            } catch (IOException | NullPointerException e) {
                logger.error(e.getMessage());
            } finally {
                try {
                    we.close();
                    msDoc.close();
                    fs.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
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
