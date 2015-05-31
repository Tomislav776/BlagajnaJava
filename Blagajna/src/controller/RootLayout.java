package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class RootLayout implements Initializable{
	
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
	
	@FXML
	public Menu jezik;
	
	@FXML
	public ToggleGroup radioGroup1;
	
	@FXML
	public RadioMenuItem hrvatski;
	
	@FXML
	public RadioMenuItem engleski;
	
	@FXML
	public Menu opcije;
	
	@FXML
	public Menu aplikacija;
	
	private String naziv;
	private String kolicina;
	
	private static ResourceBundle bundle;
	private static Locale locale;
	
	
	// Reference to the main application.
    private Main main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public RootLayout() {
    }
    
    public static Locale getLocale()
    {
    	return locale;
    }
    
    public static ResourceBundle getBundle()
    {
    	return bundle;
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
            window.setTitle(bundle.getString("dodaj_artikl"));

            
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
            window.setTitle(bundle.getString("dodaj_konobara"));

            
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
            window.setTitle(bundle.getString("obracun"));

            
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
       
       loadLang("hr");
       
       hrvatski.setOnAction(e->
       {
    	   loadLang("hr");
       });
       
       engleski.setOnAction(e->
       {
    	   loadLang("en");
       });
    }
    
    private void loadLang(String lang)
    {
    	locale = new Locale(lang);
    	bundle = ResourceBundle.getBundle("lang.lang", locale);
    	dodaj_artikl.setText(bundle.getString("dodaj_artikl"));
    	opcije.setText(bundle.getString("opcije"));
    	aplikacija.setText(bundle.getString("aplikacija"));
    	o_nama.setText(bundle.getString("o_nama"));
    	izlaz.setText(bundle.getString("izlaz"));
    	jezik.setText(bundle.getString("jezik"));
    	hrvatski.setText(bundle.getString("hrvatski"));
    	engleski.setText(bundle.getString("engleski"));
    	dodaj_konobar.setText(bundle.getString("dodaj_konobara"));
    	obracun.setText(bundle.getString("obracun"));
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
