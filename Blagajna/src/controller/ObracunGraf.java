package controller;

import java.util.Arrays;
import java.util.List;
import dataClass.Promet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

public class ObracunGraf {
	@FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> razdoblja = FXCollections.observableArrayList();

    /**
     * Inicijalizira controller klasu. Ova se metoda automatski poziva nakon
     * �to se u�ita fxml datoteka.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the promet to show the statistics for.
     * @param prometi Lista prometa od kojih �elimo napraviti grafi�ki prikaz.
     */
    public void setPrometPodaci(List<Promet> prometi) {
    	
    	String[] razdobljaString = new String[prometi.size()];
    	for (int i = 0; i < prometi.size(); i++) {
    		razdobljaString[i] = prometi.get(i).getRazdoblje();
        }
    	
    	razdoblja.addAll(Arrays.asList(razdobljaString));
    	xAxis.setCategories(razdoblja);
    	
        int[] IznosPrometaURazdoblju = new int[prometi.size()];
        
        for (int i = 0; i < prometi.size(); i++) {
            IznosPrometaURazdoblju[i] = (int)prometi.get(i).getIznos_prometa();
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Create a XYChart.Data object for each promet. Add it to the series.
        for (int i = 0; i < razdobljaString.length; i++) {
            series.getData().add(new XYChart.Data<>(razdoblja.get(i), IznosPrometaURazdoblju[i]));
        }

        barChart.getData().add(series);
    }
}
