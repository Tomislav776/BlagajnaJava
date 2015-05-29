package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RootLayout {
	
	@FXML
	public MenuItem dodaj_artikl;
	
	private String naziv;
	private String kolicina;
	
	
	// Reference to the main application.
    private Main main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public RootLayout() {
    }
    

    public void display()
    {
    	try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../views/Dodaj_artikl.fxml"));
            AnchorPane anchor = (AnchorPane) loader.load();
            
            /*Dodaj_artikl controller = loader.getController();
            controller.btn_unesi_artikl.setOnAction(e->
            {
            	naziv = controller.txt_naziv.getText();
            	kolicina = controller.txt_kolicina.getText();
            	bazaBlagajna b = new bazaBlagajna();
            	b.dodaj_artikl(naziv, kolicina);
            });*/
            
            
            
            /*controller.btn_unesi_artikl.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent arg0) {
                	naziv = controller.txt_naziv.getText();
                	kolicina = controller.txt_kolicina.getText();
                	bazaBlagajna b = new bazaBlagajna();
                	b.dodaj_artikl(naziv, kolicina);
                }
            });*/
            
            
            
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Dodaj artikl");

            
            Scene scene = new Scene(anchor);
            window.setScene(scene);
            window.showAndWait();
      
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
       dodaj_artikl.setOnAction(e -> {
    	   display();
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
