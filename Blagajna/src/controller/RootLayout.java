package controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class RootLayout {
	
	@FXML
	public MenuItem dodaj_artikl;
	
	
	
	
	// Reference to the main application.
    private Main main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public RootLayout() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
       dodaj_artikl.setOnAction(e -> {
    	   System.out.println("Dodan artikl");
    	   });
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Main main) {
        this.main = main;

    }

}
