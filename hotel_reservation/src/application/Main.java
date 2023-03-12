package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application implements Pages{
	public static void main(String[] args) throws IOException {
		retriveData();
		Runtime.getRuntime().addShutdownHook(new Thread(()->{
			//retrive data
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/pvadlamani/git/repository/hotel_reservation/src/application/date.txt"));
				writer.write(String.valueOf(DataToStringArray.date));

				writer.close();
			} catch(IOException e) {

			}

		}));
		Application.launch(Main.class, args);
		//launch()
	}
	public static void retriveData() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("/Users/pvadlamani/git/repository/hotel_reservation/src/application/date.txt"));
		String dateValue;
		while ((dateValue = br.readLine()) != null) {

			// Print the string

			DataToStringArray.date = LocalDate.parse(dateValue);
		}

	}

	@Override
	public void start(Stage stage) throws IOException {       
		Scene scene = new Scene(loadpage.load());
		stage.setScene(scene);
		stage.show();
	} 

	public static void switchOut(ActionEvent event, FXMLLoader loader) {
		FadeTransition newFade1 = new FadeTransition(Duration.seconds(1));		

		newFade1.setNode((Node)((Node) event.getSource()).getScene().getRoot());
		newFade1.setFromValue(1);
		newFade1.setToValue(0);
		newFade1.setCycleCount(1);
		newFade1.play();
		newFade1.setOnFinished(e ->{{

			try {
				FXMLLoader newLoader = determineLOAD(loader);
				Scene scene;
				scene = new Scene(newLoader.load());
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				switchIn((Node)scene.getRoot());
				stage.setScene(scene);
				stage.show();
			} catch(IOException catchE){
				//
			}
		}});


	}
	public static void switchIn(Node loadin) {
		FadeTransition newFade1 = new FadeTransition(Duration.seconds(1));		
		newFade1.setNode(loadin);
		newFade1.setFromValue(0);
		newFade1.setToValue(1);
		newFade1.setCycleCount(1);
		newFade1.play();

	}
	public static FXMLLoader determineLOAD(FXMLLoader load) {
		FXMLLoader newLoad = null;

		String valueOf = String.valueOf(load);
		if (valueOf.equals((String.valueOf(terms)))) {
			newLoad = new FXMLLoader(Hotel.class.getResource("terms.fxml"));
		} else if (valueOf.equals(String.valueOf(registrationpage))) {
			newLoad = new FXMLLoader(Hotel.class.getResource("registration.fxml"));
		} else if (valueOf.equals(String.valueOf(loginpage))) {
			newLoad = new FXMLLoader(Hotel.class.getResource("login.fxml"));
		} else if (valueOf.equals(String.valueOf(loginsuccess))) {
			newLoad = new FXMLLoader(Hotel.class.getResource("loginSuccess.fxml"));
		} else if (valueOf.equals(String.valueOf(startpage))) {
			newLoad = new FXMLLoader(Hotel.class.getResource("startpage.fxml"));
		} else if (valueOf.equals(String.valueOf(extras))) {
			newLoad = new FXMLLoader(Hotel.class.getResource("registration3.fxml"));
		} else if (valueOf.equals(String.valueOf(totalbill))) {
			newLoad = new FXMLLoader(Hotel.class.getResource("registration4.fxml"));
		} else if (valueOf.equals(String.valueOf(roomselection))) {
			newLoad = new FXMLLoader(Hotel.class.getResource("registration2.fxml"));
		} else if (valueOf.equals(String.valueOf(ID))) {
			newLoad = new FXMLLoader(Hotel.class.getResource("ID.fxml"));
		} else if(valueOf.equals(String.valueOf(postbill))) {
			newLoad = new FXMLLoader(Hotel.class.getResource("PostBill.fxml"));
		}else if(valueOf.equals(String.valueOf(advancedlogin))) {
			newLoad = new FXMLLoader(Hotel.class.getResource("advancedLog.fxml"));
		}else if(valueOf.equals(String.valueOf(adminlogin))) {
			newLoad = new FXMLLoader(Hotel.class.getResource("adminlp.fxml"));
		}else if(valueOf.equals(String.valueOf(adminpanel))) {
			newLoad = new FXMLLoader(Hotel.class.getResource("adminLog.fxml"));
		}else if(valueOf.equals(String.valueOf(userpanel))) {
			newLoad = new FXMLLoader(Hotel.class.getResource("kickuser.fxml"));
		} else if(valueOf.equals(String.valueOf(discountload))) {
			newLoad = new FXMLLoader(Hotel.class.getResource("discount.fxml"));
		} else if(valueOf.equals(String.valueOf(admindiscount))) {
			newLoad = new FXMLLoader(Hotel.class.getResource("admindiscount.fxml"));
		} 
		return newLoad;
	}
	//later - work on implementing a cleaner switchScreen method
	//interfaces of all the stages


}