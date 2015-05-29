package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Login {

	    @FXML
	    public TextField txt_user;
	    @FXML
	    public TextField txt_pass;
	    
	    @FXML
	    public Button btn_prijava;
	    @FXML
	    private Button btn_izlaz;
	    

	    // Reference to the main application.
	    private Main main;

	    /**
	     * The constructor.
	     * The constructor is called before the initialize() method.
	     */
	    public Login() {
	    }

	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	    	
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
