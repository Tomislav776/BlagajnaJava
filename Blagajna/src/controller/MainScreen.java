package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainScreen{
	
	@FXML
    public GridPane grid_GumboviArtikl;

    private Button[] btns = new Button[50];

   private Main main; 
   public void setMainApp(Main main) {
        this.main = main;

    }

   	//Inicjalizira gumbove
    public void initBtnsArray() {
        for(int i = 0; i < btns.length; i++) {
            btns[i] = new Button("Button-"+i);
           
        }
    }
    
    //popunjava Grid s gumbovima
    @FXML
    public void initialize() {
    	initBtnsArray();
    	
         
        int k=-1;
        for(int i = 0 ; i<4;i++) {
        	for (int j=0;j<4;j++){
        		k++;
        		grid_GumboviArtikl.add(btns[k],i ,j);
        		grid_GumboviArtikl.setHalignment(btns[k], HPos.CENTER);  //Centrira gumbove
        		
        		
        	}
        }
    	
    }
    
    
    
}

