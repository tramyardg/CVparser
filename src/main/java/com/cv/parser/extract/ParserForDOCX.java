package com.cv.parser.extract;

import com.cv.parser.CVParserSingleton;
import com.cv.parser.extract.ExtensionSingleton.Ext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserForDOCX implements ParserInterface {
    private final static Logger LOG = LogManager.getLogger();
    private File resumeStorage = new File(CVParserSingleton.getInstance().resumesStoragePath);

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
                LOG.error(e.getMessage());
            } finally {
                try {
                    we.close();
                    msDoc.close();
                    fs.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage());
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
