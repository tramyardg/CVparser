package com.cv.parser.factorymethod.parser;

import com.cv.parser.CVParserSingleton;
import com.cv.parser.factorymethod.ExtensionSingleton;
import com.cv.parser.factorymethod.ExtensionSingleton.Ext;
import com.cv.parser.factorymethod.ParserInterface;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserForPDF implements ParserInterface {
    private File resumeStorage = new File(CVParserSingleton.getInstance().resumesStoragePath);
    private final Logger logger = LoggerFactory.getLogger(ParserForPDF.class.getName());

    private File[] pdfFiles;
    private List<String> contents = new ArrayList<>();

    @Override
    public void setFiles() {
        this.pdfFiles = finder(ExtensionSingleton.getInstance().get(Ext.PDF));
    }

    @Override
    public void extractFiles() {
        for (File file : pdfFiles) {
            try {
                PDDocument document = PDDocument.load(file);
                PDFTextStripper pdfStripper = new PDFTextStripper();
                String removedPageNumberRegex = "\\bPage\\d\\b";
                this.contents.add(pdfStripper.getText(document).replaceAll(removedPageNumberRegex, ""));
                document.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
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
