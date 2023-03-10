package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Terms implements Pages{
	@FXML
	public void moveTo(ActionEvent event) {
		Main.switchOut(event, startpage);
	}
}
