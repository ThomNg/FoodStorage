package com.tng.tnabschlussprojekt.gui;


import com.tng.tnabschlussprojekt.Main;
import com.tng.tnabschlussprojekt.model.Food;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A Singleton-class to manage the scenes
 */
public class SceneManager {
    //region Konstanten
    private static SceneManager instance;
    private Stage mainStage;
    // endregion

    //region Attribute
    //endregion

    //region Konstruktoren
    private SceneManager() {

    }
    //endregion

    //region Methoden
    public static synchronized SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public void setAndConfigureMainStage(Stage stage) {
        mainStage = stage;
        mainStage.setTitle("Welcome!");
        switchScene("FoodDashboard.fxml");
    }

    public void switchScene(String resourceFilePath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(resourceFilePath));
            Scene scene = new Scene(fxmlLoader.load());
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToDetailWithFood(Food selectedFood) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FoodDetails.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            FoodDetailsController controller = fxmlLoader.getController();
            controller.setSelectedFood(selectedFood);

            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion
}
