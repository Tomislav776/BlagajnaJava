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
import javafx.scene.layout.VBox;

public class RootLayout {
	
	@FXML
	public MenuItem dodaj_artikl;
	
	@FXML
	public MenuItem dodaj_konobar;
	
	@FXML
	public MenuItem obracun;
	
	@FXML
	public MenuItem o_nama;
	
	@FXML
	public MenuItem izlaz;
	
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
            VBox vbox = (VBox) loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Dodaj artikl");

            
            Scene scene = new Scene(vbox);
            window.setScene(scene);
            window.showAndWait();
      
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void displayKonobar()
    {
    	try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../views/Dodaj_konobar.fxml"));
            VBox vbox = (VBox) loader.load();
                    
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Dodaj konobara");

            
            Scene scene = new Scene(vbox);
            window.setScene(scene);
            window.showAndWait();
      
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void displayObracun()
    {
    	try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../views/Obracun.fxml"));
            VBox vbox = (VBox) loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("O aplikaciji");

            
            Scene scene = new Scene(vbox);
            window.setScene(scene);
            window.showAndWait();
      
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void displayONama()
    {
    	try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../views/O_app.fxml"));
            AnchorPane anchor = (AnchorPane) loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("O aplikaciji");

            
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
       
       dodaj_konobar.setOnAction(e -> {
    	   displayKonobar();
    	   });
       
       obracun.setOnAction(e -> {
    	   displayObracun();
    	   });
       
       o_nama.setOnAction(e -> {
    	   displayONama();
    	   });
       
       izlaz.setOnAction(e -> {
    	   main.zatvori();
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
