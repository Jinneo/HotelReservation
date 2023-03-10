package application;
import java.io.IOException;
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
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.control.DateCell;
import java.util.ResourceBundle;

import org.joda.time.DateTime;

import javafx.util.Callback;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.time.temporal.ChronoUnit;


public class DateHotel extends DataToStringArray implements Initializable, Pages {
	@FXML
	public DatePicker startDate;
	@FXML
	public DatePicker endDate;
	@FXML
	public void moveOn(ActionEvent event) throws IOException {
		Main.switchOut(event, roomselection);
		StartEnd_Send();
	}
	//populates abstract class DataToString before it loads that page.
	
	//added class just so code is more clean
	public class MinDay {
		LocalDate date;
		public MinDay(LocalDate date) {
			this.date = date;
		}
		//CALLBACK function below
		final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
		     public DateCell call(final DatePicker datePicker) {
		         return new DateCell() {
		             @Override 
		             public void updateItem(LocalDate item, boolean empty) {
		                 super.updateItem(item, empty);
		                 if(item.isBefore(date)) {
		                	 //this refers to total datecell node itself
		                	 this.setDisable(true);
		                	 this.setStyle("-fx-background-color: #FFB84C;");		           
		                	 }
		             }
		         };
		     }
		 };
		
	}
	public class MaxDay extends MinDay{
		LocalDate date;
		public MaxDay(LocalDate date) {
			super(date);
			this.date = date;
		}
		//CALLBACK function below
		final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
		     public DateCell call(final DatePicker datePicker) {
		         return new DateCell() {
		             @Override 
		             public void updateItem(LocalDate item, boolean empty) {
		                 super.updateItem(item, empty);
		                 if(item.isBefore(date.plusDays(1L))) {
		                	 //this refers to total datecell node itself
		                	 this.setDisable(true);
		                	 this.setStyle("-fx-background-color: #FFB84C;");		           
		                	 }
		             }
		         };
		     }
		 };
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		startDate.setDayCellFactory(new MinDay(date).dayCellFactory);
//        startDate.setStyle(".today{'-fx-background-color: black;'}");

		startDate.setValue(date);
//		startDate.setValue(null);
		endDate.setDayCellFactory(new MaxDay(date).dayCellFactory);
		endDate.setValue(date.plusDays(1L));
		System.out.println(date);
	}
	public void datesChanged(ActionEvent event) {
		endDate.setDayCellFactory(new MaxDay(startDate.getValue()).dayCellFactory);
		endDate.setValue(startDate.getValue().plusDays(1L));
	}
	public void StartEnd_Send() {
		LocalDate startObj = startDate.getValue();
		LocalDate endObj = endDate.getValue();
		start_date = DateTime.parse(startObj.toString());
		end_date = DateTime.parse(endObj.toString());
		start_date.plusHours(1);
		end_date.plusHours(1);
		String startString = startObj.toString();
		String endString = endObj.toString();
		int daysBetween = (int) ChronoUnit.DAYS.between(startObj, endObj);
		String days = String.valueOf(daysBetween);
		System.out.println(startString + "\n" + endString);
		System.out.println(days);
		Data.add(startString);
		Data.add(endString);
		Data.add(days);
		System.out.println(Data);
	}
	
	

	
}
