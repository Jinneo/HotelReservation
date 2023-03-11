package application;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.joda.time.DateTime;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import javafx.fxml.Initializable;

import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class DataToStringArray implements Serializable{
	static ArrayList<String> Data = new ArrayList<String>();
	static LinkedHashMap<String, Integer> ExtraServicesPrices = new LinkedHashMap<String, Integer>();
	//Java serialization
	static LocalDate date = LocalDate.now();
	static String loginSuccess;
	//rmv below later
	static DateTime start_date;
	static DateTime end_date;
	static List<String[]> sentLine = new ArrayList<String[]>();
	static String sendPMS;
	static boolean isValidID;
	static String prevIDText;
	static boolean isValidDiscount;
	static double discountAmnt;
	static String adminSuccess;
	
	//link hash map sendline
//	static List keys = new ArrayList(ExtraServicesPrices.keySet());
//	static int[] ExtraServicesPrices = new int[] {25, 100, 30, 0, 75, 0, 35};
	
	
	public boolean RF(File file) {
		boolean isHeader = false;
		try {
	        FileReader readfile = new FileReader(file);
	        CSVReader read = new CSVReader(readfile);
	        List<String[]> allUserData = read.readAll(); 
        System.out.println(allUserData);
        if(allUserData ==null || allUserData.isEmpty()) {
        	isHeader = true;
        }
		}
		catch(IOException | CsvException e) {
			//nothing just catches it.
		}
		return isHeader;
	}
	public void DTS(ArrayList<String> Data) {
		File file = new File("/Users/pvadlamani/git/repository/hotel_reservation/src/application/userData.csv");
		File permafile = new File("/Users/pvadlamani/git/repository/hotel_reservation/src/application/adminuserlog.csv");

	    try {
	        FileWriter outputfile = new FileWriter(file, true);
	        CSVWriter writer = new CSVWriter(outputfile);
	        CSVWriter permawriter = new CSVWriter(new FileWriter(permafile, true));
	        if(RF(file)) {
	        	String[] header = {"UserID","Startdate", "Enddate", "InBetween", "RoomNo", "RoomPrices", "TV", "RS", "PA", "RMP", "PMP", "NM", "M", "TCOST", "CheckedIn", "Canceled"};
	        	
		  	    writer.writeNext(header);
		  	    
	        }	    
//	  	    System.out.println(read);
	  	    writer.writeNext(Data.toArray(new String[Data.size()]));
	  	    permawriter.writeNext(Data.toArray(new String[Data.size()]));
//	  	    for(String i: Data) {
//	  	    	writer.writeNext(new String[] {i});
//	  	    }
	  	    permawriter.close();
	  	    writer.close();
	  	    
	    }
	    catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    Data.clear();
	}
	public void PMS(ArrayList<String> Data) {
		File file = new File("/Users/pvadlamani/git/repository/hotel_reservation/src/application/permaRecord.csv");
	    try {
	        FileWriter outputfile = new FileWriter(file, true);
	        CSVWriter writer = new CSVWriter(outputfile);
	        if(RF(file)) {
	        	String[] header = {"UserID", "RwdPts"};
	        	
		  	    writer.writeNext(header);
	        }
	  	    
//	  	    System.out.println(read);
	  	    writer.writeNext(Data.toArray(new String[Data.size()]));
//	  	    for(String i: Data) {
//	  	    	writer.writeNext(new String[] {i});
//	  	    }
	  	    writer.close();
	  	    
	    }
	    catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    Data.clear();
	}
	public void Discount(ArrayList<String> Data) {
		File file = new File("/Users/pvadlamani/git/repository/hotel_reservation/src/application/discount.csv");
	    try {
	        FileWriter outputfile = new FileWriter(file, true);
	        CSVWriter writer = new CSVWriter(outputfile);
	        if(RF(file)) {
	        	String[] header = {"DiscountID", "DiscountClass"};
	        	
		  	    writer.writeNext(header);
	        }
	  	    
//	  	    System.out.println(read);
	  	    writer.writeNext(Data.toArray(new String[Data.size()]));
//	  	    for(String i: Data) {
//	  	    	writer.writeNext(new String[] {i});
//	  	    }
	  	    writer.close();
	  	    
	    }
	    catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    Data.clear();
	}
	
}
