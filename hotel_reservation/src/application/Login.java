package application;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

public class Login extends DataToStringArray implements Pages{
	@FXML
	TextField loginField;
	@FXML
	Label errorMessage;
	boolean login_Worked;
	public void checkLogin(ActionEvent event) throws IOException {

		File file = new File("/Users/pvadlamani/git/repository/hotel_reservation/src/application/userData.csv");
		File permfile = new File("/Users/pvadlamani/git/repository/hotel_reservation/src/application/permaRecord.csv");

		try {
			FileReader readfile = new FileReader(file);
			CSVReader read = new CSVReaderBuilder(readfile).build();
			List<String[]> allUserData = read.readAll(); 
			CSVReader permread = new CSVReaderBuilder(new FileReader(permfile)).build();
			List<String[]> permUserData = permread.readAll(); 
			for(String[] nextLn: permUserData) {

				if(loginField.getText().equals(nextLn[0])) {
					sendPMS = nextLn[1];

					login_Worked = true;
					for(String[] nextLine : allUserData) {
						if(loginField.getText().equals(nextLine[0]) && !(date.isAfter(LocalDate.parse(nextLine[2])))) {
							sentLine.add(nextLine);
							
						} 
						//				        
					}
					loginSuccess = loginField.getText();
					loginSuccesful(event);
				}else {
					login_Worked = false;
				}

			}

			if(!login_Worked) {
				errorMessage.setText("Login failed please try again");
				login_Worked = false;
			}


		} catch(IOException e) {
			//
		} catch (CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void loginSuccesful(ActionEvent event) throws IOException {
		Main.switchOut(event, loginsuccess);
	}
	public void load(ActionEvent event) throws IOException {
		Main.switchOut(event, startpage);
	}
	@FXML
	public void goBackHome(ActionEvent event) {
		Main.switchOut(event, startpage);
	}

}
