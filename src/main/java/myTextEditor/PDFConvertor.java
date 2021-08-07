package myTextEditor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFConvertor {
	private float fontSize;
	private float pageWidth;
	private float margin;
	private PDFont Pdfont = PDType1Font.HELVETICA;
	
	
	private List<String> breakLongString(String allText) {
		List<String> lines = new ArrayList<String>();
		int pageIndex = 63;
		
	   //roughly calculate number of characters each line
	    try {
			pageIndex = (int) ((int)pageWidth * 1000 / (fontSize*(Pdfont.getStringWidth("A"))));
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null,"Unable to convert: "+ e1);
		}
		
		
		for (String text : allText.split("\n"))
        {
            int lastSpace = -1;
            while (text.length() > 0)
            {
                int spaceIndex = text.indexOf(' ', lastSpace + 1);
                if (spaceIndex < 0)
                    spaceIndex = text.length();
                String subString = text.substring(0, spaceIndex);
                float size;
				try {
					size = fontSize * Pdfont.getStringWidth(subString) / 1000;
	                if (size > pageWidth)
	                  { 
	                    if (lastSpace < 0)
	                        lastSpace = spaceIndex;
	                    subString = text.substring(0, lastSpace);
		                while (lastSpace > pageIndex) {
		                	String temp= subString.substring(0, pageIndex);
		                	lines.add(temp);
		                	subString= subString.substring(pageIndex,lastSpace);
		                	lastSpace = subString.length();
		                }
	                    lines.add(subString);
	                    text = subString.substring(lastSpace).trim();
	                    System.out.println(" text :"+  text);
	                    lastSpace = -1;
	                }
	                else if (spaceIndex == text.length())
	                {
	                    lines.add(text);
	                    text = "";
	                }
	                else
	                {
	                    lastSpace = spaceIndex;
	                }
	                } catch (IOException e) {
						
	                	JOptionPane.showMessageDialog(null,"Unable to convert: "+ e);
					}
            }
        }
		
		return lines;
	}

	public void txt2PDF(String content, String filePath) {
		PDDocument doc = new PDDocument();
		PDPage page = new PDPage();
		doc.addPage(page);
		PDPageContentStream stream;
		
		fontSize =14;	
		margin = 10;
		PDRectangle mediabox = page.getMediaBox();
	    pageWidth = mediabox.getWidth() - 2*margin;
	    float x = mediabox.getLowerLeftX() + margin;
	    float y = mediabox.getUpperRightY() - 2*margin;
	    float leading = 1.5f * fontSize;
		
		try {
			stream = new PDPageContentStream(doc, page);
        	List<String> lines = breakLongString(content);
			
			for(String line : lines) {
		        stream.beginText();
		        stream.setFont(Pdfont, fontSize);
		        stream.newLineAtOffset(x, y);
		        stream.showText(line);
		        stream.endText();
				y -= leading;
		    }
			
			stream.close();
			doc.save(filePath);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Unable to convert: " + e);
		}
	}

}
