package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	
	public  ObservableList<Artikli> artikli = FXCollections.observableArrayList();
	
	@FXML
    public GridPane grid_GumboviArtikl;
	
	@FXML
    public TableView<Artikli> tableViewRacun;
	
	@FXML
    public TableColumn<Artikli, Double> tableColumnCijena;
	
	@FXML
    public TableColumn<Artikli, String> tableColumnNaziv;

    private Button[] btns = new Button[50];

   private Main main; 
   public void setMainApp(Main main) {
        this.main = main;

    }

   	//Inicjalizira gumbove
    public void initBtnsArray() {
    	List<Artikli> artikli = new ArrayList<Artikli>();
		artikli=bazaBlagajna.bazaCitajArtikle();
		
        for(int i = 0; i < artikli.size(); i++) {
            btns[i] = new Button(artikli.get(i).getNaziv()); 
        }
    }
    
    //popunjava Grid s gumbovima
    @FXML
    public void initialize() {
    	initBtnsArray();
    	
    	//inicijalizira tablicu stupce, naziv je ime podatka u klasi Artikli
    	tableColumnNaziv.setCellValueFactory(new PropertyValueFactory<Artikli,String>("naziv"));
    	tableColumnCijena.setCellValueFactory(new PropertyValueFactory<Artikli,Double>("cijena"));
    	tableViewRacun.setItems(getArtikli(""));
    	
    	
    	List<Artikli> artikli = new ArrayList<Artikli>();
		artikli=bazaBlagajna.bazaCitajArtikle();
         
        	int k=0,i=0,j=0;
	        while (k!=artikli.size()) {    
	        	
	        		
	        		btns[k].setMinSize(149, 120);	//Poveca gumbove da popune okvir
	        		btns[k].setMaxSize(620, 500);
	        		    
	        		grid_GumboviArtikl.add(btns[k],j ,i);
	        		grid_GumboviArtikl.setHalignment(btns[k], HPos.CENTER);  //Centrira gumbove
	        		grid_GumboviArtikl.setValignment(btns[k], VPos.CENTER);
	        		
	        		
	        		//Postavlja Listenere na gumbove
	        		btns[k].setOnAction (e -> {
	        			gumbArtikliKlik(e);
	        		});
	        		
	        	++k;
	        	++j;
	        	if (j%4==0){
	        		j=0;
	        		++i;
	        	}
	        }
        }
    
    //Funkcija koja se izvodi kad se klikne na gumb
	public void gumbArtikliKlik(ActionEvent sender){
		Button btn=(Button) sender.getSource();
		String naziv=btn.getText();
		
		System.out.println(btn.getText());
		
		tableViewRacun.setItems(getArtikli(naziv));
		//getArtikli(naziv);
		//System.out.println("Provba");
	}
	
	
	//Radi observable list stavlja artikle u nju za prikaz u table view
	public ObservableList<Artikli> getArtikli(String naziv){
		
		List<Artikli> artiklii = new ArrayList<Artikli>();
		artiklii=bazaBlagajna.bazaCitajArtikle();
		
		
		for (int i =0 ;i<artiklii.size();i++){
		if (naziv.equals(artiklii.get(i).getNaziv())){
			System.out.println("wtf");
			artikli.add(artiklii.get(i));
		}
		}

		return artikli;
	}
	
    	
}
    
    
    


