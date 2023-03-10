package application;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.*;
import java.util.*;

public class LoginSuccess extends DataToStringArray implements Buttons, Initializable, Pages {
	@FXML
	Label rwdpt;
	@FXML
	TabPane ReservationOpts;
//	@FXML
	ArrayList<GridPane> rsvns = new ArrayList<GridPane>();
	@Override
	public void userinterface() {
		// add features
		// - cancelLate, cancel reg, check in/check in out reg, terms and condition
		// add warning to check-in that after u check in you will no longer be able to cancel reservation, and will be refunded no money if you leave early
		//generate discn with rwd pts
		
		//when will reward poitns b added
		//added at end of jit
		//user starts off with 0 rwd pts
		//perm record -> 0 rwd pts
		//user first thing ends and goes to CHECK_OUT
		//then, RWD Pts collected from prm record & current rcrd for now we will do just rwd pts = total cost
		
		//then when user LOGINS in, there are new buttons created for discount shit below other fats.
		//that goes through perma record & fetches the rwd based on connection
		
		//then when check out happens it determines the RWD points locally, and then adds that to according fitfat sr
		
		//ADD SYSTEM TO CHECK BTNS BETWEEN RESERVATIONS
		
		
		//FIX-- cancel DOES NOT give reward pts make sure
		//ADD LABELS FOR RESERVATIONS TO SHOW WHICH ONES THEY R 
		
		//bug where it duplicates twice idk why
	

		
		
		int check = 0;
		boolean isReservation = false;
		File file = new File("/Users/pvadlamani/Downloads/Wproject/hotel_reservation/src/application/userData.csv");
		try {
			FileReader readfile = new FileReader(file);
			CSVReader read = new CSVReaderBuilder(readfile).build();
	        List<String[]> allUserData = read.readAll(); 
	        System.out.println(sendPMS);
	        rwdpt.setText(rwdpt.getText() + sendPMS);
		    for(String[] nextLine : allUserData) {
		    	if(loginSuccess.equals(nextLine[0])) {
		    		//send CHECK value.
		    		isReservation = true;
		    		check++;
		    		final GridPane newGP = new GridPane();
		    		final JFXButton checkIn = new JFXButton("Check In");
		    		final JFXButton checkOut = new JFXButton("Check Out");
		    		final JFXButton cancelLate = new JFXButton("Cancel Late");
		    		final JFXButton cancel = new JFXButton("Cancel Early");
		    		final StackPane newPane = new StackPane();
			    	newPane.setMinWidth(317);
			    	newPane.setMinHeight(140);
			    	newPane.setLayoutY(20);
			    	newPane.setAlignment(Pos.CENTER);
		    		checkIn.setStyle("-fx-background-color: deepskyblue;");
		    		checkOut.setStyle("-fx-background-color: deepskyblue;");
		    		cancelLate.setStyle("-fx-background-color: deepskyblue;");
		    		cancel.setStyle("-fx-background-color: deepskyblue;");
		    		newGP.setVgap(15);
		        	if(date.isBefore(LocalDate.parse(nextLine[1]))) {
		        		cancel.setOnAction(event->{{
		        			try {
		        				sentLine.removeIf((String[] s) -> !(Arrays.equals(s, nextLine)));
		        				CSVWriter writer = new CSVWriter(new FileWriter(file));
			        			System.out.println(nextLine.length);
			        			nextLine[0] = "CHECKED_OUT";
			        			nextLine[nextLine.length-1] = "ONTIME";
			        			writer.writeAll(allUserData);
			        			writer.flush();
			        			writer.close();
			        			sentLine.get(0)[sentLine.get(0).length-1] = "ONTIME";
								Main.switchOut(event, postbill);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		        		}});
		    			GridPane.setHalignment(cancel, HPos.CENTER);
		        		newGP.getChildren().addAll(cancel);
		        		
		        	} 
		        	else if(Boolean.valueOf(nextLine[nextLine.length-2].toLowerCase())) {
		        		checkOut.setOnAction(event->{{
		        			try {
		        				sentLine.removeIf((String[] s) -> !(Arrays.equals(s, nextLine)));
		        				CSVWriter writer = new CSVWriter(new FileWriter(file));
			        			System.out.println(nextLine.length);
			        			nextLine[0] = "CHECKED_OUT";
			        			writer.writeAll(allUserData);
			        			writer.flush();
			        			writer.close();
								Main.switchOut(event, postbill);
								
								//add a REPORT of check out 
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		        		}});
		    			GridPane.setHalignment(checkOut, HPos.CENTER);
		        		newGP.getChildren().add(checkOut);
		        		
		        		
		        	}
		        	else if((date.isAfter(LocalDate.parse(nextLine[1]))|| date.isEqual(LocalDate.parse(nextLine[1]))) && (!date.isAfter(LocalDate.parse(nextLine[2])))) {
		        		checkIn.setOnAction(event ->{{
		        			try {
		        				
		        			CSVWriter writer = new CSVWriter(new FileWriter(file));
		        			System.out.println(nextLine.length);
		        			nextLine[14] = "true";
		        			writer.writeAll(allUserData);
		        			writer.flush();
		        			writer.close();
		        			goBackHome(event);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
		        			
		        			
		        		}});
		        		cancelLate.setOnAction(event->{{
		        			try {
		        				sentLine.removeIf((String[] s) -> !(Arrays.equals(s, nextLine)));
			        			CSVWriter writer = new CSVWriter(new FileWriter(file));
			        			System.out.println(nextLine.length);
			        			nextLine[0] = "CHECKED_OUT";
			        			nextLine[nextLine.length-1] = "LATE";
			        			writer.writeAll(allUserData);
			        			writer.flush();
			        			writer.close();
								Main.switchOut(event, postbill);
			        			sentLine.get(0)[sentLine.get(0).length-1] = "LATE";

								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

		        		}});
		        		GridPane.setHalignment(checkIn, HPos.CENTER);
		    			GridPane.setHalignment(cancelLate, HPos.CENTER);
		    			newGP.add(checkIn, 0,0);
			        	newGP.add(cancelLate, 0, 1);		        			
		        	} 
		        	Tab newTab = new Tab("Reservation" + (check));

			    	AnchorPane newAnchp = new AnchorPane();
			    	Label reservationDate = new Label(nextLine[1] + " - " + nextLine[2] + "\n" + "\t\t\t"+nextLine[4]);
			    	reservationDate.setFont(new Font("System", 15));
			    	newGP.setAlignment(Pos.CENTER);
			    	StackPane.setAlignment(reservationDate, Pos.TOP_CENTER);
			    	StackPane.setAlignment(newGP, Pos.CENTER);
			    	newPane.getChildren().addAll(newGP, reservationDate);
			    	newAnchp.getChildren().add(newPane);
			    	newTab.setContent(newAnchp);
			    	
				    ReservationOpts.getTabs().add(newTab);
		        	rsvns.add(newGP);
		    	}	    	
		    }
		    System.out.println(rsvns);
		    read.close();
		} catch(IOException e) {
			//
		} catch (CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		if(!isReservation) {
			Tab newTab = new Tab("Reservation" );

	    	AnchorPane newAnchp = new AnchorPane();
	    	Label reservationDate = new Label("NO RESERVATIONS");
	    	reservationDate.setFont(new Font("System", 30));

	    	StackPane newPane = new StackPane();
	    	newPane.setMinWidth(317);
	    	newPane.setMinHeight(140);
	    	newPane.getChildren().addAll(reservationDate);
	    	
	    	
	    	StackPane.setAlignment(reservationDate, Pos.CENTER);
	    	newAnchp.getChildren().add(newPane);
	    	newTab.setContent(newAnchp);
	    	
		    ReservationOpts.getTabs().add(newTab);
		}
	}
	@Override
	public void userLog() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void discount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kickUser() {
		// TODO Auto-generated method stub
		
	}
	@FXML
	public void goBackHome(ActionEvent event) throws IOException {
		Main.switchOut(event, startpage);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		userinterface();
	}

	
	
}
