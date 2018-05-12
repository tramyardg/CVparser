package com.cv.parser.extract;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.FileExtension;
import com.cv.parser.FileExtension.Ext;
import com.cv.parser.FileFinderByExt;

/**
 * Supports MS Word 2004+.
 * @author RAYMARTHINKPAD
 *
 */
public class MSExtractor extends ExtractFiles implements IExtractor {
	Logger logger = LoggerFactory.getLogger(MSExtractor.class);

	FileExtension fe = new FileExtension();
	FileFinderByExt find = new FileFinderByExt();

	File[] combinedMSDocs;
	List<String> contents = new ArrayList<String>();

	public MSExtractor(Button btnExtractContents, File[] filesInPublicDir, Table tableExtractedContent) {
		super(btnExtractContents, filesInPublicDir, tableExtractedContent);
	}

	public void main() {
		setFiles();
		extractFiles();
		displayIntable();
	}

	public void setFiles() {
		File[] doc = find.finder(fe.get(Ext.DOC));
		File[] docx = find.finder(fe.get(Ext.DOCX));
		if (doc.length != 0 && docx.length != 0) {
			this.combinedMSDocs = ArrayUtils.addAll(doc, docx);
		} else if (doc.length != 0) {
			this.combinedMSDocs = doc;
		} else if (docx.length != 0) {
			this.combinedMSDocs = docx;
		}
	}

	public void extractFiles() {
		for (File file : combinedMSDocs) {
			FileInputStream fs = null;
			XWPFDocument msDoc = null;
			XWPFWordExtractor we = null;
			try {
				fs = new FileInputStream(file);
				msDoc = new XWPFDocument(fs);
				we = new XWPFWordExtractor(msDoc);
				this.contents.add(we.getText());
			} catch (FileNotFoundException e) {
				logger.error(e.getMessage());
			} catch (IOException e) {
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

	public void displayIntable() {
		for (int i = 0; i < contents.size(); i++) {
			TableItem item = new TableItem(tableExtractedContent, SWT.NONE);
			item.setText(new String[] { "MSWORD", contents.get(i) });
		}
	}

}
