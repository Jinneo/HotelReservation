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
	//below is all the data that is transferred between all classes
	//main idea behind sending/temporarily saving data here is so it actually saves me from using extra for loops
	//ie I would run a for loop somewhere in code and acquire Data A
	//instead of re-running for loop in another class just to get the same data anyways
	//I just extend all classes to this class where Data is being sent back and forth
	//essentially a temporary store
	//also made everything STATIC so it would not be overwritten each time it was reloaded
	//it reloads bc there are multiple controllers for seperate FXML files
	
	
	//acquire all the data from user registration and put into arraylist of strings to be written to csv file 
	static ArrayList<String> Data = new ArrayList<String>();
	
	//make the extra services into a linked has map which will be populated when the controller of this class is intialized
	//made it into a linkedhashmap so I can just put all the data from here to the fxml file and NOT manually do it
	//manually means -> I would try and input data through the fxml file itself through scenebuilder(and forced to use tedious string manipulation)
	//automatically means -> easily I can add extra items via one of code if I want to and access the according data to them easily
	static LinkedHashMap<String, Integer> ExtraServicesPrices = new LinkedHashMap<String, Integer>();
	static LocalDate date = LocalDate.now();
	static String loginSuccess;
	static DateTime start_date;
	static DateTime end_date;
	
	//for all the reservations for a user
	//i can use this information when i gather it to assign to accordingly and plays a key role in saving an extra for loop in PostBill
	//in PostBill it uses this data when filtered in LoginSuccess to determine
	//which reservation is being cancelled/checked-in/checked-out and what isn't
	static List<String[]> sentLine = new ArrayList<String[]>();
	
	
	static String sendPMS;
	static boolean isValidID;
	static String prevIDText;
	static boolean isValidDiscount;
	static double discountAmnt;
	static String adminSuccess;

	//used OPENCSV library which is a csv reader/writer 
	//function below determines whether to add header to function or not
	public boolean RF(File file) {
		boolean isHeader = false;
		try {
			FileReader readfile = new FileReader(file);
			CSVReader read = new CSVReader(readfile);
			List<String[]> allUserData = read.readAll(); 

			if(allUserData ==null || allUserData.isEmpty()) {
				isHeader = true;
			}
		}
		catch(IOException | CsvException e) {
			//nothing just catches it.
		}
		return isHeader;
	}
	//function below adds the users data from registration to two csv files
	//one is the userData one and the permanent one for log of all admin and users's actions
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
			//below converts the arraylist of Strings to an array of Strings since parmater is just a string of array
			writer.writeNext(Data.toArray(new String[Data.size()]));
			permawriter.writeNext(Data.toArray(new String[Data.size()]));
			permawriter.close();
			writer.close();

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//makes sure its clear for next time a user may register
		Data.clear();
	}
	//below is for permanent record so you can keep track of user's reward points
	//only does this when you don't enter your previous ID in registration
	public void PMS(ArrayList<String> Data) {
		File file = new File("/Users/pvadlamani/git/repository/hotel_reservation/src/application/permaRecord.csv");
		try {
			FileWriter outputfile = new FileWriter(file, true);
			CSVWriter writer = new CSVWriter(outputfile);
			if(RF(file)) {
				String[] header = {"UserID", "RwdPts"};

				writer.writeNext(header);
			}

			//
			writer.writeNext(Data.toArray(new String[Data.size()]));
			writer.close();

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Data.clear();
	}
	//writes all the discount codes to a discount.csv file
	public void Discount(ArrayList<String> Data) {
		File file = new File("/Users/pvadlamani/git/repository/hotel_reservation/src/application/discount.csv");
		try {
			FileWriter outputfile = new FileWriter(file, true);
			CSVWriter writer = new CSVWriter(outputfile);
			if(RF(file)) {
				String[] header = {"DiscountID", "DiscountClass"};

				writer.writeNext(header);
			}

			writer.writeNext(Data.toArray(new String[Data.size()]));
			writer.close();

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Data.clear();
	}

}
