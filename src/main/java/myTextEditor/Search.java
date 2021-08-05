package myTextEditor;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

public class Search extends DefaultHighlighter.DefaultHighlightPainter {
	// constructor
	public Search(Color color) {
		super(color);
	}

//should stay outside the class    
	static Highlighter.HighlightPainter myHightlighterPainter = new Search(Color.yellow);

//remove highlight b4 searching for other words
	public static void removeHighlights(JTextComponent textComp) {
		Highlighter hlighter = textComp.getHighlighter();
		Highlighter.Highlight[] hlights = hlighter.getHighlights();
		for (int i = 0; i < hlights.length; i++) {
			if (hlights[i].getPainter() instanceof Search) {
				hlighter.removeHighlight(hlights[i]);
			}
		}
	}

	public static void highlight(JTextComponent textComp, String key) {
		removeHighlights(textComp);
		try {
			Highlighter hlighter = textComp.getHighlighter();
			Document doc = textComp.getDocument();
			String text = doc.getText(0, doc.getLength());
			int pos = 0;
			while ((pos = text.toUpperCase().indexOf(key.toUpperCase(), pos)) >= 0) {
				hlighter.addHighlight(pos, pos + key.length(), myHightlighterPainter);
				pos += key.length();
			}
			if ((pos = text.toUpperCase().indexOf(key.toUpperCase(), pos)) < 0) {
			JOptionPane.showMessageDialog(null,key +" not found.");}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error: " + e);
		}
		;
	}
}