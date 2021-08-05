package myTextEditor;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class TextEditor extends JFrame implements Serializable{
	
	private static final long serialVersionUID = 7842805864855355915L;

	// fields
	private static TextEditor WINDOW;
	private JFrame frame;
	private JTextArea textArea;
	private JScrollPane scroll;
	private JMenuBar menuBar;
	private JMenu fileMenu, searchMenu, viewMenu, manageMenu, helpMenu;
	private JMenuItem printButton, openButton, newButton, saveButton, aboutButton, selectAllButton, timeButton,
						pdfButton,searchButton, exitButton, cutButton, copyButton ,pasteButton;
	
	

	// launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    WINDOW = new TextEditor();
					WINDOW.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Application constructor
	public TextEditor() {
			
		initialize();
		
	
	}
	

	//initialize the window.
	private void initialize() {
		//Set up frame 
		frame = new JFrame();
		frame.setTitle("My Text Editor");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		//Set up textArea with scrollbar.scrollbar appears when needed.
		textArea = new JTextArea();
		frame.getContentPane().add(textArea, BorderLayout.NORTH);	
		scroll = new JScrollPane(textArea);
		frame.getContentPane().add(scroll, BorderLayout.CENTER);
		
		
		menuBar = new JMenuBar();
		menuBar.setForeground(Color.PINK);
		menuBar.setBackground(Color.DARK_GRAY);
		frame.setJMenuBar(menuBar);
		
		fileMenu = new JMenu("File");
		fileMenu.setBackground(Color.GRAY);
		menuBar.add(fileMenu);
		
		// Create new file
		newButton = new JMenuItem("New");
//		newButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//newFile();
//			}
//		});
//		
		newButton.setBackground(Color.LIGHT_GRAY);
		fileMenu.add(newButton);
		
		//Open file
		openButton = new JMenuItem("Open");
//		openButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//openFile();
//			}
//		});
		openButton.setBackground(Color.LIGHT_GRAY);
		fileMenu.add(openButton);
		
		// Save file 
		saveButton = new JMenuItem("Save");
//		saveButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//save();
//			}
//		});
		saveButton.setBackground(Color.LIGHT_GRAY);
		fileMenu.add(saveButton);
		
		
		//Exit the window
		exitButton = new JMenuItem("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					System.exit(DISPOSE_ON_CLOSE);	
			}
		});
		exitButton.setBackground(Color.LIGHT_GRAY);
		fileMenu.add(exitButton);
		
		
		
		searchMenu = new JMenu("Search");
		searchMenu.setBackground(Color.GRAY);
		menuBar.add(searchMenu);
		
		//Search for words.
		searchButton = new JMenuItem("search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame();  
				String searchKey = JOptionPane.showInputDialog(f,"Search string: ");
                Search.highlight(textArea, searchKey);
                
			}
		});
		searchButton.setBackground(Color.LIGHT_GRAY);
		searchMenu.add(searchButton);
		
		
		viewMenu = new JMenu("View");
		viewMenu.setBackground(Color.GRAY);
		menuBar.add(viewMenu);
		
		
		//display time and date
		timeButton = new JMenuItem("Time and Date");
//		timeButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//viewTime();
//			}
//		});
		timeButton.setBackground(Color.LIGHT_GRAY);
		viewMenu.add(timeButton);
		
		//Authors' info
		aboutButton = new JMenuItem("About");
//		aboutButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//aboutMe();
//			}
//		});
		aboutButton.setBackground(Color.LIGHT_GRAY);
		viewMenu.add(aboutButton);
		
		manageMenu = new JMenu("Manage");
		manageMenu.setBackground(Color.LIGHT_GRAY);
		menuBar.add(manageMenu);
		
		// Select the whole page
		selectAllButton = new JMenuItem("Select All");
		selectAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.selectAll();
			}
		});
		selectAllButton.setBackground(Color.LIGHT_GRAY);
		manageMenu.add(selectAllButton);
		
		//Function cut
		cutButton = new JMenuItem("Cut");
		cutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.cut();
			}
		});
		cutButton.setBackground(Color.LIGHT_GRAY);
		manageMenu.add(cutButton);
		
		//Function Copy.
		copyButton = new JMenuItem("Copy");
		copyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.copy();
			}
		});
		copyButton.setBackground(Color.LIGHT_GRAY);
		manageMenu.add(copyButton);
		
		//Function paste
		pasteButton = new JMenuItem("Paste");
		pasteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.paste();
			}
		});
		pasteButton.setBackground(Color.LIGHT_GRAY);
		manageMenu.add(pasteButton);
		
		helpMenu = new JMenu("Help");
		helpMenu.setBackground(Color.GRAY);
		menuBar.add(helpMenu);
		
		//connect to the printer
		printButton = new JMenuItem("Print");
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//print();
			}
		});
		printButton.setBackground(Color.LIGHT_GRAY);
		helpMenu.add(printButton);
		
		//Convert TO PDF file
		pdfButton = new JMenuItem("Convert to PDF");
//		pdfButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//converter();
//			}
//		});
		pdfButton.setForeground(Color.BLACK);
		pdfButton.setBackground(Color.LIGHT_GRAY);
		helpMenu.add(pdfButton);
		
	}

	
	
	
/********************************************************************************/
/************Function implementations********************************************/
//	
//	protected void converter() {
//	try {
//				
//			
//			
//		}catch(Exception e){
//			e.getStackTrace();
//		}
//	
//		
//	}
//
//	protected void print() {
//	try {
//				
//			
//			
//		}catch(Exception e){
//			e.getStackTrace();
//		}
//	
//		
//	}
//
//	 
//	protected void aboutMe() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	protected void viewTime() {
//	try {
//				
//			
//			
//		}catch(Exception e){
//			e.getStackTrace();
//		}
//	
//		
//	}
//
//
//	
//	
//
//	protected void save() {
//	try {
//				
//			
//			
//		}catch(Exception e){
//			e.getStackTrace();
//		}
//	
//		
//	}
//
//	protected void openFile() {
//		try {
//				
//			
//			
//		}catch(Exception e){
//			e.getStackTrace();
//		}
//	
//	}
//
//	protected void newFile() {
//	try {
//				
//			
//			
//		}catch(Exception e){
//			e.getStackTrace();
//		}
//	
//		
//	}
//
//	

}
