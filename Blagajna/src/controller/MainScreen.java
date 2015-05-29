package controller;

import dataClass.Artikli;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class MainScreen{
	
	@FXML
    public GridPane grid_GumboviArtikl;
	
	@FXML
    public TableView<Artikli> tableViewRacun;
	
	@FXML
    public TableColumn tableColumnCijena;
	
	@FXML
    public TableColumn<Artikli, String> tableColumnNaziv;

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
    	
    	//inicijalizira tablicu stupce, naziv je ime podatka u klasi Artikli
    	tableColumnNaziv.setCellValueFactory(new PropertyValueFactory<Artikli,String>("naziv"));
    	tableViewRacun.setItems(getArtikli());
    	
    	
    	
         
        int k=-1;
        for(int i = 0 ; i<4;i++) {    
        	for (int j=0;j<4;j++){
        		k++;
        		btns[k].setMinSize(149, 120);	//Poveca gumbove da popune okvir
        		btns[k].setMaxSize(620, 500);
        		    
        		grid_GumboviArtikl.add(btns[k],j ,i);
        		grid_GumboviArtikl.setHalignment(btns[k], HPos.CENTER);  //Centrira gumbove
        		grid_GumboviArtikl.setValignment(btns[k], VPos.CENTER);
        		
        		//grid_GumboviArtikl.setHgrow(btns[k], Priority.ALWAYS); 
        		//grid_GumboviArtikl.setVgrow(btns[k], Priority.ALWAYS);
        		
        		//Postavlja Listenere na gumbove
        		btns[k].setOnAction (e -> {
        			gumbArtikliKlik(e);
        		});
        		}
        	}
        }
    
    //Funkcija koja se izvodi kad se klikne na gumb
	public void gumbArtikliKlik(ActionEvent sender){
		Button btn=(Button) sender.getSource();
		
		
		//System.out.println("Provba");
	}
	
	
	//Radi observable list stavlja artikle u nju za prikaz u table view
	public ObservableList<Artikli> getArtikli(){
		ObservableList<Artikli> artikli = FXCollections.observableArrayList();
		
		//for (int i=0;i<2;i++)
		artikli.add(new Artikli("Netko",2));
		artikli.add(new Artikli("Netko2",3));
		artikli.add(new Artikli("Netko3",4));
		
		return artikli;
	}
	
    	
}
    
    
    


