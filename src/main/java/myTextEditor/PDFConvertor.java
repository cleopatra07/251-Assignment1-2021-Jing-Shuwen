package myTextEditor;

import java.io.FileOutputStream;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFConvertor {

	public void txt2PDF(String content, String path, String fileName) {
		try {
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(path + fileName + ".pdf"));
			doc.open();
			doc.add(new Paragraph(content));
			doc.close();
			JOptionPane.showMessageDialog(null, "PDF saved");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Unable to convert: " + e);

		}
	}

}
