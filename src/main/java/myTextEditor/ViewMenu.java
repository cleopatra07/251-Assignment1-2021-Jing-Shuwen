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
		String info = "<html><h2>Thanks for using &copy;MyTextEditor </h2>"
				+ "<br/>"
				+"<p align=center>Authors can be reached on discord.";
					
		JOptionPane.showMessageDialog(TextEditor.frame,info,"About Us",JOptionPane.INFORMATION_MESSAGE);

       
	}

}
