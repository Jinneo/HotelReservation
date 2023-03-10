package application;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UID extends DataToStringArray implements Initializable, Pages{
	@FXML
	public Label UID;
	@FXML
	public AnchorPane Anchorpane;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		UID.setText(Data.get(0));
		UID.setMaxWidth(Double.MAX_VALUE);
		UID.setAlignment(Pos.CENTER);
		AnchorPane.setLeftAnchor(UID, 0.0);
		AnchorPane.setRightAnchor(UID, 0.0);
	}
	@FXML
	public void returnHome(ActionEvent event) throws IOException {
		
		Main.switchOut(event, startpage);
	    super.DTS(Data);
	}
	
}
