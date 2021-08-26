package myTextEditorTest;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import myTextEditor.FileMenu;
import myTextEditor.TextEditor;

class SaveTest {
	String con;
	BufferedReader buffer;
	TextEditor textEditor ;
	FileMenu fm ;
	File file;
	PrintWriter write;

	
	// empty file stores in test/resources folder.
	@BeforeEach
	public void setUp() throws IOException {
		textEditor = new TextEditor();
		fm = new FileMenu ();	
		file =new File(new File("src/test/resources/saveFile.txt").getAbsolutePath());
		con="";	
		
	} 
	
	
	
	//delete the content in the file after each test.
	@AfterEach
	public void teardown() throws IOException {
		 write = new PrintWriter(file);
		 write.append("");
		 write.flush();
		 write.close();
		 con="";
		 buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		 String line;
		 	while((line = buffer.readLine())!=null) {
		 		con +=line + '\n';
		 	}					
		 buffer.close();
		 //assertEquals(con.toString().trim(),"");
		 
	}
	
	//test on empty file , implement save to the  initial screen which contains welcoming message. 
	@Test
	public void testSave() throws IOException {
		assertTrue(file.exists());
		
		fm.save(file);
		buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line;
		while((line = buffer.readLine())!=null) {
			con +=line + '\n';
		}					
		buffer.close();
		assertEquals(con.trim(),"Welcome to Text Editor !");
			
	}
	

}
