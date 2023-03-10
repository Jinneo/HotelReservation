package application;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class Admin implements Pages, Initializable{

	@FXML
	JFXComboBox<String> activeUsers;
//	}
	@FXML
	public void gendiscount(ActionEvent event) {
		
	}
	@FXML
	public void kickUser(ActionEvent event) {
		Main.switchOut(event, userpanel);
	}
	@FXML
	public void logdata(ActionEvent event) {
		
	}
	@FXML
	public void addpoints(ActionEvent event) {
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Ok");
		try {
			System.out.println("Ok");

			if(arg0.sameFile(new URL("file:/Users/pvadlamani/Downloads/Wproject/hotel_reservation/bin/application/kickuser.fxml"))){
				System.out.println("Ok");
			ObservableList<String> activeusers = FXCollections.observableArrayList();
			File file = new File("/Users/pvadlamani/Downloads/Wproject/hotel_reservation/src/application/userData.csv");
			try {
				FileReader readfile = new FileReader(file);
				CSVReader read = new CSVReaderBuilder(readfile).build();
			    List<String[]> allUserData = read.readAll(); 
			    for(String[] nextLine : allUserData) {
			        if(!(nextLine[0].equals("CHECKED_OUT")) && !(nextLine[0].equals("UserID"))) {
			        	activeusers.add(nextLine[0]);
			        	}
			    }
			    activeUsers.setItems(activeusers);
			} catch(IOException e) {
				//
			} catch (CsvException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
}
}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
