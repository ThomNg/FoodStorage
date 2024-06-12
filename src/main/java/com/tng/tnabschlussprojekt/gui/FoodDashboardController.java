package com.tng.tnabschlussprojekt.gui;

import com.google.gson.Gson;
import com.tng.tnabschlussprojekt.Main;
import com.tng.tnabschlussprojekt.listview.FoodCellFactory;
import com.tng.tnabschlussprojekt.logic.FoodManager;
import com.tng.tnabschlussprojekt.model.Food;
import com.tng.tnabschlussprojekt.settings.AppText;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;

/**
 * Food Dashboard Controller
 * where we set an image, implement a clock and set the food's ListView
 */
public class FoodDashboardController {

    @FXML
    public ComboBox<String> cityComboBox;
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
                clockLabel.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yy"))+" "+ currentTime);
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
        cityComboBox.getItems().addAll(AppText.CITY_AACHEN, AppText.CITY_BREMEN, AppText.CITY_BERLIN, AppText.CITY_LEIPZIG, AppText.CITY_COLOGNE);
        cityComboBox.getSelectionModel().selectFirst();
        try {
            onSelectComboBox();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void goToDetailsWithSelectedFood() {
        Food food = listView.getSelectionModel().getSelectedItem();
        SceneManager.getInstance().switchToDetailWithFood(food);
    }

    @FXML
    private void onSortNameBtn() {
        ObservableList<Food> listTiers = FoodManager.getInstance().getFoods();
        listTiers.sort(Comparator.comparing(o -> o.getName().toLowerCase()));
    }
    @FXML
    private void onSortPriceBtn() {
        ObservableList<Food> listTiers = FoodManager.getInstance().getFoods();
        listTiers.sort(Comparator.comparing(Food::getPrice));
    }

    @FXML
    public void onSortCalorieBtn() {
        ObservableList<Food> listTiers = FoodManager.getInstance().getFoods();
        listTiers.sort(Comparator.comparing(Food::getCalories));
    }
    @FXML
    public void OnSortStockBtn() {
        ObservableList<Food> listTiers = FoodManager.getInstance().getFoods();
        listTiers.sort(Comparator.comparing(Food::getStock));
    }
    @FXML
    public void OnSortSupplierBtn() {
        ObservableList<Food> listTiers = FoodManager.getInstance().getFoods();
        listTiers.sort(Comparator.comparing(o -> o.getSupplier().toLowerCase()));
    }
    @FXML
    public void OnSortExpireBtn() {
        ObservableList<Food> listTiers = FoodManager.getInstance().getFoods();
        listTiers.sort(Comparator.comparing(Food::getExpiredDate));
    }
    @FXML
    private void OnClickAddFoodBtn() {
        SceneManager.getInstance().switchToDetailWithFood(null);
    }

    @FXML
    public void onSelectComboBox() throws IOException, InterruptedException {
        String selectedCity = cityComboBox.getValue();
        weatherLabel.setText(executeWeatherCall(selectedCity));
    }

    @FXML
    private static String executeWeatherCall(String selectedCity) throws IOException, InterruptedException {
        Gson gson = new Gson();
        CityTemperature temperature;

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openweathermap.org/data/2.5/weather?q="+ selectedCity
                        + "&appid=" + AppText.API_KEY + "&units=metric"))
                .GET()
                .header("Authorization", AppText.API_KEY).build();

        HttpResponse<String> getResponse;
        try (HttpClient client = HttpClient.newHttpClient()) {
            getResponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        }
        temperature = gson.fromJson(getResponse.body(), CityTemperature.class);
        return temperature.getMain().getTemp() +" °C, "+temperature.getName();
    }

    public void OnClickPieChart() {
        SceneManager.getInstance().switchScene("PieChart.fxml");
    }

}