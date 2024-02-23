package general;

/*
 * Project: Investopia
 * Programmer(s): Stefan Lorenc Ekin Yelken
 * Date of Initiation: Jan 14, 2019 3:18 PM
 * Date of Completion: May 30, 2019 11:59 PM
 * Version 1.0
 * Class Name: IBR.java 
 */

/*
 * The IBR class is the second window for Investopia which
 * is more on the research end of the code. The idea is to have
 * text files which the instructors can edit which are displayed in
 * tables and can be compared using sorts easily. To do so IBR employs
 * JTables and JComboBoxes and JTabbedpane. The outcome is an easy to 
 * use and simple sort of stock screener.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
public class IBR implements ActionListener {

	// universal frame, stock function object and panel
	private JFrame frame = new JFrame();
	private StockFunction sf = new StockFunction();
	JPanel panel = new JPanel();
	
	// button
	JButton btnBack = new JButton("Back");
	
	// title
	private JTextField textField;
	
	// tables on each tabbed pane
	private JTable tableF = new JTable(); // finance table
	private JScrollPane scrollPaneF; // scroll pane
	String[] cnF; // designated coloumn names for each industry
	Object[][] dF; // data designated for each industry
	private JTable tableT = new JTable(); // tech table
	private JScrollPane scrollPaneT;
	String[] cnT;
	Object[][] dT;
	private JTable tableA = new JTable(); // aerospace table
	private JScrollPane scrollPaneA;
	String[] cnA;
	Object[][] dA;
	private JTable tableH = new JTable(); // healthcare table
	private JScrollPane scrollPaneH;
	String[] cnH;
	Object[][] dH;
	
	// tabbed
	JTabbedPane tabbedPane = new JTabbedPane();
	// comboBox
	String[] crit = { "Ticker", "Price", "Current Ratio", "Debt vs Equity", "Volume",  "Market Cap", "Change", "P/E Ratio", "P/B Ratio", "Profit" };
	private JComboBox comboBox = new JComboBox(crit);
	
	/**
	 * Create the application.
	 */
	public IBR() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// frame formalities
		frame.setBounds(100, 100, 700, 700);
		frame.getContentPane().setLayout(null);
		panel.setBounds(6, 6, 688, 666);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		/*
		 * This tabbed pane helps organized by industry in this search function. There are 4 tables with information on diffrent tabs.
		 * The Sort and back button are on the central panel and do not change with the tables, they are universal.
		 */
		tabbedPane.setBounds(21, 45, 646, 472);
		panel.add(tabbedPane);
		
	// the 4 tables
		
		// finance table creation
		cnF = sf.gettitle();
		dF = sf.tableconfig(sf.getFINANCE());
		scrollPaneF = new JScrollPane(tableF);
		panel.add(scrollPaneF);
		tableF = new JTable(dF, cnF);
		panel.add(tableF);
		tabbedPane.addTab("Financial Stocks", null, tableF, null);
		
		// technology table creation
		cnT = sf.gettitle();
		dT = sf.tableconfig(sf.getTECH());
		scrollPaneT = new JScrollPane(tableT);
		panel.add(scrollPaneT);
		tableT = new JTable(dT, cnT);
		panel.add(tableT);
		tabbedPane.addTab("Technology Stocks", null, tableT, null);
		
		// aerospace table creation
		cnA = sf.gettitle();
		dA = sf.tableconfig(sf.getAERO());
		scrollPaneA = new JScrollPane(tableA);
		panel.add(scrollPaneA);
		tableA = new JTable(dA, cnA);
		panel.add(tableA);
		tabbedPane.addTab("Aerospace Stocks", null, tableA, null);
		
		// healthcare table creation
		cnH = sf.gettitle();
		dH = sf.tableconfig(sf.getHEALTH());
		scrollPaneH = new JScrollPane(tableH);
		panel.add(scrollPaneH);
		tableH = new JTable(dH, cnH);
		panel.add(tableH);
		tabbedPane.addTab("Healthcare Stocks", null, tableH, null);
		
		// comboBox for sorting
		comboBox.setBounds(534, 569, 107, 27);
		comboBox.setSelectedIndex(9);
		comboBox.addActionListener(this);
		panel.add(comboBox);
		
		// back button
		btnBack.setBounds(41, 568, 117, 29);
		btnBack.addActionListener(this);
		panel.add(btnBack);
		
		// sort label
		JLabel label = new JLabel("Sort:");
		label.setBounds(485, 570, 74, 23);
		panel.add(label);
		
		// unmoving titel at top
		textField = new JTextField("Ticker | Price | Current Ratio | Debt/Equity | Volume | Mkt Cap | Change | PE Ratio | PB Ratio | Profit");
		textField.setEditable(false);
		textField.setBounds(22, 6, 644, 26);
		panel.add(textField);
		textField.setColumns(10);
		frame.setVisible(true);
	}

	@Override
	// this method is used to determine the action, since there is only one button, the only other function is sort.
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource(); 
		/*
		 * This if statement is in charge of executring certain code based on what the source of the actionPerformed is which is determined by the line above
		 */
		
		if (src == btnBack) { // disposes frame and goes back to main menu
			
			frame.dispose();
			menu m = new menu();
			
		} else if (src == comboBox) { // this means that some sort of sorting and re-display is going to occur
			
		JComboBox cb = (JComboBox) e.getSource(); 
		String criteria = (String) cb.getSelectedItem(); // from the comboBox sources gets exactly which was pressed and returns that
		/*
		 * Sorts each ArrayList based on the criteria.
		 */
		sf.sortCriteria(criteria, sf.getAERO());
		sf.sortCriteria(criteria, sf.getTECH());
		sf.sortCriteria(criteria, sf.getHEALTH());
		sf.sortCriteria(criteria, sf.getFINANCE());
		/*
		 * This part of the code is the re-displaying of the code. First the old table is removed and then a new one replaces each with the new sorted data.
		 * The tables are implemented exactly how they were in initialize();
		 */
		panel.remove(tableF);
		panel.remove(tableA);
		panel.remove(tableT);
		panel.remove(tableH);
		
		cnF = sf.gettitle();
		dF = sf.tableconfig(sf.getFINANCE());
		scrollPaneF = new JScrollPane(tableF);
		panel.add(scrollPaneF);
		tableF = new JTable(dF, cnF);
		panel.add(tableF);
		tabbedPane.addTab("Financial Stocks", null, tableF, null);
		
		cnA = sf.gettitle();
		dA = sf.tableconfig(sf.getAERO());
		scrollPaneA = new JScrollPane(tableA);
		panel.add(scrollPaneA);
		tableA = new JTable(dA, cnA);
		panel.add(tableA);
		tabbedPane.addTab("Aerospace Stocks", null, tableA, null);
		
		cnT = sf.gettitle();
		dT = sf.tableconfig(sf.getTECH());
		scrollPaneT = new JScrollPane(tableT);
		panel.add(scrollPaneT);
		tableT = new JTable(dT, cnT);
		panel.add(tableT);
		tabbedPane.addTab("Technology Stocks", null, tableT, null);
		
		cnH = sf.gettitle();
		dH = sf.tableconfig(sf.getHEALTH());
		scrollPaneH = new JScrollPane(tableH);
		panel.add(scrollPaneH);
		tableH = new JTable(dH, cnH);
		panel.add(tableH);
		tabbedPane.addTab("Healthcare Stocks", null, tableH, null);
		}
	}
}
