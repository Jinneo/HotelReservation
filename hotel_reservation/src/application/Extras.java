package application;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import application.LoginSuccess.pf10;
import application.LoginSuccess.pf15;
import application.LoginSuccess.pf20;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Extras extends DataToStringArray implements Initializable, Pages {
	@FXML
	public List<JFXCheckBox> checkBox;
	@FXML
	public List<JFXRadioButton> radioBtns;
	@FXML
	public TextField prevID;
	@FXML
	public TextField discountID;

	public void Extras_Send() {
		for(int i = 0; i<checkBox.size(); i++) {
			Data.add(String.valueOf(checkBox.get(i).isSelected()) + checkBox.get(i).getText().substring(0, checkBox.get(i).getText().indexOf('~')-1));
		}
		for(int i = 0; i<radioBtns.size(); i++) {
			Data.add(String.valueOf(radioBtns.get(i).isSelected()) + radioBtns.get(i).getText().substring(0, radioBtns.get(i).getText().indexOf('~')-1));
		}
	}
	public void checkID() throws IOException, CsvException {
		File permfile = new File("/Users/pvadlamani/git/repository/hotel_reservation/src/application/permaRecord.csv");
		CSVReader permread = new CSVReaderBuilder(new FileReader(permfile)).build();
		List<String[]> permUserData = permread.readAll(); 
		for(String[] i: permUserData) {
			if(prevID.getText().equals(i[0])) {
				prevIDText = prevID.getText();
				isValidID = true;
				break;
			}
		}
	}
	public void checkDiscount() throws IOException, CsvException, ClassNotFoundException {
		pf10 obj10 = new pf10();
		pf15 obj15 = new pf15();
		File permfile = new File("/Users/pvadlamani/git/repository/hotel_reservation/src/application/discount.csv");
		CSVReader discount = new CSVReader(new FileReader(permfile));
		List<String[]> discountData = discount.readAll(); 
		for(String[] i: discountData) {
			if(discountID.getText().equals(i[0])) {
				//



				//

				if(Class.forName(i[1]).equals(obj10.getClass())) {
					discountAmnt = 0.10;
				} else if(Class.forName(i[1]).equals(obj15.getClass())) {
					discountAmnt = 0.15;
				} else {
					discountAmnt = 0.2;
				}

				isValidDiscount = true;
				CSVWriter writer = new CSVWriter(new FileWriter(permfile));
				i[0] = "USED";
				writer.writeAll(discountData);
				writer.flush();
				writer.close();
				break;
			}
		}
	}
	@FXML
	public void benefits(ActionEvent event) throws IOException, CsvException, ClassNotFoundException {
		Extras_Send();	
		checkID();
		checkDiscount();
		Main.switchOut(event, totalbill);	    
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		for(int i = 0; i<checkBox.size(); i++) {

			checkBox.get(i).setText(checkBox.get(i).getText() + " ~ " + (ExtraServicesPrices.get(ExtraServicesPrices.keySet().toArray()[i])));
		}

		for(int i = 0; i<radioBtns.size(); i++) {
			radioBtns.get(i).setText(radioBtns.get(i).getText() + " ~ " + ExtraServicesPrices.get(ExtraServicesPrices.keySet().toArray()[i+radioBtns.size()-1]));
		}



	}

}
//<fx:define>
//<ArrayList fx:id="checkBox" >
//    <fx:reference source="TV"/>
//    <fx:reference source="RoomService"/>
//    <fx:reference source="PoolAccess"/>
//</ArrayList>
//</fx:define>
//<fx:define>
//<ArrayList fx:id="radioBtns" >
//    <fx:reference source="RegularMealPlan"/>
//    <fx:reference source="PremiumMealPlan"/>
//    <fx:reference source="NoMembership"/>
//    <fx:reference source="Membership"/>
//</ArrayList>
//</fx:define>