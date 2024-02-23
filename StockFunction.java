package general;

/*
 * Project: Investopia
 * Author(s): Stefan Lorenc & Ekin Yelken
 * Date of Initiation: Jan 14, 2019 3:18 PM
 * Date of Completion: May 30, 2019 11:59 PM
 * Version 1.0
 * Program Name: StockFunction.java 
 */

/*
 * The StockFunction class contains
 * the basic functionality of Investopia i.e the stock 
 * accessor method as well as a number of other essential methods for function.
 * The class below houses the industry and 
 * individual stock research as well as the mutator methods 
 * for such research including sorting by multiple metrics.
 * the WorkBook back end is also housed here, namely the mutator methods for its arrays
 * and the to excel function for visual display
 * 
 */

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;



public class StockFunction implements Comparable<StockFunction> {
	
	boolean tracing = false;
	/************************
	 * Below are the StockFunction class comparators for use in sorting.
	 * The Comparators are identical in structure and only reference different arrays to make 
	 * different comparisons. As such, the PRICE comparator will serve as a documentation example of the 10 
	 * comparators present in the class definition
	 ************************/
	static final Comparator<StockFunction> TICKER = new Comparator<StockFunction>() {
		public int compare(StockFunction e1, StockFunction e2) {

			return e1.getTicker().compareTo(e2.getTicker());
		}
	};
	
	static final Comparator<StockFunction> PRICE = new Comparator<StockFunction>() {
		public int compare(StockFunction e1, StockFunction e2) {// internal compare method used for object metric comparison 
			double init = Double.parseDouble(e1.getprice());
			double comp = Double.parseDouble(e2.getprice());// Object parameters to be compared are defined internally
			// this is the if statement where the comparrison occurs.
			if (init > comp) {//returns 1 if init > comp
				return 1;
			} else if (init < comp) {// returns -1 if init is < comp
				return -1;
			} else if (init == comp) {// return 0 if the 2 metrics are equivalent
				return 0;
			} else {
				//System.out.println("failure in comp function");
				return -36;
			}
		}
	};
	static final Comparator<StockFunction> CURRENT = new Comparator<StockFunction>() {

		public int compare(StockFunction e1, StockFunction e2) {

			double init = Double.parseDouble(e1.getCUR());
			double comp = Double.parseDouble(e2.getCUR());
			if (init > comp) {
				return 1;
			} else if (init < comp) {
				return -1;
			} else if (init == comp) {
				return 0;
			} else {
				//System.out.println("failure in comp function");
				return -36;
			}
		}
	};
	static final Comparator<StockFunction> DEBTVSEQUITY = new Comparator<StockFunction>() {

		public int compare(StockFunction e1, StockFunction e2) {

			double init = Double.parseDouble(e1.getDVE());
			double comp = Double.parseDouble(e2.getDVE());
			if (init > comp) {
				return 1;
			} else if (init < comp) {
				return -1;
			} else if (init == comp) {
				return 0;
			} else {
				//System.out.println("failure in comp function");
				return -36;
			}
		}
	};
	static final Comparator<StockFunction> VOLUME = new Comparator<StockFunction>() {
		public int compare(StockFunction e1, StockFunction e2) {

			double init = Double.parseDouble(e1.getvol());
			double comp = Double.parseDouble(e2.getvol());
			if (init > comp) {
				return 1;
			} else if (init < comp) {
				return -1;
			} else if (init == comp) {
				return 0;
			} else {
				//System.out.println("failure in comp function");
				return -36;
			}
		}
	};
	static final Comparator<StockFunction> MARCAP = new Comparator<StockFunction>() {
		public int compare(StockFunction e1, StockFunction e2) {

			double init = Double.parseDouble(e1.getmrktcap());
			double comp = Double.parseDouble(e2.getmrktcap());
			if (init > comp) {
				return 1;
			} else if (init < comp) {
				return -1;
			} else if (init == comp) {
				return 0;
			} else {
				//System.out.println("failure in comp function");
				return -36;
			}
		}
	};
	static final Comparator<StockFunction> CHANGE = new Comparator<StockFunction>() {
		public int compare(StockFunction e1, StockFunction e2) {

			double init = Double.parseDouble(e1.getchng());
			double comp = Double.parseDouble(e2.getchng());
			if (init > comp) {
				return 1;
			} else if (init < comp) {
				return -1;
			} else if (init == comp) {
				return 0;
			} else {
				//System.out.println("failure in comp function");
				return -36;
			}
		}
	};
	static final Comparator<StockFunction> PRICEEQUITY = new Comparator<StockFunction>() {
		public int compare(StockFunction e1, StockFunction e2) {

			double init = Double.parseDouble(e1.getPE());
			double comp = Double.parseDouble(e2.getPE());
			if (init > comp) {
				return 1;
			} else if (init < comp) {
				return -1;
			} else if (init == comp) {
				return 0;
			} else {
				//System.out.println("failure in comp function");
				return -36;
			}
		}
	};
	static final Comparator<StockFunction> PROFIT = new Comparator<StockFunction>() {
		public int compare(StockFunction e1, StockFunction e2) {

			double init = Double.parseDouble(e1.getprft());
			double comp = Double.parseDouble(e2.getprft());
			if (init > comp) {
				return 1;
			} else if (init < comp) {
				return -1;
			} else if (init == comp) {
				return 0;
			} else {
				//System.out.println("failure in comp function");
				return -36;
			}
		}
	};
	static final Comparator<StockFunction> PRICEBOOK = new Comparator<StockFunction>() {
		public int compare(StockFunction e1, StockFunction e2) {

			double init = Double.parseDouble(e1.getPB());
			double comp = Double.parseDouble(e2.getPB());
			if (init > comp) {
				return 1;
			} else if (init < comp) {
				return -1;
			} else if (init == comp) {
				return 0;
			} else {
				//System.out.println("failure in comp function");
				return -36;
			}
		}
	};
	
