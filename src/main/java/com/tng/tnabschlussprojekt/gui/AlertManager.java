package com.tng.tnabschlussprojekt.gui;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;


/**
 * A Singleton-class to manage alert messages
 */
public class AlertManager {
    //region Konstanten
    private static AlertManager instance;
    // endregion

    //region Attribute
    //endregion

    //region Konstruktoren
    private AlertManager() {

    }
    //endregion

    //region Methoden
    public static synchronized AlertManager getInstance() {
        if (instance == null) {
            instance = new AlertManager();
        }
        return instance;
    }
    public Optional<ButtonType> showConfirmationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(message);
        return alert.showAndWait();
    }

    public void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(message);
        alert.show();
    }
    //endregion
}
