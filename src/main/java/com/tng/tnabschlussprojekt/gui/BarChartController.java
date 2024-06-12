package com.tng.tnabschlussprojekt.gui;

import com.tng.tnabschlussprojekt.logic.FoodManager;
import com.tng.tnabschlussprojekt.model.Food;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for bar chart visualization
 */
public class BarChartController implements Initializable {

    @FXML
    private BarChart barChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series<String, Integer> seriesOne = new XYChart.Series<>();
        XYChart.Series<String, Double> seriesTwo = new XYChart.Series<>();
        seriesOne.setName("Calories");
        seriesTwo.setName("Prices");
        ObservableList<Food> foods = FoodManager.getInstance().getFoods();

        for (Food food: foods
             ) {
            seriesOne.getData().add(new XYChart.Data<>(food.getName(), food.getCalories()));
            seriesTwo.getData().add(new XYChart.Data<>(food.getName(), food.getPrice()));
        }

        barChart.getData().addAll(seriesOne,seriesTwo);
    }

    public void backToPieChart(ActionEvent actionEvent) {
        SceneManager.getInstance().switchScene("PieChart.fxml");
    }

    public void goToDashboard(ActionEvent actionEvent) {
        SceneManager.getInstance().switchScene("FoodDashboard.fxml");
    }
}