	private ArrayList<StockFunction> Finance;// Collection of Finance Stocks
	private ArrayList<StockFunction> Tech;// Collection of Tech Stocks Stocks
	private ArrayList<StockFunction> Aerospace;// Collection of Aerospace Stocks
	private ArrayList<StockFunction> Healthcare;// Collection of Healthcare Stocks
	private ArrayList<StockFunction> WorkBook;//Personal collection of Stocks
	
	String[] title = {"Ticker", "Price","CUR","DVE","Volume","Market Cap","Change","P/E","P/B","Profit"};
	String[] finance;//Arrays used in the import of Finance .txt file information
	String[] tech;//Arrays used in the import of Tech.txt file information
	String[] health;//Arrays used in the import of Healthcare.txt file information
	String[] aero;//Arrays used in the import of Aerospace.txt file information
	String[] list;//Temporary list
	
	private String Ticker;//Strings used for display
	private String Price;//Strings used for display
	private String CUR;//Strings used for display
	private String PER;//Strings used for display
	private String PBR;//Strings used for display
	private String DVE;//Strings used for display
	private String Volume;//Strings used for display
	private String marketCap;//Strings used for display
	private String Change;//Strings used for display
	private String Profit;//Strings used for display


	StockFunction() throws IOException {
		Finance = new ArrayList<StockFunction>();// Initializations of the Mutable Arraylists for both instructor and student use
		Tech = new ArrayList<StockFunction>();
		Aerospace = new ArrayList<StockFunction>();
		Healthcare = new ArrayList<StockFunction>();
		WorkBook = new ArrayList<StockFunction>();
		finance = fileImport("Finance.txt");//
		tech = fileImport("Tech.txt");
		aero = fileImport("Aerospace.txt");
		health = fileImport("Healthcare.txt");
		arrayconsolidation(finance, tech, health, aero);

	}

	StockFunction(String Ticker) throws IOException {
		boolean test = false;
		// this will loop until it works completely in case there is an IndexOutOfBounds
		while (!test) {
			try {
				Stock(Ticker);
				test = true;
			} catch (StringIndexOutOfBoundsException a) {
				return;
			}
		}
	}

	StockFunction(String Tick, String Pri, String CU, String DV, String Volu, String Mrk, String Chn, String PE,
		String prf, String PB) {
		Ticker = Tick;
		Price = Pri;
		CUR = CU;
		DVE = DV;
		Volume = Volu;
		marketCap = Mrk;
		Change = Chn;
		PER = PE;
		Profit = prf;
		PBR = PB;
	}

	/*
	 * All mutator methods.
	 */
	
