package myTextEditorTest;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import myTextEditor.FileMenu;
import myTextEditor.TextEditor;

class SaveTest {
	String con;
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
		 write.write("");
		 write.flush();
		 write.close();
		 con="";
		 con = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));		 
	}
	
	//test on empty file , implement save to the  initial screen which contains welcoming message. 
	@Test
	public void testSave() throws IOException {
		assertTrue(file.exists());
		
		fm.save(file);
		con = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

		assertEquals( con.trim(),"Welcome to Text Editor !");
			
	}
	

}
