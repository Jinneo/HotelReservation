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
	requires org.slf4j;
	requires java.xml;
	requires kernel;
	requires layout;
	
	opens application to javafx.graphics, javafx.fxml;
}