	public String getTicker() {
		return Ticker;
	}
	public String getprice() {
		return Price;
	}
	public String getCUR() {
		return CUR;
	}
	public String getDVE() {
		return DVE;
	}
	public String getvol() {
		return Volume;
	}
	public String getmrktcap() {
		return marketCap;
	}
	public String getchng() {
		return Change;
	}
	public String getPE() {
		return PER;
	}
	public String getprft() {
		return Profit;
	}
	public String getPB() {
		return PBR;
	}
	public String[] gettitle() {
		return title;
	}
	/*
	 * All of the methods which turn ArrayLists into nice strings.
	 */
	public String getFinance() {
		return Finance.toString().replace(",", "").replace("[", "").replace("]", "");
	}
	public String getTech() {
		return Tech.toString().replace(",", "").replace("[", "").replace("]", "");
	}
	public String getHealthcare() {
		return Healthcare.toString().replace(",", "").replace("[", "").replace("]", "");
	}
	public String getAerospace() {
		return Aerospace.toString().replace(",", "").replace("[", "").replace("]", "");
	}
	public String getWB() {
		return WorkBook.toString().replace(",", "").replace("[", "").replace("]", "");
	}
	/*
	 * More mutator methods which return the Arraylists.
	 */
	public ArrayList<StockFunction> getWorkBook() {
		return WorkBook;
	}
	public ArrayList<StockFunction> getFINANCE() {
		return Finance;
	}
	public ArrayList<StockFunction> getHEALTH() {
		return Healthcare;
	}
	public ArrayList<StockFunction> getTECH() {
		return Tech;
	}
	public ArrayList<StockFunction> getAERO() {
		return Aerospace;
	}
	/*
	 * Comparator implementation methods which are called later.
	 */
	public Comparator<StockFunction> getTICKER() {
		return TICKER;
	}
	public Comparator<StockFunction> getPRICE() {
		return PRICE;
	}
	public Comparator<StockFunction> getCURRENT() {
		return CURRENT;
	}
	public Comparator<StockFunction> getDEBTVSEQUITY() {
		return DEBTVSEQUITY;
	}
	public Comparator<StockFunction> getVOLUME() {
		return VOLUME;
	}
	public Comparator<StockFunction> getMARCAP() {
		return MARCAP;
	}
	public Comparator<StockFunction> getCHANGE() {
		return CHANGE;
	}
	public Comparator<StockFunction> getPRICEEQUITY() {
		return PRICEEQUITY;
	}
	public Comparator<StockFunction> getPROFIT() {
		return PROFIT;
	}
	public Comparator<StockFunction> getPRICEBOOK() {
		return PRICEBOOK;
	}

	/*
	 * Stock Function: this method is the central part of our entire program. The idea is that based on a ticker the method access yahoo finance and 
	 * gets a quote for a stock based on a ticker, assigning values to a universal set of variables
	 */
	
