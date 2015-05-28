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
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane LoginLayout;
    String user = null;
    String pass = null;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Blagajna");

        initLogin();
        /*initRootLayout();

        showPersonOverview();*/
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
            	                   System.out.println(blagajna.Connect(user, pass));
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
                   System.out.println(blagajna.Connect(user, pass));
                   if(blagajna.Connect(user, pass) == true){
                   	initRootLayout();
                   	showPersonOverview();
                   }
                   System.out.println(user);
                   System.out.println(pass);
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

    public static void main(String[] args) {
    	//bazaBlagajna b = new bazaBlagajna();
        launch(args);
        //b.bazaIspis();
    }
}