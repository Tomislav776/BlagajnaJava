package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import javafx.scene.control.ButtonBar.ButtonData;

/**
 * Razred <code>Main</code> je glavni razred ove aplikacije te sadržava glavni Stage koji se prikazuje na ekranu.
 * 
 */
public class Main extends Application {

    private static Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane LoginLayout;
    private static AnchorPane MainScreen;
    private RootLayout controller;
    String user = null;
    String pass = null;

    /**
     * Metoda koju pozove launch metoda iz maina.
     * @param primaryStage Stage na koji će se postavljati layoutovi.
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Blagajna");
        
        Image icon = new Image(getClass().getResourceAsStream("../resources/images/21-128.png"));
        this.primaryStage.getIcons().add(icon);
        
        primaryStage.setOnCloseRequest(e-> {
        	e.consume();
        	zatvori();
        	});
        
        initLogin();
    }
    
    /**
     * Postavlja scenu na login layout te ju prikaže na Stageu.
     */
    public void initLogin() {
    	
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../views/login.fxml"));
            LoginLayout = (AnchorPane) loader.load();
            
            Login controller = loader.getController();
            controller.setMainApp(this);
            
            //Pritiskom entera se unosi šifra
            controller.user().setOnKeyPressed(new EventHandler<KeyEvent>()
            	    {
            	        @Override
            	        public void handle(KeyEvent ke)
            	        {
            	            if (ke.getCode().equals(KeyCode.ENTER))
            	            {
            	            	user = controller.user().getText();
            	                   pass = controller.pass().getText();
            	                   bazaBlagajna blagajna = new bazaBlagajna();
            	                   if(blagajna.Connect(user, pass) == true){
            	                   initRootLayout();
            	                   initSredisnjiLayout();
            	                	
            	            }
            	                   else
            	                   {
            	                	Alert alert = new Alert(AlertType.WARNING);
            	                   	alert.setTitle("Krivi podaci");
            	                   	alert.setHeaderText(null);
            	                   	alert.setContentText("Unijeli ste krive podatke za pristup bazi podataka.");
            	                   	alert.showAndWait();
            	                   }
            	        } }
            	    });
            
            //Pritiskom gumba se unosi šifra
            controller.prijava().setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent arg0) {
                   user = controller.user().getText();
                   pass = controller.pass().getText();
                   bazaBlagajna blagajna = new bazaBlagajna();
                   if(blagajna.Connect(user, pass) == true){
                   	initRootLayout();
                   	initSredisnjiLayout();
                
                   }
                   
                   else
                   {
                	Alert alert = new Alert(AlertType.WARNING);
                   	alert.setTitle("Krivi podaci");
                   	alert.setHeaderText(null);
                   	alert.setContentText("Unijeli ste krive podatke za pristup bazi podataka.");
                   	alert.showAndWait();
                   }

                }
            });
            
            
            //Pritiskom na gumb izlaz poziva se funkcija zatvori koja otvara dijalog gdje vas pita da li ste sigurni da želite izaæi van iz aplikacije, ukoliko kliknete da jeste, aplikacija se gasi
            controller.izlaz().setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent arg0) {
                	zatvori();
                }
            });
           
            


            Scene scene = new Scene(LoginLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            //Fokusira se na text field
            controller.user().requestFocus();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Postavlja scenu na RootLayout te ju prikaže na Stageu.
     */
    public void initRootLayout() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../views/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            controller = loader.getController();
            controller.setMainApp(this);
            
            controller.hrvatski().setOnAction(e->
            {
            	controller.loadLang("hr");
            	refreshMainScreen1();
            });
            
            controller.engleski().setOnAction(e->
            {
            	controller.loadLang("en");
            	refreshMainScreen1();
            });
            
            primaryStage.setMinWidth(1120);
            primaryStage.setMinHeight(808);
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Postavlja MainScreen layout u centralni dio RootLayout BorderPanea.
     */
    public void initSredisnjiLayout() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../views/MainScreen.fxml"));
            
            MainScreen = (AnchorPane) loader.load();

            rootLayout.setCenter(MainScreen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	

    /**
     * Vraća glavni Stage.
     * @return primaryStage To je glavni Stage.
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    
    /**
     * Otvara alert dijalog gdje pita korisnika da li stvarno hoće izaći iz aplikacije,
     * ako korisnik odabere da je siguran onda izlazi iz aplikacije, inaće ostaje u njoj.
     */
    public void zatvori()
    {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Izlazak iz aplikacije");
    	alert.setHeaderText(null);
    	alert.setContentText("Jeste li sigurni da želite izaći iz aplikacije?");

    	ButtonType button1 = new ButtonType("Jesam, izađi");
    	ButtonType button2 = new ButtonType("Nisam, ostani u aplikaciji", ButtonData.CANCEL_CLOSE);

    	alert.getButtonTypes().setAll(button1, button2);

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == button1){
    		primaryStage.close();
    	}
    }
    
    /**
     * Refresha RootLayout i MainScreenLayoutove.
     */
    public void refreshMainScreen(){
    	initRootLayout();
    	initSredisnjiLayout();
    }
    
    /**
     * Refresha MainScreenLayout (poziva se nakon dodavanja artikla i/ili konobara).
     */
    public void refreshMainScreen1(){
    	initSredisnjiLayout();
    }

    public static void main(String[] args) {
        launch(args);
    }
}