	void Stock(String Tick) throws IOException {
		URL url = new URL("https://ca.finance.yahoo.com/quote/" + Tick + "?p=" + Tick + "&.tsrc=fin-srch");
		URLConnection urlConn = url.openConnection();
		@SuppressWarnings("unused")
		boolean trace = false;
		// setting up input stream to read information
		InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
		BufferedReader buff = new BufferedReader(inStream);
		String line = buff.readLine();
		String collection = null;
		int indexi = 0;
		int indexii = 0;
		int indexiii = 0;
		int indexiiii = 0;
		int indexiiiii = 0;
		int indexiiiiii = 0;
		int indexiregulator = 0;
		int indexiiregulator = 0;
		int indexiiiregulator = 0;
		int indexiiiiregulator = 0;
		int indexiiiiiregulator = 0;
		int indexiiiiiiregulator = 0;
		int indexv = 0;
		int indexvregulator = 0;
		int indexvv = 0;
		int indexvvregulator = 0;
		int indexvvv = 0;
		int indexvvvregulator = 0;

		while (line != null) { // this loop is where the actual scraping happens, it scans for the index of each of the following
			if (line.contains(Tick)) {
				indexi = line.indexOf("regularMarketPrice");
				indexii = line.indexOf("currentRatio");
				indexiii = line.indexOf("debtToEquity");
				indexiiii = line.indexOf("regularMarketVolume");
				indexiiiii = line.indexOf("marketCap");
				indexiiiiii = line.indexOf("regularMarketChange");
				indexv = line.indexOf("forwardPE");
				indexvv = line.indexOf("profitMargins");
				indexvvv = line.indexOf("priceToBook");
				indexiregulator = line.indexOf(".", indexi);
				indexiiregulator = line.indexOf(".", indexii);
				indexiiiregulator = line.indexOf(".", indexiii);
				indexiiiiregulator = line.indexOf(",", indexiiii);
				indexiiiiiregulator = line.indexOf(",", indexiiiii);
				indexiiiiiiregulator = line.indexOf(",", indexiiiiii);
				indexvregulator = line.indexOf(".", indexv);
				indexvvregulator = line.indexOf(".", indexvv);
				indexvvvregulator = line.indexOf(".", indexvvv);
				collection = line;
			}
			line = buff.readLine();
		}
		// after this, the program goes and cuts down the extracted info into the desired number and assigns it to a proto then sends it to be assigned
		String proto1 = collection.substring(indexiregulator - 8, indexiregulator + 8);
		String proto2 = collection.substring(indexiiregulator - 8, indexiiregulator + 8);
		String proto3 = collection.substring(indexiiiregulator - 8, indexiiiregulator + 8);
		String proto4 = collection.substring(indexiiiiregulator - 8, indexiiiiregulator + 8);
		String proto5 = collection.substring(indexiiiiiregulator - 12, indexiiiiiregulator + 8);
		String proto6 = collection.substring(indexiiiiiiregulator - 12, indexiiiiiiregulator + 8);
		String proto7 = collection.substring(indexvregulator - 8, indexvregulator + 8);
		String proto8 = collection.substring(indexvvregulator - 8, indexvvregulator + 8);
		String proto9 = collection.substring(indexvvvregulator - 10, indexvvvregulator + 10);
		
		/*
		 * Assigns the findings from the scrap to the universal variables.
		 */
		Ticker = Tick;
		Price = proto1.replaceAll("[^\\d.-]", "");
		CUR = proto2.replaceAll("[^\\d.-]", "");
		PER = proto7.replaceAll("[^\\d.-]", "");
		PBR = proto9.replaceAll("[^\\d.-]", "");
		DVE = proto3.replaceAll("[^\\d.-]", "");
		Volume = proto4.replaceAll("[^\\d.-]", "");
		marketCap = proto5.replaceAll("[^\\d.-]", "");
		Change = proto6.replaceAll("[^\\d.-]", "");
		Profit = proto8.replaceAll("[^\\d.-]", "");

	}
	/*
	 * This method is used to put a stock into the personalized workbook. 
	 */
	void toWorkBook(String Tick) throws IOException {
		StockFunction stock = new StockFunction(Tick); // gets quote with stock function
		if(stock.getTicker().equals(null)) { // stops if the ticker is null (doesn't exist or is a bad ticker)
			return;
		}
		/*
		 * This for loop will go through he Workbook and checks for any duplicates
		 */
		for (int i = 0; i < WorkBook.size(); i++) {
			// if the workbook only has a null stock in it then replace it or...
			if(WorkBook.get(i).getTicker() == null) {
				WorkBook.remove(i);
				WorkBook.add(stock);
			}
			// compares tickers, if they are the same then stop
			if (WorkBook.get(i).getTicker().compareTo(stock.getTicker()) == 0) {
				return;
			}
		}
		// this adds the stock if everything goes by without any issues
		WorkBook.add(stock);

	}
	/*
	 * This method is used to remove a stock from the personalized workbook. 
	 */
	void removeWorkBook(String Tick) {
		// this loop is here to go through the ArrayList and compare tickers, if it does not exist than nothing will happen
		for (int i = 0; i < WorkBook.size(); i++) {
			// if the ticker exists, then that stock is removed and the for loop is stopped.
			if (Tick.compareTo(WorkBook.get(i).getTicker()) == 0) {
				WorkBook.remove(i);
				return;
			}
		}

	}
	/*
	 * This method is in charge of loading a workbook from a csv file into the personalized workbook
	 */
	void loadWorkBook(String Fileselection) throws IOException {
		// temporary array with the information imported
		String[] temp = fileImport(Fileselection);
		String tempString1 = Arrays.toString(temp).substring(63);
		String tempString2 = tempString1.substring(0, tempString1.length() - 1);
		String tempString3 = tempString2.replaceAll("\\s", "");
		String[] collected = tempString3.split(",");
		
		int tracing = 0;
		// this loop is in charge of adding all the stocks from the csv file into the workbook to be displayed
		for (int j = 0; j < collected.length / 10; j++) {
			StockFunction newStock = new StockFunction(collected[0 + tracing], collected[1 + tracing],
					collected[2 + tracing], collected[3 + tracing], collected[4 + tracing], collected[5 + tracing],
					collected[6 + tracing], collected[7 + tracing], collected[8 + tracing], collected[9 + tracing]);
			WorkBook.add(newStock);
			tracing = tracing + 10;
		}
	}
	
