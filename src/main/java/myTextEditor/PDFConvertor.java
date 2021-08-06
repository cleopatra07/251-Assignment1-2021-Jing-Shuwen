package myTextEditor;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFConvertor {

	public void txt2PDF(String content, String filePath) {
		PDDocument doc = new PDDocument();
		PDPage page = new PDPage();
		doc.addPage(page);
		PDPageContentStream stream;
		try {
			stream = new PDPageContentStream(doc, page);
			PDFont font = PDType1Font.HELVETICA;
			stream.setFont(font, 14);
			stream.beginText();
			stream.newLineAtOffset(10, 780);
			stream.showText(content);
			stream.endText();
			stream.close();
			doc.save(filePath);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Unable to convert: " + e);
		}
	}

}
