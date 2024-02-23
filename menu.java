package general;

/*
 * Project: Investopia
 * Author(s): Stefan Lorenc Ekin Yelken
 * Date of Initiation: Jan 14, 2019 3:18 PM
 * Date of Completion: May 30, 2019 11:59 PM
 * Version 1.0
 * Class Name: menu.java 
 */

/*
 * The menu class is the one which is the central sort of class
 * from which all of the other functionality is run from. This is
 * the main menu. In addition to the buttons, there is a text area 
 * which talks about S&E and how to use the program for user reference.
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


@SuppressWarnings("unused")
public class menu implements ActionListener {

	private JFrame frame; // universal frame
	private JTextField txtInvestopiaMain; // title
	private JTextArea display; // the text area with about and instructions
	private JButton btnIBR; // the button to access Industry Based Research
	private JButton btnSSS; // the button to access Single Stock Research
	private JButton btnWB; // the button to access MyWorkBook

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu window = new menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public menu() {
		initialize();
	}

	/**
	 * Creates logo image in the window.
	 */
	
	public ImageIcon createImage(String file) {
		return new ImageIcon(java.awt.Toolkit.getDefaultToolkit().getClass().getResource(file));
	}
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		// frame formalities
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		// panel formalities
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 688, 666);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		// title for the main menu pane
		txtInvestopiaMain = new JTextField();
		txtInvestopiaMain.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		txtInvestopiaMain.setBounds(23, 27, 380, 53);
		txtInvestopiaMain.setText("Investopia - Menu");
		txtInvestopiaMain.setEditable(false);
		panel.add(txtInvestopiaMain);
		txtInvestopiaMain.setColumns(10);
		
		// code for the image icon for the logo on top right.
		ImageIcon queen = new ImageIcon("/resources/Logos1.png");
		queen.setImage(createImage("/resources/Logos1.png").getImage());
		final JLabel lblNewLabel = new JLabel(queen);
		lblNewLabel.setBounds(415, 6, 224, 193);
		panel.add(lblNewLabel);
		
		// the button to access MyWorkBook
		btnWB = new JButton("My Workbook");
		btnWB.setBounds(149, 126, 117, 29);
		btnWB.addActionListener(this);
		panel.add(btnWB);
		
		// tabbed pane with 2 tables, About/Instructions and Stock Research.
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(26, 197, 631, 451);
		panel.add(tabbedPane);
		
		// textArea with al lof the about/instructions text in a scroll pane.
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("About/Instructions", null, panel_1, null);
		display = new JTextArea("Developers: Ekin Yelken & Stefan Lorenc | 2019\n\n\n"
				+ "S&E Development Co. is a small development group headed by two programmers based in Toronto Canada specializes in the application and visual development. Stefan Lorenc is in  charge of core and specialized research, development/implementation of SE's applications. Ekin Yelken is in charge of graphic user interface development and visual aesthetics. Both work together on the logical fabrication of the applications S&E develops. The objective of this program is to create a simple platform which amateur economists can  learn how to navigate the market or mitigate losses. Specifically, to build their knowledge about stocks, stock market interpretation, and analysis."
				+ "\n\n\nInstructions:\nTo access your workbook where all of your saved stocks are located, click on the 'MyWorkbook' button located in the top right. To Research Stocks or check highlighted stocks set by your teacher, click on 'Stock Research' and you will given two options: Single Stock Research (get info of a single stock), and Industry Based Research (search stocks by industry). Click one of the buttons to proceed."
				+ "\n\nMyWorkbook - The program opens a tab where the user can manually mutate their personal workbook containing stocks. There are 4 buttons on the page besides the 'back' button: Add, Remove, Save, and Load.\n\nAdd: The user inputs a Ticker into the textfield to the right of the button and upon clicking the 'add' button if the Ticker exists, the program adds that stock and its information to the user's personal list.\nRemove: The user inputs a Ticker into the textfield to the right of the button and upon clicking the 'remove' button, the program removes that stock and its information from the user's personal list.\nSave: Upon clicking the 'Save' button, Microsoft Excel is automatically opened and the user's workbook and information is outputed onto the spreadsheet where it can be manipulated\nLoad: The user has the option of loading an already existing Excell file with stock information in it, into their program's workbook."
				+ "\n\nIndusry Based Research - The program will fetch stocks using predetermined Tickers based on one of four specific industries (this may take up to 20 seconds!). The user can switch through each industry and see the different stocks and their information. The User can sort the information by click the large drop down menu on the bottom half of the screen and sort by clicking on of the criteria. Once you are done, Click the red X button to close the tab and continue using."
				+ "\n\nSingle Stock Research - The program will prompt the user to enter the Ticker of the stock they wish to research. Upon entering a valid Ticker, the program will fetch, format, and present the user with all of the relevant information for that stock in a different tab.", 25, 48);
		display.setLineWrap(true);
		display.setWrapStyleWord(true);
		display.setEditable(false);
		JScrollPane scroll = new JScrollPane(display);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_1.add(scroll);
		
		// Stock Research tab with the IBR and SSS buttons in it
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Stock Research", null, panel_2, null);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
	// Buttons
		// Industry Based Research
		btnIBR = new JButton("Industry Based Research");
		panel_2.add(btnIBR);
		btnIBR.addActionListener(this);
		// Single Stock Research
		btnSSS = new JButton("Single Stock Research");
		panel_2.add(btnSSS);
		btnSSS.addActionListener(this);
	}
	/*
	 * This method is attached to every component and is used to first identify the button that was pressed then 
	 * based on that with the large if statement runs code based on the actionPerformed
	 */
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		/*
		 * This if statement is in charge of executring certain code based on what the source of the actionPerformed is which is determined by the line above
		 */
		if (src == btnIBR) { // starts the Industry Based Research function

			try {
				IBR window = new IBR();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (src == btnWB) { // runs the personalized workbook
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						WorkBook window = new WorkBook();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		} else if (src == btnSSS) { // runs the single stock research function
			
			String tickerInfo = "";
			String Ticker = getTicker(); // gets ticker
			try {
				StockFunction s = new StockFunction(Ticker); // gets quote and displays it with formating
				String SingleStock = ("Ticker | Price | Current Ratio | Debt/Equity | Volume | Mkt Cap | Change | PE Ratio | PB Ratio | Profit\n" + s.toString());
		        JOptionPane.showMessageDialog( null, SingleStock);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	/*
	 *  Individual method to get a ticker, it is designed to stop input which would be immediately incorrect (ie. numbers) using unicode to save time
	 */
	String getTicker() {

		boolean goodTicker = false;
		String Ticker = "";
		while (goodTicker != true) {

			try {
				Ticker = JOptionPane.showInputDialog("Enter the Ticker you wish to search: "); // sets this Ticker string and checks that the returns good input
				// loops through every character until bad input is found the breaks 
				for (int i = 0; i < Ticker.length(); i++) {
					if (Ticker.toUpperCase().charAt(i) <= 65 && Ticker.toUpperCase().charAt(i) >= 90
							|| Ticker.toUpperCase().charAt(i) == '-') { // 65-90 is A-Z in unicode
						goodTicker = false;
						break;
					}
					goodTicker = true;
				}

			} catch (Exception e) {
				goodTicker = false;
				break;
			}

		}

		return Ticker;
	}
	
}
