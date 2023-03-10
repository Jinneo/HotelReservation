module hotel_reservation {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.graphics;
	requires com.opencsv;
	requires com.jfoenix;
	requires org.apache.commons.lang3;
	requires javafx.base;
	requires org.joda.time;
	
	opens application to javafx.graphics, javafx.fxml;
}
