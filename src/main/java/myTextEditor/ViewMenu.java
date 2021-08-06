package myTextEditor;

import java.util.Date;

public class ViewMenu {
	
	public void viewTime() {
		String today = String.format("%Ta, %<TB %<Td %<TY %<TI:%<TM:%<TS %<Tp",new Date() ); 
		TextEditor.frame.setTitle(today);
	}
	
//	public void aboutUs() {
//		
//		
//	}
	

}
