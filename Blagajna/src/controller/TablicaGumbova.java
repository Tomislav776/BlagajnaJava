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

public class TablicaGumbova{
	
	@FXML
    public GridPane grid_GumboviArtikl;
	
	//private Main main;

    private Button[] btns = new Button[14];

        
   /* public void setMainApp(Main main) {
        this.main = main;

    }*/

    public void initBtnsArray() {
        for(int i = 0; i < btns.length; i++) {
            btns[i] = new Button("Button-"+i);
           
        }
    }
    
    @FXML
    public void initialize() {
        // Initialize the person table with the two columns.
    	initBtnsArray();
    	/*for(Button b : btns) {
        grid_GumboviArtikl.add(b, 0, 0);*/
    	grid_GumboviArtikl.setHgap(2);
    	grid_GumboviArtikl.setVgap(2);
         
        int k=-1;
        for(int i = 0 ; i<3;i++) {
        	for (int j=0;j<3;j++){
        		k++;
            // do something with your button
            // maybe add an EventListener or something
        		//grid_GumboviArtikl.getChildren().add(b);
        		grid_GumboviArtikl.add(btns[k],i ,j);
        		grid_GumboviArtikl.setHalignment(btns[k], HPos.CENTER);
        		//grid_GumboviArtikl.add(btns[1],0 , 2);*/
        	}
            i++;
        }
    	
    }
}

//i*(i+(int)btns[0].getWidth())
