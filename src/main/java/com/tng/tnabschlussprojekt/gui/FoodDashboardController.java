package com.tng.tnabschlussprojekt.gui;

import com.google.gson.Gson;
import com.tng.tnabschlussprojekt.Main;
import com.tng.tnabschlussprojekt.listview.FoodCellFactory;
import com.tng.tnabschlussprojekt.logic.FoodManager;
import com.tng.tnabschlussprojekt.model.Food;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class FoodDashboardController {

    @FXML
    public ComboBox cityComboBox;
    @FXML
    private Label weatherLabel;
    @FXML
    private Label clockLabel;
    @FXML
    private ListView<Food> listView;
    @FXML
    private TextField searchTextField;
    @FXML
    private ImageView foodIcon;

    public void initialize() {
        // set image
        foodIcon.setImage(new Image(String.valueOf(Main.class.getResource("foods.png"))));

        // set each listView item with cell
        listView.setCellFactory(new FoodCellFactory());
        listView.setItems(FoodManager.getInstance().getFoods());

        // clock feature
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String currentTime = sdf.format(new Date());
                clockLabel.setText( currentTime);
            }
        };
        timer.start();

        // double click event to select food and routing to details view
        listView.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2) {
                goToDetailsWithSelectedFood();
            }
        });

        // searchbar
        searchTextField.textProperty().addListener((_, _, newV) ->
                listView.setItems(FoodManager.getInstance().filterBy(newV)));

        // weather api
        cityComboBox.getItems().addAll("Aachen","Bremen","Berlin","Leipzig");




    }

    private void goToDetailsWithSelectedFood() {
        Food food = listView.getSelectionModel().getSelectedItem();
        SceneManager.getInstance().switchToDetailWithFood(food);
    }

    @FXML
    private void onSortNameBtn(ActionEvent actionEvent) {
        ObservableList<Food> listTiers = FoodManager.getInstance().getFoods();
        listTiers.sort(Comparator.comparing(o -> o.getName().toLowerCase()));
    }
    @FXML
    private void onSortPriceBtn(ActionEvent actionEvent) {
        ObservableList<Food> listTiers = FoodManager.getInstance().getFoods();
        listTiers.sort(Comparator.comparing(Food::getPrice));
    }

    @FXML
    public void onSortCalorieBtn(ActionEvent actionEvent) {
        ObservableList<Food> listTiers = FoodManager.getInstance().getFoods();
        listTiers.sort(Comparator.comparing(Food::getCalories));
    }
    @FXML
    public void OnSortStockBtn(ActionEvent actionEvent) {
        ObservableList<Food> listTiers = FoodManager.getInstance().getFoods();
        listTiers.sort(Comparator.comparing(Food::getStock));
    }
    @FXML
    public void OnSortSupplierBtn(ActionEvent actionEvent) {
        ObservableList<Food> listTiers = FoodManager.getInstance().getFoods();
        listTiers.sort(Comparator.comparing(o -> o.getSupplier().toLowerCase()));
    }
    @FXML
    public void OnSortExpireBtn(ActionEvent actionEvent) {
        ObservableList<Food> listTiers = FoodManager.getInstance().getFoods();
        listTiers.sort(Comparator.comparing(Food::getExpiredDate));
    }
    @FXML
    private void OnClickAddFoodBtn(ActionEvent actionEvent) {
        SceneManager.getInstance().switchToDetailWithFood(null);
    }

    @FXML
    public void onSelectComboBox(ActionEvent actionEvent) throws IOException, InterruptedException {
        String selectedCity = (String) cityComboBox.getValue();
        weatherLabel.setText(executeWeatherCall(selectedCity));
    }

    @FXML
    private static String executeWeatherCall(String selectedCity) throws IOException, InterruptedException {
        Gson gson = new Gson();
        CityTemperature temperature = new CityTemperature();

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openweathermap.org/data/2.5/weather?q="+ selectedCity
                        +"&appid=06eb6c65be82d246e786e4b4565bd64a&units=metric"))
                .GET()
                .header("Authorization","06eb6c65be82d246e786e4b4565bd64a").build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        //System.out.println(getResponse.body());

        temperature = gson.fromJson(getResponse.body(), CityTemperature.class);

        return temperature.getMain().getTemp() +" C, "+temperature.getName();
    }
}