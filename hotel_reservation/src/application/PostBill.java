package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class PostBill extends DataToStringArray implements Pages, Initializable{
	@FXML
	Label header;
	@FXML
	Label content;
	int rwdpts;
	static int prefIndex;
	@FXML
	public void goHome(ActionEvent event) {
		sentLine.clear();
		Main.switchOut(event, startpage);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FileReader readfile;
		try {
			readfile = new FileReader("/Users/pvadlamani/eclipse/finalPROJECT/hotel_reservation/src/application/permaRecord.csv");
			CSVReader read = new CSVReaderBuilder(readfile).build();
	        List<String[]> allUserData = read.readAll(); 
	        for(String[] i: allUserData) {
				for(String[] sendLine: sentLine) {
					
					if(i[0].equals(sendLine[0])) {
					System.out.println(sendLine[sendLine.length-1]);
					if(sendLine[sendLine.length-1].equals("ONTIME")) {
						header.setText("Cancelled Succesfully");
						content.setText("Refund Amount(100%): " + sendLine[sendLine.length-3]);
						break;
					} else if(sendLine[sendLine.length-1].equals("LATE")) {
						header.setText("Cancelled Succesfully");
						content.setText("Original Amount: " + sendLine[sendLine.length-3] + "\nRefund Amount(70%): " + Integer.parseInt(sendLine[sendLine.length-3])*0.7);
						break;
					} else {
						rwdpts = Integer.parseInt(sendLine[sendLine.length-3]);
						System.out.println(rwdpts);
						CSVWriter writer = new CSVWriter(new FileWriter("/Users/pvadlamani/eclipse/finalPROJECT/hotel_reservation/src/application/permaRecord.csv"));
	        			i[1] = String.valueOf(Integer.parseInt(i[1])+rwdpts);
	        			writer.writeAll(allUserData);
	        			writer.flush();
	        			writer.close();
						content.setText(content.getText() + ""+rwdpts + "\nYour Total Reward Points: " + i[1]);
						break;
					}	
					}
				}
	        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
//reward points -> generate discounts essentially we will add a text field at submit cc info and what nit 
//add for every login
//accumulate reward points by checking which IDs r same and then get the rwd category and add jit up
//as for dealing with login dates n shit just do all of the FIRST index of it
//run another loop to read through fat to find same ID possibly, if there is match just add reward points from saved previous fat and add it so

//first of userdata shit -> enter the login and get proper options
//second of permdata which will contain only reward pts and ID, and check to login


//REWARD POINTS only added AFTER the check out is finished