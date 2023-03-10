package application;

import javafx.fxml.FXMLLoader;

public interface Pages {
    public static final FXMLLoader loadpage = new FXMLLoader(Hotel.class.getResource("loadpage.fxml"));
    public static final FXMLLoader terms = new FXMLLoader();
    public static final FXMLLoader registrationpage = new FXMLLoader();
    public static final FXMLLoader loginpage = new FXMLLoader();
    public static final FXMLLoader loginsuccess = new FXMLLoader();
    public static final FXMLLoader startpage = new FXMLLoader();
    public static final FXMLLoader extras = new FXMLLoader();
    public static final FXMLLoader totalbill = new FXMLLoader();
    public static final FXMLLoader roomselection = new FXMLLoader();
    public static final FXMLLoader ID = new FXMLLoader();
    public static final FXMLLoader postbill = new FXMLLoader();
    public static final FXMLLoader advancedlogin = new FXMLLoader();
    public static final FXMLLoader adminlogin = new FXMLLoader();
    public static final FXMLLoader adminpanel = new FXMLLoader();
    public static final FXMLLoader userpanel = new FXMLLoader();

    
    
}
