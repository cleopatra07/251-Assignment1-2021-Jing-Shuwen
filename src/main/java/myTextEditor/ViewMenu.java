package myTextEditor;

import java.util.Date;
import javax.swing.JOptionPane;

public class ViewMenu {
	
	//display date and time on the banner.
	public void viewTime() {
		String today = String.format("%Ta, %<TB %<Td %<TY %<TI:%<TM:%<TS %<Tp",new Date() ); 
		TextEditor.frame.setTitle(today);
	}
	
	
	//about the app and authors.
	public void aboutUs() {
		String info = "<html><h2>Thanks for using MyTextEditor </h2>"
				+ "<br/>"
				+"<p align=left>Authors Information</p>"
				+"<ul align=left><li>Shuwen 20010847</li><li>Jing 20018322</li></ul>";
					
		JOptionPane.showMessageDialog(TextEditor.frame,info,"About Us",JOptionPane.INFORMATION_MESSAGE);

       
	}

}
