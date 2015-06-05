package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

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

/**
 * Razred <code>RootLayout</code> postavlja i inicijalizira glavni layout (BorderPane) na glavni zaslon u kojega je onda kasnije u njegovu
 * sredinu dodan MainScreen layout. Na vrh je dodan Menu.
 */
public class RootLayout implements Initializable{
	
	@FXML
	private MenuItem dodaj_artikl;
	
	@FXML
	private MenuItem dodaj_konobar;
	
	@FXML
	private MenuItem obracun;
	
	@FXML
	private MenuItem postavke;
	
	@FXML
	private MenuItem o_nama;
	
	@FXML
	private MenuItem izlaz;
	
	@FXML
	private Menu jezik;
	
	@FXML
	private ToggleGroup radioGroup1;
	
	@FXML
	private RadioMenuItem hrvatski;
	
	@FXML
	private RadioMenuItem engleski;
	
	@FXML
	private Menu opcije;
	
	@FXML
	private Menu aplikacija;
	
	private String naziv;
	private String kolicina;
	
	private static ResourceBundle bundle;
	private static Locale locale;
	
	
	// Reference to the main application.
    private Main main;

    /**
     * Inicijalizira controller klasu. Ova se metoda automatski poziva nakon
     * što se uèita fxml datoteka.
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
    
    /**
     * Otvara dijalog za dodavanje novih artikala u bazu.
     */
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
    
    /**
     * Otvara dijalog za dodavanje novih konobara u bazu.
     */
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
    
    /**
     * Otvara dijalog za napraviti obraèun i grafièki prikaz tog obraèuna za neko razdoblje.
     */
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
    
    /**
     * Otvara dijalog koji sadrži informacije o autorima aplikacije.
     */
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
     * Otvara dijalog u kojem korisnik može birati odreðene postavke za aplikaciju.
     */
    public void displayPostavke()
    {
    	try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../views/Postavke.fxml"));
            AnchorPane anchor = (AnchorPane) loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Postavke");

            
            Scene scene = new Scene(anchor);
            window.setScene(scene);
            window.showAndWait();
      
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Inicijalizira controller klasu. Ova se metoda automatski poziva nakon
     * što se uèita fxml datoteka.
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
       
       postavke.setOnAction(e -> {
    	   displayPostavke();
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
    
    /**
     * Postavlja jezik aplikacije.
     * @param lang String koji odreðuje jezik aplikacije, može biti "hr" ili "en".
     */
    public void loadLang(String lang)
    {
    	locale = new Locale(lang);
    	bundle = ResourceBundle.getBundle("lang.lang", locale);
    	dodaj_artikl.setText(bundle.getString("dodaj_artikl"));
    	opcije.setText(bundle.getString("opcije"));
    	aplikacija.setText(bundle.getString("aplikacija"));
    	o_nama.setText(bundle.getString("o_nama"));
    	postavke.setText(bundle.getString("postavke"));
    	izlaz.setText(bundle.getString("izlaz"));
    	jezik.setText(bundle.getString("jezik"));
    	hrvatski.setText(bundle.getString("hrvatski"));
    	engleski.setText(bundle.getString("engleski"));
    	dodaj_konobar.setText(bundle.getString("dodaj_konobara"));
    	obracun.setText(bundle.getString("obracun"));
    }

    /**
     * Poziva se iz Maina da bi se referencirao na samog sebe.
     * @param main
     */
    public void setMainApp(Main main) {
        this.main = main;

    }
    public RadioMenuItem hrvatski()
    {
    	return hrvatski;
    }
    
    public RadioMenuItem engleski()
    {
    	return engleski;
    }
    

}
