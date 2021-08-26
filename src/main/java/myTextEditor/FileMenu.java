package myTextEditor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.aspose.words.Document;
import com.aspose.words.SaveFormat;


public class FileMenu {
	
		private File file;
		//save the file as it is.
		protected void saveFile() throws Exception {
			this.save(file);
		}
		public void save (File file) {
			try {
				
				if ((file ==null)||(file.getName()==null)){
					this.saveAs();		
				}else {
					String content = TextEditor.textArea.getText();
					BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
					buffer.write(content);
					buffer.flush();
					buffer.close();
					JOptionPane.showMessageDialog(null,"Content Saved");

				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Error!");
			}		
	}

		
	//save the current file with the selected path and filename.	
	public void saveAs() {
		
		try {				
			JFileChooser filechooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", ".txt");		
			filechooser.addChoosableFileFilter(filter);
		    filechooser.setFileFilter(filter);
		    filechooser.setAcceptAllFileFilterUsed(false);
			filechooser.setDialogTitle("Save as");
			int i = filechooser.showSaveDialog(null);
			if (i == JFileChooser.CANCEL_OPTION) {
				return;
			}else {
				String filename = filechooser.getSelectedFile().toString();
				if (!filename .endsWith(".txt")) {
					filename += ".txt";
				}
				file = new File(filename);		
				String content =TextEditor.textArea.getText();
				BufferedWriter buffer ;
			
				buffer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
				buffer.write(content);
				buffer.flush();
				buffer.close();
			
				TextEditor.frame.setTitle(file.getName());
				JOptionPane.showMessageDialog(null,"File Saved");		
			}		
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"File not saved \n Caused by "+e);
		} 
				
		
	}
	
	//opne file in the file system.
	public void open() {
		try {	
			JFileChooser filechooser= new JFileChooser();
			filechooser.setDialogTitle("Choose file to open");
			int i = filechooser.showOpenDialog(TextEditor.frame);
			file =filechooser.getSelectedFile();
			if (i == JFileChooser.CANCEL_OPTION) {
				return;
			}else {
				if(!file.exists()) {
					JOptionPane.showMessageDialog(null,"no such file");
					return;
				}	
			 }	
			this.openFile(file); 									
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Could not open the file. Caused by "+e);
		}	
	}
		
	public void openFile(File file) {
		BufferedReader buffer;
		
		String content="";
		try {	
			    String path = file.getAbsolutePath();
			    String fileName = file.getName();
			    TextEditor.frame.setTitle(fileName);
				//load to aspose doc
				Document doc = new Document(path);
				//convert odt to text file 
			    if (path.contains(".odt")) {
			    	doc.save("tempODT2TXT.text",SaveFormat.TEXT); 
			    	doc = new Document("tempODT2TXT.text");
			    }
				
				String text = doc.getText();
				Reader inputString = new StringReader(text);
				buffer = new BufferedReader(inputString);
				String line;
				while((line = buffer.readLine())!=null) {
					//if the line we're on contains the text we don't want to add, skip it
			        if (line.contains("Created with an evaluation copy of Aspose.Words. To discover the full versions of our APIs please visit: https://products.aspose.com/words/")) {
			            //skip
			            continue;
			        }
			        if (line.contains("Evaluation Only. Created with Aspose.Words. Copyright 2003-2018 Aspose Pty Ltd.")) {
			        	//skip
			            continue;
			        }
					content +=line + '\n';
				}					
				buffer.close();
				TextEditor.textArea.setText(content);
			}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Could not open the file. Caused by "+e);
		}
	
	}

	//if file is empty or it only contains  welcome message, abort the stale page, open a new one.
	//if the window title is a specific file title ,ask the user to save before open a new window.
	protected void newFile() throws Exception {
			if ((!TextEditor.textArea.getText().equals("Welcome to Text Editor !"))&&(!TextEditor.textArea.getText().equals(""))){
				int choice = JOptionPane.showConfirmDialog(null, "Do you want to save the file?","WARNING", JOptionPane.YES_NO_OPTION );
				if (choice == JOptionPane.YES_OPTION) {	
					if (!TextEditor.frame.getTitle().equals(TextEditor.title)) {
						this.saveFile();
					}else {
						this.saveAs();				
					}	
				}
			}
			TextEditor.frame.setTitle( TextEditor.title);
			TextEditor.textArea.setText("");				
	}
	
	
	
	//if file is empty or it only contains welcome message, quit without asking.
	//if the window title is a specific file title , ask the user whether to save the page.
	//quit afterwards.
	protected void close() throws Exception{
		
		if ((!TextEditor.frame.getTitle().equals(TextEditor.title))&&(!TextEditor.textArea.getText().equals("Welcome to Text Editor !"))
				&&(!TextEditor.textArea.getText().equals(""))){
			
			System.out.println(TextEditor.title);
			int choice = JOptionPane.showConfirmDialog(null, "Do you want to save the file?","WARNING", JOptionPane.YES_NO_OPTION );
			if (choice == JOptionPane.YES_OPTION) {
				this.saveFile();
		}
		}
		System.exit(13);
				
	}
}
