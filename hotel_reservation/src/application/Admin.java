package application;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.jfoenix.controls.JFXComboBox;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.*;

import application.LoginSuccess.pf10;
import application.LoginSuccess.pf15;
import application.LoginSuccess.pf20;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Admin extends DataToStringArray implements Pages, Initializable{

	@FXML
	JFXComboBox<String> activeUsers;
	@FXML
	JFXComboBox<String> permaUsers;
	@FXML
	TextField amount;
	static String discountString;
	@FXML
	Label discount;
	//	}
	ArrayList<String> discountData = new ArrayList<String>();
	@FXML
	public void goBackHome(ActionEvent event) {
		Main.switchOut(event, startpage);
	}
	@FXML
	public void gen10pf(ActionEvent event) {
		pf10 pf10obj = new pf10();
		discountString = pf10obj.discount();
		discountData.add(pf10obj.discount());
		discountData.add(pf10obj.getClass().getName());
		Discount(discountData);
		Main.switchOut(event, admindiscount);
	}
	@FXML
	public void gen15pf(ActionEvent event) {
		pf15 pf15obj = new pf15();
		discountString = pf15obj.discount();
		discountData.add(pf15obj.discount());
		discountData.add(pf15obj.getClass().getName());
		Discount(discountData);
		Main.switchOut(event, admindiscount);
	}
	@FXML
	public void gen20pf(ActionEvent event) {
		pf20 pf20obj = new pf20();
		discountString = pf20obj.discount();

		discountData.add(pf20obj.discount());
		discountData.add(pf20obj.getClass().getName());
		Discount(discountData);

		Main.switchOut(event, admindiscount);
	}

	@FXML
	public void goBackToLogin(ActionEvent event) {
		Main.switchOut(event, adminpanel);
	}
	@FXML
	public void userPanel(ActionEvent event) {
		Main.switchOut(event, userpanel);
	}
	@FXML
	public void kickUser(ActionEvent event) {

		String userInput = (activeUsers.getValue()).substring(0,(activeUsers.getValue()).indexOf(" "));
		try {
			CSVReader reader = new CSVReader(new FileReader("/Users/pvadlamani/git/repository/hotel_reservation/src/application/userData.csv"));
			List<String[]> allUserData = reader.readAll();
			ObservableList<String> activeusers = FXCollections.observableArrayList();
			for(String[] nextLine: allUserData) {

				if(nextLine[0].equals(userInput)) {
					CSVWriter adminwriter = new CSVWriter(new FileWriter("/Users/pvadlamani/git/repository/hotel_reservation/src/application/adminuserlog.csv",true));
					String[] adminLog = new String[]{adminSuccess, "kicked", nextLine[0]};
					adminwriter.writeNext(adminLog);
					adminwriter.close();

					CSVWriter writer = new CSVWriter(new FileWriter("/Users/pvadlamani/git/repository/hotel_reservation/src/application/userData.csv"));

					nextLine[0] = "CHECKED_OUT";
					writer.writeAll(allUserData);
					writer.flush();
					writer.close();


				} else if(!(nextLine[0].equals("CHECKED_OUT"))) {
					activeusers.add(nextLine[0] + " - " + nextLine[4]);
				}
			}
			activeUsers.setItems(activeusers);

		} catch (CsvValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	public void logdata(ActionEvent event) throws IOException, CsvException, URISyntaxException {
		File pdfFile = new File("/Users/pvadlamani/git/repository/hotel_reservation/src/application/log.pdf");
		PdfWriter pdf = new PdfWriter(pdfFile);
		PdfDocument pdfD = new PdfDocument(pdf);
		pdfD.addNewPage();
		Document document = new Document(pdfD);
		Paragraph header = new Paragraph("ADMIN AND USER LOG");
		document.add(header);
		CSVReader reader = new CSVReader(new FileReader(new File("/Users/pvadlamani/git/repository/hotel_reservation/src/application/adminuserlog.csv")));
		List<String[]> allUserData = reader.readAll();
		for(String [] nextLine: allUserData) {
			Paragraph newLine = new Paragraph();
			for(String i: nextLine) {
				newLine.add(i+ " ");
			}
			//
			document.add(newLine);
		}

		document.close();
		if(Desktop.isDesktopSupported()) {
			Desktop.getDesktop().browse(new URI("file:///Users/pvadlamani/git/repository/hotel_reservation/src/application/log.pdf"));
		}

	}
	@FXML
	public void addpoints(ActionEvent event) {
		String addPTS =permaUsers.getValue().substring(0,permaUsers.getValue().indexOf(" ")); 

		int pts = Integer.parseInt(amount.getText());

		try {
			CSVReader reader = new CSVReader(new FileReader("/Users/pvadlamani/git/repository/hotel_reservation/src/application/permaRecord.csv"));

			List<String[]> allUserData = reader.readAll();
			ObservableList<String> permausers = FXCollections.observableArrayList();

			for(String [] nextLine: allUserData) {
				//
				if(nextLine[0].equals(addPTS)) {
					CSVWriter adminwriter = new CSVWriter(new FileWriter("/Users/pvadlamani/git/repository/hotel_reservation/src/application/adminuserlog.csv",true));
					String[] adminLog = new String[]{adminSuccess, "changed", nextLine[0], "points from", nextLine[1], "to", String.valueOf(Double.parseDouble(nextLine[1]) + pts)};
					adminwriter.writeNext(adminLog);
					adminwriter.close();

					CSVWriter writer = new CSVWriter(new FileWriter("/Users/pvadlamani/git/repository/hotel_reservation/src/application/permaRecord.csv"));

					nextLine[1] = String.valueOf(Double.parseDouble(nextLine[1]) + pts);
					permausers.add(nextLine[0]+ " - " + nextLine[1]);
					writer.writeAll(allUserData);
					writer.flush();
					writer.close();
				} else if(!(nextLine[0].equals("CHECKED_OUT"))) {
					permausers.add(nextLine[0] + " - " + nextLine[1]);
				}
			}
			permaUsers.setItems(permausers);

		} catch (CsvValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {


			if(arg0.sameFile(new URL("file:/Users/pvadlamani/git/repository/hotel_reservation/bin/application/kickuser.fxml"))){

				ObservableList<String> activeusers = FXCollections.observableArrayList();
				ObservableList<String> permausers = FXCollections.observableArrayList();

				File file = new File("/Users/pvadlamani/git/repository/hotel_reservation/src/application/userData.csv");
				try {

					FileReader readfile = new FileReader(file);
					CSVReader read = new CSVReaderBuilder(readfile).build();
					List<String[]> allUserData = read.readAll(); 

					for(String[] nextLine : allUserData) {
						//
						if(!(nextLine[0].equals("CHECKED_OUT")) && !(nextLine[0].equals("UserID"))) {
							activeusers.add(nextLine[0] + " - " + nextLine[4] + " " + nextLine[1]+" - "+nextLine[2]);
						}
					}

					activeUsers.setItems(activeusers);
					FileReader permfile = new FileReader(new File("/Users/pvadlamani/git/repository/hotel_reservation/src/application/permaRecord.csv"));
					CSVReader permread = new CSVReaderBuilder(permfile).build();
					List<String[]> permData = permread.readAll();
					for(String[] nextLine : permData) {
						//
						if(!(nextLine[0].equals("UserID"))) {
							permausers.add(nextLine[0] + " - " + nextLine[1]);
						}
					}

					permaUsers.setItems(permausers);
				} catch(IOException e) {
					//
				} catch (CsvException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if((arg0.sameFile(new URL("file:/Users/pvadlamani/git/repository/hotel_reservation/bin/application/admindiscount.fxml")))) {
				discount.setText(discountString);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

	}

}
