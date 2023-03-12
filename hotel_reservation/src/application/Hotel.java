package application;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

import java.time.temporal.ChronoUnit;


public class Hotel extends DataToStringArray implements Initializable, Pages  {
	@FXML
	Label currDate;
	@FXML
	TextField userAdmin;
	@FXML
	TextField passAdmin;
	@FXML
	Label checkLabel;

	@FXML
	public void registerStart(ActionEvent event) throws IOException {
		Main.switchOut(event, registrationpage);
	}
	@FXML
	public void changeDate(ActionEvent event) throws IOException, CsvException{
		File file = new File("/Users/pvadlamani/git/repository/hotel_reservation/src/application/userData.csv");
		date = date.plusDays(1L);
		currDate.setText(date.toString());
		FileReader readfile = new FileReader(file);
		CSVReader read = new CSVReaderBuilder(readfile).build();
		List<String[]> allUserData = read.readAll(); 
		for(String[] nextLine : allUserData) {
			try {	
				if(date.isAfter(LocalDate.parse(nextLine[2]))) {
					CSVWriter writer = new CSVWriter(new FileWriter(file));

					nextLine[0] = "CHECKED_OUT";
					writer.writeAll(allUserData);
					writer.flush();
					writer.close();
				}
			} catch(DateTimeParseException e) {

			}
		}
	}
	@FXML
	public void loginStart(ActionEvent event) throws IOException {
		Main.switchOut(event, advancedlogin);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			if(arg0.sameFile(new URL("file:/Users/pvadlamani/git/repository/hotel_reservation/bin/application/startpage.fxml"))){
				currDate.setText(date.toString());
			}  
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block

		}

	}
	@FXML
	public void userLogin(ActionEvent event) {
		Main.switchOut(event, loginpage);
	}
	@FXML
	public void adminLogin(ActionEvent event) {
		Main.switchOut(event, adminlogin);
	}
	@FXML
	public void checkAdmin(ActionEvent event) {
		File file = new File("/Users/pvadlamani/git/repository/hotel_reservation/src/application/adminData.csv");
		try {
			boolean validated = false;
			FileReader outputfile = new FileReader(file);
			CSVReader read = new CSVReaderBuilder(outputfile).build();
			List<String[]> allUserData = read.readAll(); 
			for(String[] i: allUserData) {

				if(i[0].equals(userAdmin.getText())) {
					if(i[1].equals(passAdmin.getText())) {
						validated = true;
						adminSuccess = userAdmin.getText();
						Main.switchOut(event, adminpanel);

						break;
					}

				}
			}
			if(!validated) {
				checkLabel.setText("incorrect try again");
			} 
		}
		catch (IOException | CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
