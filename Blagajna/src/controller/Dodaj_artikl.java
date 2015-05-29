package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class Dodaj_artikl {
	@FXML
	public TextField txt_naziv;
	@FXML
	public TextField txt_kolicina;
	@FXML
	public TextField txt_cijena;
	@FXML
	public Button btn_unesi_artikl;

	private String naziv;
	private String kolicina;
	private String cijena;
	
	// Reference to the main application.
    private Main main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public Dodaj_artikl() {
    }
    

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	/*btn_unesi_artikl.setOnAction(e -> {
    		naziv = txt_naziv.getText();
        	kolicina = txt_kolicina.getText();
        	bazaBlagajna b = new bazaBlagajna();
        	b.dodaj_artikl(naziv, kolicina);
     	   });*/
    	/*btn_unesi_artikl.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
            	naziv = txt_naziv.getText();
            	kolicina = txt_kolicina.getText();
            	bazaBlagajna b = new bazaBlagajna();
            	b.dodaj_artikl(naziv, kolicina);

            }
        });*/
    }
    
    	
    	public void handleClickDodaj()
    	{
    		naziv = txt_naziv.getText();
        	kolicina = txt_kolicina.getText();
        	cijena = txt_cijena.getText();
        	int kolicina_INT = Integer.parseInt(kolicina);
        	double cijena_Double = Double.parseDouble(cijena);
        	bazaBlagajna b = new bazaBlagajna();
        	if(b.dodaj_artikl(naziv, kolicina_INT, cijena_Double) == true)
        	{
        		
        	}
        	
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
