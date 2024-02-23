package general;

/*
 * Project: Investopia
 * Authors(s): Stefan Lorenc Ekin Yelken
 * Date of Initiation: Jan 14, 2019 3:18 PM
 * Date of Completion: May 30, 2019 11:59 PM
 * Version 1.0
 * Class Name: WorkBook.java 
 **/

/**
 * The Workbook function is another central feature of the code. 
 * As the third window for the program, the whole idea is all about
 * personalizing the Investopia experience for users. This class contains
 * the GUI and visual interation for the WorkBook functionality. The main
 * features of this class are in addition to the tables and sorting similar
 * to IBR, saving work or loading work from excel where a visual graphical 
 * representation can be produced.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextArea;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class WorkBook implements ActionListener {
	
	// universal frame and panel
	private JFrame frame = new JFrame(); 
	private JPanel panel;
	
// components
	// textfields
	private JTextField textField_A = new JTextField();
	private JTextField textField_R = new JTextField();
	// buttons
	private JButton btnBack = new JButton("Back"); 
	private JButton btnAdd = new JButton("Add");
	private JButton btnRemove = new JButton("Remove");
	private JButton btnSave = new JButton("Save");
	private JButton btnLoad = new JButton("Load");
	// global stock function object
	private StockFunction sf = new StockFunction(); 
	// combobox 
	String[] crit = { "Ticker", "Price", "Current Ratio", "Debt vs Equity", "Volume",  "Market Cap", "Change", "P/E Ratio", "P/B Ratio", "Profit" }; // criteria to be sorted by
	private JComboBox comboBox = new JComboBox(crit);
	private String criteria;
	// table
	String[] columnNames; // column names of table
	Object[][] data; // data to populate table
	private JTable table; // table
	
	private JTextField txtMyWorkbook; // unmoving title
	
	private JScrollPane scrollPane; // scrol pane

	/**
	 * Create the application.
	 */
	public WorkBook() throws IOException {
		initialize();

	}

	/**
	 * Image icon for the logo.
	 */
	public ImageIcon createImage(String file) {
		return new ImageIcon(java.awt.Toolkit.getDefaultToolkit().getClass().getResource(file));
	}
	
	/**
	 * The actual method which sets and adds the components.
	 */
	private void initialize() throws IOException {
		
		// settings for the frame and panel
		frame.setBounds(100, 100, 720, 720);
		frame.getContentPane().setLayout(null); // absolute layout
		frame.setLocationRelativeTo(null); // spawn window in middle of screen
		panel = new JPanel();
		panel.setBounds(6, 6, 688, 666);
		frame.getContentPane().add(panel); // add panel
		panel.setLayout(null); // absolute layout
		btnAdd.setToolTipText("Add a stock to your Workbook");

	// Buttons
		
		// Add
		btnAdd.setBounds(23, 132, 117, 29);
		btnAdd.addActionListener(this); // listener to perform functionality if it is pressed
		panel.add(btnAdd);

		// Remove
		btnRemove.setToolTipText("Remove a stock from your Workbook");
		btnRemove.setBounds(23, 173, 117, 29);
		btnRemove.addActionListener(this);
		panel.add(btnRemove);

		// Load
		btnLoad.setToolTipText("Load existing Workbook");
		btnLoad.setBounds(344, 621, 117, 29);
		btnLoad.addActionListener(this);
		panel.add(btnLoad);

		// Save
		btnSave.setToolTipText("Save your workbook and open in Excel");
		btnSave.setBounds(215, 621, 117, 29);
		btnSave.addActionListener(this);
		panel.add(btnSave);

		// Back
		btnBack.setToolTipText("Back to main menu");
		btnBack.setBounds(16, 621, 117, 29);
		btnBack.addActionListener(this);
		panel.add(btnBack);

	// Text Fields
		
		// Add text field
		textField_A.setBounds(151, 132, 248, 26);
		panel.add(textField_A);
		textField_A.setColumns(10);
		
		// remove text field
		textField_R.setColumns(10);
		textField_R.setBounds(152, 173, 247, 26);
		panel.add(textField_R);

		// logo image icon
		ImageIcon logo = new ImageIcon("/resources/Logos1.png");
		logo.setImage(createImage("/resources/Logos1.png").getImage());
		final JLabel lblNewLabel = new JLabel(logo);
		lblNewLabel.setBounds(417, 6, 248, 209);
		panel.add(lblNewLabel);

		// main workbook table
		columnNames = sf.gettitle(); // sets title
		data = sf.tableconfig(sf.getWorkBook()); // populatez table
		scrollPane = new JScrollPane(table); // table to scroll pane
		panel.add(scrollPane); //scrollpane to panel
		table = new JTable(data, columnNames); // initializing and adding the table
		table.setBounds(16, 253, 654, 353);
		panel.add(table);
		table.setEnabled(false); // so it cant be edited
		
		// Sorting comboBox
		comboBox.setBounds(561, 622, 109, 27);
		comboBox.addActionListener(this);
		panel.add(comboBox);

		// JLabel
		JLabel lblSort = new JLabel("Sort:");
		lblSort.setBounds(524, 625, 42, 16);
		panel.add(lblSort);

		// Title
		txtMyWorkbook = new JTextField();
		txtMyWorkbook.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		txtMyWorkbook.setText("My Workbook");
		txtMyWorkbook.setBounds(23, 20, 376, 76);
		txtMyWorkbook.setEditable(false);
		panel.add(txtMyWorkbook);
		txtMyWorkbook.setColumns(10);
		
		// unchanging text area as requested by client
		JTextArea txtrTickerPrice = new JTextArea();
		txtrTickerPrice.setText("    Ticker        Price      Current Ratio   Debt/Equity   Volume   Market Cap   Change     P/E Ratio    P/B Ratio        Profit");
		txtrTickerPrice.setBounds(16, 227, 654, 16);
		txtrTickerPrice.setEditable(false);
		panel.add(txtrTickerPrice);
		frame.setVisible(true);
	}
	
	// This is the actionPerformed method, when an ActionListener is activted, this method 
	public void actionPerformed(ActionEvent e) {
		
		Object src = e.getSource(); 
		if (src == btnBack) { // back button fully functional
			// disposes frame and re runs menu, work no saved unless save is clicked
			frame.dispose();
			menu m = new menu();
			
		} else if (src == btnAdd) {
			// add function, gets a ticker, keeps asking till good input is put in
			boolean goodTicker = false;
			String Ticker = "";
			while (goodTicker != true) {
				Ticker = textField_A.getText();
				for (int i = 0; i < Ticker.length(); i++) {
					if (Ticker.toUpperCase().charAt(i) <= 65 && Ticker.toUpperCase().charAt(i) >= 90
							|| Ticker.toUpperCase().charAt(i) == '-') {
						goodTicker = false;
						break;
					}
					goodTicker = true;
				}
				try {
					// method to add ticker
					sf.toWorkBook(Ticker);
					@SuppressWarnings("unlikely-arg-type")
					boolean x = sf.getWorkBook().contains(Ticker);
					if(x  = true) {
						
					}
					else {
						sf.removeWorkBook(Ticker);
					}
					// re populating table and redisplaying it
					columnNames = sf.gettitle();
					data = sf.tableconfig(sf.getWorkBook());
					scrollPane = new JScrollPane(table);
					panel.add(scrollPane);
					table = new JTable(data, columnNames);
					table.setBounds(16, 253, 654, 353);
					panel.add(table);
					table.setEnabled(false);
					} catch (IOException e1) {
						
					e1.printStackTrace();
				}
			}

		} else if (src == btnRemove) { // remove button fully functional
			// once a ticker is added it is sent to removeWorkBook where it scans the list for the ticker and removes it if it exists.
			String Ticker = "";
			Ticker = textField_R.getText();
			sf.removeWorkBook(Ticker);
			// re populating table and redisplaying it
			columnNames = sf.gettitle();
			data = sf.tableconfig(sf.getWorkBook());
			scrollPane = new JScrollPane(table);
			panel.add(scrollPane);
			table = new JTable(data, columnNames);
			table.setBounds(16, 253, 654, 353);
			panel.add(table);
			table.setEnabled(false);

		} else if (src == btnLoad) { // load button is functional
			String name = JOptionPane.showInputDialog("Enter the name of the file: ");
			try {
				sf.getWorkBook().clear();
				sf.loadWorkBook(name +".csv");
				columnNames = sf.gettitle();
				data = sf.tableconfig(sf.getWorkBook());
				scrollPane = new JScrollPane(table);
				panel.add(scrollPane);
				table = new JTable(data, columnNames);
				table.setBounds(16, 253, 654, 353);
				panel.add(table);
				table.setEnabled(false);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} else if (src == btnSave) { // save button fully functional
			try {
				// saves to excel file
				sf.toExcelfile(sf.getWorkBook());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (src == comboBox) { // Sort fully functional
			// gets teh criteria if the combobox is the component interacted with. Gets the String and sends information to sort method
			String criteria = (String) comboBox.getSelectedItem();
			// sort method sorts workbook
			sf.sortCriteria(criteria, sf.getWorkBook());
			// re populating table and redisplaying it
			columnNames = sf.gettitle();
			data = sf.tableconfig(sf.getWorkBook());
			scrollPane = new JScrollPane(table);
			panel.add(scrollPane);
			table = new JTable(data, columnNames);
			table.setBounds(16, 253, 654, 353);
			panel.add(table);
			table.setEnabled(false);
		}
	}
	
}