	/*
	 * This method is the save function method, the idea is to save the current workbook to a csv file to be manipulated in excel
	 */
	void toExcelfile(ArrayList<StockFunction> work) throws IOException {
		// naming the saved file to the date and time.
		DateFormat df = new SimpleDateFormat("yy-MM-dd  HH_mm_ss");
		Calendar calobj = Calendar.getInstance();
		String[] workingstring = new String[work.size() * 10];
		int tracing = 0;
		// this loop is in charge of writing all of the stuff into the file using all of the mutators for each stock quote.
		for (int i = 0; i < workingstring.length / 10; i++) {
			workingstring[0 + tracing] = work.get(i).getTicker();
			workingstring[1 + tracing] = work.get(i).getprice();
			workingstring[2 + tracing] = work.get(i).getCUR();
			workingstring[3 + tracing] = work.get(i).getDVE();
			workingstring[4 + tracing] = work.get(i).getvol();
			workingstring[5 + tracing] = work.get(i).getmrktcap();
			workingstring[6 + tracing] = work.get(i).getchng();
			workingstring[7 + tracing] = work.get(i).getPE();
			workingstring[8 + tracing] = work.get(i).getPB();
			workingstring[9 + tracing] = work.get(i).getprft();
			tracing = tracing + 10;
		}
		// this part makes sure that there is a good title as well as the too strings.
		String con = df.format(calobj.getTime());
		FileWriter writer = new FileWriter("WorkBook" + con + ".csv");
		int tracingwriter = 0;
		// writes header
		writer.write("Ticker,Price,CUR,DVE,Volume,Market Cap,Change,P/E,P/B,Profit");
		writer.write("\r\n");
		// this writes each stock as its own line
		for (int i = 0; i < workingstring.length / 10; i++) {
			writer.write(workingstring[0 + tracingwriter].toString() + "," + workingstring[1 + tracingwriter].toString()
					+ "," + workingstring[2 + tracingwriter].toString() + ","
					+ workingstring[3 + tracingwriter].toString() + "," + workingstring[4 + tracingwriter].toString()
					+ "," + workingstring[5 + tracingwriter].toString() + ","
					+ workingstring[6 + tracingwriter].toString() + "," + workingstring[7 + tracingwriter].toString()
					+ "," + workingstring[8 + tracingwriter].toString() + ","
					+ workingstring[9 + tracingwriter].toString());
			writer.write("\r\n");
			tracingwriter = tracingwriter + 10;
		}
		writer.close();
		File file = new File("WorkBook" + con + ".csv");
		// automatically opens the csv file created.
		Desktop.getDesktop().open(file);
	}
	/*
	 * This method is only used once, it reads the txt files and gets the tickers to make qutoes for the IBR
	 */
	String[] fileImport(String Fileselection) {
			int size = 0;
			String[] temp = null;
			try {
				BufferedReader file = new BufferedReader(new FileReader(Fileselection));
				while (file.readLine() != null)
					size++;
				file.close();
			} catch (FileNotFoundException e) {
				//System.out.println("The file does not exist");
			} catch (IOException e) {
				//System.out.println("There was an error reading from the file.");
			}
			if (size > 0) // this is if the file is not null the information is read in.
				try {
					BufferedReader file = new BufferedReader(new FileReader(Fileselection));
					temp = new String[size];
					// reads all the lines, assins them to a string the a temporary array.
					for (int count = 0; count < temp.length; count++) {
						String t = file.readLine();
						if (t != null)
							temp[count] = t;
					}
					file.close();
				} catch (FileNotFoundException e) {
					//System.out.println("The file does not exist");
				} catch (IOException e) {
					//System.out.println("There was an error reading from the file.");
				}
			return temp;
	}
	/*
	 * This method is in charge of consolidating all of the industry arrays.
	 */
	void arrayconsolidation(String[] Financeinfo, String[] Techinfo, String[] Healthcareinfo, String[] Aerospaceinfo)
			throws IOException {
		/*
		 * Each of these loops will go and get quotes from all of the read in strings from the Import method and add of those stocks to their respective ArrayLists.
		 */
		for (int i = 0; i < Financeinfo.length; i++) {
			StockFunction financeOBJ = new StockFunction(Financeinfo[i]); // get quote
			Finance.add(financeOBJ); // add it
		}
		for (int i = 0; i < Techinfo.length; i++) {
			StockFunction techOBJ = new StockFunction(Techinfo[i]);
			Tech.add(techOBJ);
		}
		for (int i = 0; i < Healthcareinfo.length; i++) {
			StockFunction healthcareOBJ = new StockFunction(Healthcareinfo[i]);
			Healthcare.add(healthcareOBJ);
		}
		for (int i = 0; i < Aerospaceinfo.length; i++) {
			StockFunction aerospaceOBJ = new StockFunction(Aerospaceinfo[i]);
			Aerospace.add(aerospaceOBJ);
		}
	}
	/*
	 * This method is used for sorting by the other classes. Using a criteria obtained from the comboBoxes, certain code is run to order the information from greatest (top) to least (bottom).
	 */
	void sortCriteria(String input, ArrayList<StockFunction> list) {
		if (input.compareTo("Ticker") == 0) { // if Ticker
			Collections.sort(list, getTICKER()); // sort the array list by the Ticker criteria
			return;
		}
		/*
		 * All of the other criteria need to be reversed to have the largest at the top.
		 */
		if (input.compareTo("Price") == 0) {
			Collections.sort(list, getPRICE());
			Collections.reverse(list); // reverses to make the greatest on top.
			return;
		}
		if (input.compareTo("Current Ratio") == 0) {
			Collections.sort(list, getCURRENT());
			Collections.reverse(list);
		}
		if (input.compareTo("Debt vs Equity") == 0) {
			Collections.sort(list, getDEBTVSEQUITY());
			Collections.reverse(list);
		}
		if (input.compareTo("Volume") == 0) {
			Collections.sort(list, getVOLUME());
			Collections.reverse(list);
		}
		if (input.compareTo("Market Cap") == 0) {
			Collections.sort(list, getMARCAP());
			Collections.reverse(list);
		}
		if (input.compareTo("Change") == 0) {
			Collections.sort(list, getCHANGE());
			Collections.reverse(list);
		}
		if (input.compareTo("P/E Ratio") == 0) {
			Collections.sort(list, getPRICEEQUITY());
			Collections.reverse(list);
		}
		if (input.compareTo("Profit") == 0) {
			Collections.sort(list, getPROFIT());
			Collections.reverse(list);
		}
		if (input.compareTo("P/B Ratio") == 0) {
			Collections.sort(list, getPRICEBOOK());
			Collections.reverse(list);
		} else {
			return;
		}
	}
	/*
	 * This method is the central one used to configure the tables went they are being displayed or re-displayed.
	 */
	public Object[][] tableconfig(ArrayList<StockFunction> list){
		// creates array
		int size = list.size();
		Object[][] data = new Object[size][10];
		// loops and adds all the correct data
		for (int i = 0; i<size; i++) {
			data[i][0] = list.get(i).getTicker();
			data[i][1] = list.get(i).getprice();
			data[i][2] = list.get(i).getCUR();
			data[i][3] = list.get(i).getDVE();
			data[i][4] = list.get(i).getvol();
			data[i][5] = list.get(i).getmrktcap();
			data[i][6] = list.get(i).getchng();
			data[i][7] = list.get(i).getPE();
			data[i][8] = list.get(i).getPB();
			data[i][9] = list.get(i).getprft();
		}
		return data ;
	}

	@Override
	// overridden specialized toString method for our program.
	public String toString() {
		return (this.getTicker() + " | " + this.getprice() + " | " + this.getCUR() + " | " + this.getDVE() + " | "
				+ this.getvol() + " | " + this.getmrktcap() + " | " + this.getchng() + " | " + this.getPE() + " | "
				+ this.getPB() + " | " + this.getprft() + " | " + "\n\n");
	}

	@Override
	// specializaed overridden compareTo method for our program. 
	public int compareTo(StockFunction Stock) {
		return 0;
	}

}

