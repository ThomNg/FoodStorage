package com.tng.tnabschlussprojekt.gui;

import com.tng.tnabschlussprojekt.logic.FoodManager;
import com.tng.tnabschlussprojekt.model.Food;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for pie chart visualization
 */
public class PieChartController implements Initializable {

    @FXML
    private javafx.scene.chart.PieChart pieChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Food> foods = FoodManager.getInstance().getFoods();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (Food food: foods) {
            pieChartData.add(new PieChart.Data(food.getName(), food.getStock()));
        }

        pieChartData.forEach( data ->
                data.nameProperty().bind(
                        Bindings.concat(data.getName(), " amount: ",data.pieValueProperty())
                ));
        pieChart.getData().addAll(pieChartData);
    }

    public void toDashboardBtn(ActionEvent actionEvent) {
        SceneManager.getInstance().switchScene("FoodDashboard.fxml");
    }

    public void toBarChart(ActionEvent actionEvent) {
        SceneManager.getInstance().switchScene("BarChart.fxml");
    }
}
