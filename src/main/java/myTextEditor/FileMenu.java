package myTextEditor;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;




public class FileMenu {
	
		private File file;
		//save the file as it is.
		protected void saveFile() throws Exception {
			this.save(file);
		}
		public void save (File file) {
			try {
				if (file ==null){
					this.saveAs();		
				}else {
					String content = TextEditor.textArea.getText();
					BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
					buffer.write(content);
					buffer.flush();
					buffer.close();
					JOptionPane.showMessageDialog(null,"Content Saved");
					}
			}catch(IOException e){
				JOptionPane.showMessageDialog(null,"Error!");
			}		
	}

		
	//save the current file with the selected path and filename.	
	public void saveAs() {
		
		try {				
			JFileChooser filechooser = new JFileChooser();
         	FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", ".txt");					
		    filechooser.setFileFilter(filter);
		    filechooser.setAcceptAllFileFilterUsed(false);
			filechooser.setDialogTitle("Save as");
			filechooser.showSaveDialog(null);
			String filename = filechooser.getSelectedFile().toString();
			if (!filename .endsWith(".txt"))
		        filename += ".txt";
			File file = new File(filename);
			//file = filechooser.getSelectedFile();
						
			String content =TextEditor.textArea.getText();
			BufferedWriter buffer ;
			
			buffer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			buffer.write(content);
			buffer.flush();
			buffer.close();
					
			TextEditor.frame.setTitle(file.getName());
			JOptionPane.showMessageDialog(null,"File Saved");
			
					
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
			openFile(file); 									
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Could not open the file. Caused by "+e);
		}	
	}
		
	public void openFile(File file) {
		BufferedReader buffer;
		String content="";
		try {	
				TextEditor.frame.setTitle(file.getName());
				buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String line;
				while((line = buffer.readLine())!=null) {
					content +=line + '\n';
				}					
				buffer.close();
				TextEditor.textArea.setText(content.toString());
			}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Could not open the file. Caused by "+e);
		}
	
	}

	//if file is not empty or it only contains  welcome message, abort the stale page, open a new one.
	//if the window title is a specific file title ,ask the user to save before open a new window.
	//else ask the user whether to save the changes.
	protected void newFile() throws Exception {
		
		if(((!TextEditor.textArea.getText().trim().equals("Welcome to Text Editor !") )
				|| (!TextEditor.textArea.getText().equals("")))
				&& (!TextEditor.frame.getTitle().equals(TextEditor.title))) {
			int choice = JOptionPane.showConfirmDialog(null, "Do you want to save the file?","WARNING", JOptionPane.YES_NO_OPTION );
			if (choice == JOptionPane.YES_OPTION) {
				saveFile();
			}
			
		}
		TextEditor.frame.setTitle( TextEditor.title);
		TextEditor.textArea.setText("");
				
	}
	
	
	
	//if file is not empty or it only contains welcome message, quit without asking.
	//if the window title is a specific file title , ask the user whether to save the page.
	//else ask the user whether to save the changes.
	//quit afterwards.
	protected void close() throws Exception {
		if(((!TextEditor.textArea.getText().equals("")) ||
			(!TextEditor.textArea.getText().trim().equals("Welcome to Text Editor !") ))
				&& (!TextEditor.frame.getTitle().equals(TextEditor.title)) ) {
			int choice = JOptionPane.showConfirmDialog(null, "Do you want to save the file?","WARNING", JOptionPane.YES_NO_OPTION );
			if (choice == JOptionPane.YES_OPTION) {
				saveFile();
		}
		}
		System.exit(13);
				
	}
	

	
}
