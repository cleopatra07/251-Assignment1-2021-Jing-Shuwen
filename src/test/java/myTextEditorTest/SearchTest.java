package myTextEditorTest;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JTextArea;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import myTextEditor.Search;

 class SearchTest {
	JTextComponent text;
	String keySingle;
	String keyLong;

	@BeforeEach
	public void setUp() throws Exception {
		text = new JTextArea();
		text.setText("akbkck123k");
		keySingle = "k";
		keyLong = "123k";
	}

	@Test
	public void testHighlightSingleWord() {
		if (text != null) {
		Search.highlight(text, keySingle);
		Highlighter hlighter = text.getHighlighter();
		Highlighter.Highlight[] hlights = hlighter.getHighlights();
		assertEquals(hlights.length, 4);
		for (int i = 0; i < hlights.length; i++) {
			String actual = text.getText().substring(hlights[i].getStartOffset(), hlights[i].getEndOffset());
			assertEquals(keySingle, actual);
		}
	  }
	}

	@Test
	public void testHighlightLongWord() {
		if (text != null) {
		Search.highlight(text, keyLong);
		Highlighter hlighter = text.getHighlighter();
		Highlighter.Highlight[] hlights = hlighter.getHighlights();
		assertEquals(hlights.length, 1);
		for (int i = 0; i < hlights.length; i++) {
			String actual = text.getText().substring(hlights[i].getStartOffset(), hlights[i].getEndOffset());
			assertEquals(keyLong, actual);
		}
		}
	}

	@AfterEach
	public void tearDown(TestInfo testInfo) {
		text = null;
		keySingle = null;
		keyLong = null;
		assertNull(text);
		assertNull(keySingle);
		assertNull(keyLong);
	}

}
