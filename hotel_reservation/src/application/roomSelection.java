package application;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import com.jfoenix.controls.JFXButton;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//read the csv file --> compute what rooms are taken, and then filter out those rooms from the arraylist
// then in that arraylist imma update so it changes and then i can add like another waitlist menu possibly, and idk ill determine the queue
//check for each line if date current is between end date and start date... also userID --> ensure that is NOT checked out
//run to check if dates are overdue, if so ensure they are checed_OUT
//if its not CHECKED_OUT --> get ROOM value and put it so that it filters out from the array
//also should work on more sophisticated way of getting from data instead of basing of indexes 
//wait...
//check if their booking is DURING the timezone of the other for this room

//create a notification system, so then it would notify users when they r off the waitlist and 
//if they got kicked outta a room.
//give updates on whether a user got the room or not, and if they did they will be auto-accepted since they alr paid money and only option is to decline and get refund
//pre pay money beforehand basically
public class roomSelection extends DataToStringArray implements Initializable, Pages  {
	@FXML public GridPane gridPane;
	@FXML
	public void move(ActionEvent event) throws IOException {
		System.out.println(event);
		String roomName ;
		int x = 0;
		String roomPrice;
		System.out.println("reach");
		System.out.println(event.getSource());
		ObservableList<Node> toCheck = ((Node)event.getSource()).getParent().getChildrenUnmodifiable();
		for(Node i: toCheck) {
			System.out.println(i.getClass());
			if(i.getClass()== new Label().getClass()) {
				Data.add(((Label)i).getText());
			}
		}
		System.out.println(x);
//		Data.add(roomNames.get(CHK).getText());
//		Data.add(roomPrices.get(CHK).getText());
		populate();
		Main.switchOut(event, extras);
		
	}
	public void populate() {
		ExtraServicesPrices.put("TV", (Integer)25);
		ExtraServicesPrices.put( "Room Service", (Integer)100);
		ExtraServicesPrices.put( "Pool Access", (Integer)30);
		ExtraServicesPrices.put("Regular Meal Plan", (Integer)0);
		ExtraServicesPrices.put("Premium Meal Plan", (Integer)75);
		ExtraServicesPrices.put("No Membership", (Integer)0);
		ExtraServicesPrices.put("Membership", (Integer)35);
	}
	public void goBackHome(ActionEvent event) {
		Data.clear();
		Main.switchOut(event, startpage);
	}
	//check if end date is before, or equal to other bking date and if start date is after or equal to other start date and end date
	//from larger overview -->
	//if start date/end date is in between another booking, 
	//
	@Override 
	public void initialize(URL arg0, ResourceBundle arg1) {
		//try and figure out a more efficent solution, if not this soln is ok
		File file = new File("/Users/pvadlamani/Downloads/Wproject/hotel_reservation/src/application/userData.csv");
		try {
		FileReader readfile = new FileReader(file);
		CSVReader read = new CSVReaderBuilder(readfile).build();
        List<String[]> allUserData = read.readAll(); 
        for(int j = 1; j<allUserData.size(); j++) {
        String nextLine[] = allUserData.get(j);
        if(!(nextLine[0].equals("CHECKED_OUT"))) {
        	System.out.println(nextLine[4]);
        	System.out.println(String.valueOf(new Button(nextLine[4])));
        	//make method below recursive just for fun and also to make it more efficent maybe since no longer three loops
        	for(Node paneChildren: gridPane.getChildren()) {
        		ObservableList<Node> smallPane = (ObservableList<Node>)((Parent) paneChildren).getChildrenUnmodifiable();
        		for(Node eachPane: smallPane) {
        			if((eachPane.getClass()== new JFXButton().getClass()) &&((paneChildren.getId()).equals(nextLine[4].replaceAll("\\s", "")))) {
        				System.out.println("hello");
        				//due to half open intervals, I subtracted minute to make it work
        				//Also, used a library called JODA-TIME to help me deal with overlaps easily 
        				Interval interval = new Interval( start_date.plusMinutes(-1), end_date.plusMinutes(-1) );
        				System.out.println("hello");
        				Interval interval2 = new Interval( DateTime.parse(nextLine[1]), DateTime.parse(nextLine[2]));
        				System.out.println("reached" + interval.overlaps(interval2));
        				System.out.println(interval +"\n" + interval2);
        				System.out.println(interval.isEqual(interval2));
        				System.out.println(interval.overlaps(interval2));
        				System.out.println(interval2.overlaps(interval));

        				if((interval.overlaps(interval2))|| (interval2.overlaps(interval))||(interval.isEqual(interval2)) || (end_date.isEqual(DateTime.parse(nextLine[1])))) {
        					((JFXButton)eachPane).setText("Room Currently Booked");
        					((JFXButton)eachPane).setLayoutX(50);
        					((JFXButton)eachPane).setStyle("-fx-background-color: #FF4000;");
        					((JFXButton)eachPane).setOnAction(e->{{
        						System.out.println("wip-waitlist");
        					}});
        				}
        			}
        		}
        	}
        	}
        }
        
		}catch(IOException | CsvException e) {
			
		}


		
		
	}
}
