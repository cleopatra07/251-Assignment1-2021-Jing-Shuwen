package myTextEditor;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFConvertor {
//convert text file to PDF file 
	public void txt2PDF(String content, String path) {
		try {
			Document doc = new Document();
			OutputStream os = Files.newOutputStream(Paths.get(path+".pdf"));
			PdfWriter.getInstance(doc, os);
			doc.open();
			doc.add(new Paragraph(content));
			os.close();
			doc.close();
			JOptionPane.showMessageDialog(null, "PDF saved");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Unable to convert: " + e);

		}
	}

}
