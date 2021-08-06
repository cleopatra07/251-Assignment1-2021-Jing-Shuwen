package myTextEditor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileMenu {
	
	private File file;
	
	public void save () {
		try {
		
			String content = TextEditor.textArea.getText();
			BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			buffer.write(content);
			buffer.flush();
			buffer.close();
			JOptionPane.showMessageDialog(null,"Content Saved");
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error!");
		}
				
		
		
	}
	public void saveAs() {
		
		try {	
			JFileChooser filechooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
			filechooser.addChoosableFileFilter(filter);
		    filechooser.setFileFilter(filter);
		    filechooser.setAcceptAllFileFilterUsed(true);
			filechooser.setDialogTitle("Save as");
			filechooser.showSaveDialog(null);
			file = filechooser.getSelectedFile();
			
			
			String content =TextEditor.textArea.getText();
			BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			buffer.write(content);
			buffer.flush();
			buffer.close();
		
			
			TextEditor.frame.setTitle(file.getName());
			JOptionPane.showMessageDialog(null,"File Saved");
			
					
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"File not saved \n Caused by "+e);
		}
				
		
	}

	protected void openFile() {
		BufferedReader buffer;
		String content="";
		try {
		
			JFileChooser filechooser= new JFileChooser();
			filechooser.setDialogTitle("Choose file to open");
			int i = filechooser.showOpenDialog(TextEditor.frame);
			file =filechooser.getSelectedFile();
			if (i == JFileChooser.APPROVE_OPTION) {
				if(!file.exists()) {
					JOptionPane.showMessageDialog(null,"no such file");
					return;
				}
			
				TextEditor.frame.setTitle(file.getName());
				buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String line;
				while((line = buffer.readLine())!=null) {
					content +=line + '\n';
				}					
				buffer.close();
				TextEditor.textArea.setText(content.toString());
			}							
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Could not open the file \n Caused by "+e);
		}
	
	}

	protected void newFile() {
		
		if(!TextEditor.textArea.getText().equals("") 
				|| (!TextEditor.frame.getTitle().equals(TextEditor.title)) ){
			int choice = JOptionPane.showConfirmDialog(null, "Do you want to save the file?","WARNING", JOptionPane.YES_NO_OPTION );
			if (choice == JOptionPane.YES_NO_OPTION) {
				save();
			}
			
		}
		TextEditor.frame.setTitle( TextEditor.title);
		TextEditor.textArea.setText("");
				
	}
	protected void close() {
		if(!TextEditor.textArea.getText().equals("") 
				|| (!TextEditor.frame.getTitle().equals(TextEditor.title)) ) {
			int choice = JOptionPane.showConfirmDialog(null, "Do you want to save the file?","WARNING", JOptionPane.YES_NO_OPTION );
			if (choice == JOptionPane.YES_NO_OPTION) {
				save();
		}
		}
		System.exit(13);
		
	
		
	}


}
