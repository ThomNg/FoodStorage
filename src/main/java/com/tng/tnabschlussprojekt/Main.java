package com.tng.tnabschlussprojekt;

import com.tng.tnabschlussprojekt.gui.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.getInstance().setAndConfigureMainStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}