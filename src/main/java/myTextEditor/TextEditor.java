package myTextEditor;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class TextEditor extends JFrame {
	
	private static final long serialVersionUID = 7842805864855355915L;

	// fields
	static String title = "Untitled";
	static TextEditor WINDOW;
	static JFrame frame;
	static JTextArea textArea;
	JScrollPane scroll;
	JMenuBar menuBar;
	JMenu fileMenu, searchMenu, viewMenu, manageMenu, helpMenu;
	JMenuItem printButton, openButton, newButton, saveButton, aboutButton, selectAllButton, timeButton,
						pdfButton,searchButton, exitButton, cutButton, copyButton ,pasteButton, saveAsButton;
	FileMenu fm ;
	ViewMenu vm;
	
	

	// launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				    WINDOW = new TextEditor();
					TextEditor.frame.setVisible(true);
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
		frame = new JFrame("My Text Editor");
		frame.setTitle(title);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		//Set up textArea with scrollbar.scrollbar appears when needed.
		textArea = new JTextArea();
		frame.getContentPane().add(textArea, BorderLayout.NORTH);	
		scroll = new JScrollPane(textArea);
		frame.getContentPane().add(scroll, BorderLayout.CENTER);
		
		
		menuBar = new JMenuBar();
		menuBar.setForeground(Color.LIGHT_GRAY);
		menuBar.setBackground(Color.DARK_GRAY);
		frame.setJMenuBar(menuBar);
		
		fileMenu = new JMenu("File");
		fileMenu.setForeground(Color.PINK);
		fileMenu.setBackground(Color.GRAY);
		menuBar.add(fileMenu);
		// Create FileMenu object
		fm = new FileMenu();
		
		// Create new file
		newButton = new JMenuItem("New");
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fm.newFile();
			}
		});
		
		newButton.setBackground(Color.LIGHT_GRAY);
		fileMenu.add(newButton);
		
		//Open file
		openButton = new JMenuItem("Open");
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fm.openFile();
			}
		});
		openButton.setBackground(Color.LIGHT_GRAY);
		fileMenu.add(openButton);
		
		// Save file 
		saveButton = new JMenuItem("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fm.save();
			}
		});
		saveButton.setBackground(Color.LIGHT_GRAY);
		fileMenu.add(saveButton);
		
		saveAsButton = new JMenuItem("Save As");
		saveAsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fm.saveAs();
			}
		});
		saveAsButton.setBackground(Color.LIGHT_GRAY);
		fileMenu.add(saveAsButton);
		
		
		//Exit the window
		exitButton = new JMenuItem("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fm.close();
			}
		});
		exitButton.setBackground(Color.LIGHT_GRAY);
		fileMenu.add(exitButton);
		
		
		
		searchMenu = new JMenu("Search");
		searchMenu.setForeground(Color.PINK);
		searchMenu.setBackground(Color.GRAY);
		menuBar.add(searchMenu);
		
		//Search for words.
		searchButton = new JMenuItem("search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchKey = JOptionPane.showInputDialog(frame,"Search string: ");
                Search.highlight(textArea, searchKey);
                
			}
		});
		searchButton.setBackground(Color.LIGHT_GRAY);
		searchMenu.add(searchButton);
		
		
		viewMenu = new JMenu("View");
		viewMenu.setForeground(Color.PINK);
		viewMenu.setBackground(Color.GRAY);
		menuBar.add(viewMenu);
		//Create ViewMenu object
		vm = new ViewMenu();
		
		
		//display time and date
		timeButton = new JMenuItem("Time and Date");
		timeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				vm.viewTime();
			}
		});
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
		manageMenu.setForeground(Color.PINK);
		manageMenu.setBackground(Color.GRAY);
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
		helpMenu.setForeground(Color.PINK);
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
