package application;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;

public class Totalbill extends DataToStringArray implements Initializable, Pages {
	@FXML
	public StackPane TotalCost;
	@FXML
	public GridPane totalPane;
	ArrayList<Label> totalLabels = new ArrayList<Label>();
//	ArrayList<>
	public void getData() {
		System.out.println(Data);
		double total = 0;
		int roomCalculation, RateOfRoom = 0, DaysInBetween = 0, yComp = 0;
		String RoomName = null;
		Iterator<String> itr = Data.iterator();
		while(itr.hasNext()) {
			 String key = itr.next();
			  if(key.contains("true")) {
				  System.out.println(ExtraServicesPrices.get(key.substring(key.indexOf('e')+1)));
				  total += ExtraServicesPrices.get(key.substring(key.indexOf('e')+1));
				  totalLabels.add(new Label( String.valueOf(key.substring(key.indexOf('e')+1)) + " price: " + String.valueOf(ExtraServicesPrices.get(key.substring(key.indexOf('e')+1)))));
			  } else if(StringUtils.isNumeric(key)) {
					DaysInBetween = Integer.parseInt(key);
			  } else if(key.contains("Room")){
				    RoomName = key;
			  } else if (key.contains("$")){
				  	//String manipulation
				  	RateOfRoom = Integer.parseInt(key.substring(key.indexOf(':')+2, key.indexOf('$')));
					roomCalculation = RateOfRoom*DaysInBetween;
					total += roomCalculation;
					totalLabels.add(new Label(RoomName + "- price : " + String.valueOf(RateOfRoom)));
					totalLabels.add(new Label("Days staying " + String.valueOf(DaysInBetween)));
					totalLabels.add(new Label("room cost " + String.valueOf(roomCalculation)));
			  } else {
				  System.out.println(key);
			  }
		}
		if(isValidDiscount) {
			totalLabels.add(new Label("Original cost: " + (total)));
			totalLabels.add(new Label("Discount : " + (discountAmnt*100) + "%"));
			total = total-total*discountAmnt;
			totalLabels.add(new Label("Discounted cost: " + (total)));
			isValidDiscount = false;
		} else {totalLabels.add(new Label("Total cost: " + (total)));};
		System.out.println(total);
		for(Label i: totalLabels) {
			//need to remove stackpane its literally useless here
//			StackPane.setAlignment(i, Pos.CENTER);
			totalLabels.get(totalLabels.indexOf(i)).setLayoutY(yComp+=25);
			totalLabels.get(totalLabels.indexOf(i)).setFont(new Font("System", 20));
			GridPane.setHalignment(i, HPos.CENTER);
			totalPane.add(totalLabels.get(totalLabels.indexOf(i)), 0, totalLabels.indexOf(i));
		}
		Data.add(String.valueOf(total));
		//--> null below ensures for later that the checkedIn thing can actually work otherwise the array will not count it as a vaid array space.
		Data.add("PLACE_HOLDER");
		Data.add("PLACE_HOLDER");
		//figure out system to calculate price - days
		//room
		//extra fees, so if it is TRUE in fact, then correlate to index of a new array I'll call PRICES -- bc right now, seven values I need to take care of -> [25, 100, 30, 0, 75, 0, 35] 
	}
	public void MoveToIdPage(ActionEvent event) throws IOException, NoSuchAlgorithmException {
		//check if fit is alr there for now ill just L
		generateUserID(event);
		totalPane.getChildren().clear();
	}
	//using Java's random generator for secure numbers
	public void generateUserID(ActionEvent event) throws NoSuchAlgorithmException {
		if(isValidID) {
			isValidID = false;
			Data.add(0, prevIDText);
			super.DTS(Data);
			Main.switchOut(event, startpage);

		}else {
			SecureRandom random = new SecureRandom();
			byte bytes[] = new byte[4];
			random.nextBytes(bytes);
			//converts generated bytes to string with creating a new String obj with its parmaters of the (byte[], standardcharset)
			String UID = String.valueOf(new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8));
			//adding to 0th index no matter what so its easy to access.
			Data.add(0,UID);
			ArrayList<String> permvals = new ArrayList<String>();
			permvals.add(UID);
			permvals.add("0");
			PMS(permvals);
			System.out.println(UID);
			Main.switchOut(event, ID);

		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		getData();
	}
	
}
