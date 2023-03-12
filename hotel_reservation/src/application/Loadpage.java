package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Loadpage implements Initializable, Pages {
	@FXML
	ImageView img;
	@FXML
	Label topHeading;
	@FXML
	Label botHeading;
	@FXML
	JFXButton button;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//will work on more efficent method later

		FadeTransition newFade = new FadeTransition(Duration.seconds(3), img);
		newFade.setFromValue(0);
		newFade.setToValue(1);
		newFade.setCycleCount(1);
		newFade.play();
		newFade.setOnFinished(event ->{{
			FadeTransition newFade1 = new FadeTransition(Duration.seconds(1), topHeading);
			newFade1.setFromValue(0);
			newFade1.setToValue(1);
			newFade1.setCycleCount(1);
			newFade1.play();
			topHeading.setVisible(true);

			FadeTransition newFade2 = new FadeTransition(Duration.seconds(1), botHeading);
			newFade2.setFromValue(0);
			newFade2.setToValue(1);
			newFade2.setCycleCount(1);
			newFade2.play();
			botHeading.setVisible(true);

			FadeTransition newFade3 = new FadeTransition(Duration.seconds(1), button);
			newFade3.setFromValue(0);
			newFade3.setToValue(1);
			newFade3.setCycleCount(1);
			newFade3.play();
			button.setVisible(true);

		}});
		button.setOnAction(event->{{
			Main.switchOut(event, terms);


		}});

	}

}
