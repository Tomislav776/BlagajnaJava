package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import javafx.scene.control.ButtonBar.ButtonData;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane LoginLayout;
   // private AnchorPane anchor;
    String user = null;
    String pass = null;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Blagajna");
        
        primaryStage.setOnCloseRequest(e-> {
        	e.consume();
        	zatvori();
        	});
        
        initLogin();
    }
    
    public void initLogin() {
    	
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../views/login.fxml"));
            LoginLayout = (AnchorPane) loader.load();
            
            Login controller = loader.getController();
            controller.setMainApp(this);
            
            //Pritiskom entera se unosi šifra
            controller.txt_user.setOnKeyPressed(new EventHandler<KeyEvent>()
            	    {
            	        @Override
            	        public void handle(KeyEvent ke)
            	        {
            	            if (ke.getCode().equals(KeyCode.ENTER))
            	            {
            	            	user = controller.txt_user.getText();
            	                   pass = controller.txt_pass.getText();
            	                   bazaBlagajna blagajna = new bazaBlagajna();
            	                   if(blagajna.Connect(user, pass) == true){
            	                   initRootLayout();
            	                   showPersonOverview();
            	                	
            	            }
            	        } }
            	    });
            
            //Pritiskom gumba se unosi šifra
            controller.btn_prijava.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent arg0) {
                   user = controller.txt_user.getText();
                   pass = controller.txt_pass.getText();
                   bazaBlagajna blagajna = new bazaBlagajna();
                   if(blagajna.Connect(user, pass) == true){
                   	initRootLayout();
                   	showPersonOverview();
                
                   }

                }
            });
            
            
            //Pritiskom na gumb izlaz poziva se funkcija zatvori koja otvara dijalog gdje vas pita da li ste sigurni da želite izaæi van iz aplikacije, ukoliko kliknete da jeste, aplikacija se gasi
            controller.btn_izlaz.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent arg0) {
                	zatvori();
                }
            });
           
            

            // Show the scene containing the root layout.
            Scene scene = new Scene(LoginLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            //Fokusira se na text field
            controller.txt_user.requestFocus();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../views/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../views/MainScreen.fxml"));
           /*
            TablicaGumbova ini = new TablicaGumbova();
            ini.initBtnsArray();
            ini.initialize();*/
            
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    
    public void zatvori()
    {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Izlazak iz aplikacije");
    	alert.setHeaderText(null);
    	alert.setContentText("Jeste li sigurni da želite izaæi iz aplikacije?");

    	ButtonType button1 = new ButtonType("Jesam, izaði");
    	ButtonType button2 = new ButtonType("Nisam, ostani u aplikaciji", ButtonData.CANCEL_CLOSE);

    	alert.getButtonTypes().setAll(button1, button2);

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == button1){
    		primaryStage.close();
    	}
    }

    public static void main(String[] args) {
    	//bazaBlagajna b = new bazaBlagajna();
        launch(args);
        //b.bazaIspis();
    }
}