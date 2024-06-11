package com.tng.tnabschlussprojekt.gui;

import com.tng.tnabschlussprojekt.Main;
import com.tng.tnabschlussprojekt.logic.FoodManager;
import com.tng.tnabschlussprojekt.model.Food;
import com.tng.tnabschlussprojekt.settings.AppText;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.util.Optional;

public class FoodDetailsController {
    //region Konstanten
    // endregion

    //region Attribute
    @FXML
    private ImageView detailsFood;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField caloriesField;
    @FXML
    private TextField stockField;
    @FXML
    private TextField supplierField;
    @FXML
    private DatePicker expireDateField;

    @FXML
    private Button saveBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button editBtn;

    private Food selectedFood;

    //endregion

    //region Konstruktoren
    //endregion

    //region Methoden
    public void initialize(){
        detailsFood.setImage(new Image(String.valueOf(Main.class.getResource("mainFood.png"))));
    }

    public void setSelectedFood(Food selectedFood) {
        this.selectedFood = selectedFood;
        if(selectedFood == null) return;

        deleteBtn.setDisable(false);

        nameField.setText(selectedFood.getName());
        priceField.setText(String.valueOf(selectedFood.getPrice()));
        caloriesField.setText(String.valueOf(selectedFood.getCalories()));
        stockField.setText(String.valueOf(selectedFood.getStock()));
        supplierField.setText(selectedFood.getSupplier());
        expireDateField.setValue(selectedFood.getExpiredDate());
        setDisableTextField(true);
        saveBtn.setDisable(true);

    }

    private void setDisableTextField(boolean b) {
        nameField.setDisable(b);
        priceField.setDisable(b);
        caloriesField.setDisable(b);
        supplierField.setDisable(b);
        stockField.setDisable(b);
        expireDateField.setDisable(b);
    }

    public void backToDashboardBtn(ActionEvent actionEvent) {
        goToDashboardScene();
    }

    public void onClickEditBtn(ActionEvent actionEvent) {
        if(selectedFood != null){
            setDisableTextField(false);
            deleteBtn.setDisable(true);
            saveBtn.setDisable(false);
        }
    }

    @FXML
    public void OnClickDeleteBtn(ActionEvent actionEvent) {
        Optional<ButtonType> optional = AlertManager.getInstance().showConfirmationAlert(
                String.format(AppText.TXT_DELETE_FOOD_CONFIRMATION, selectedFood)
        );

        if (optional.isPresent() && optional.get() == ButtonType.OK) {
            FoodManager.getInstance().remove(selectedFood);
            goToDashboardScene();
        }
    }

    private void goToDashboardScene() {
        SceneManager.getInstance().switchScene("FoodDashboard.fxml");
    }

    public void OnClickSaveBtn(ActionEvent actionEvent) {
        try {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int calories = Integer.parseInt(caloriesField.getText());
            int stock = Integer.parseInt(stockField.getText());
            String supplier = supplierField.getText();
            LocalDate date = expireDateField.getValue();

            if (name.isBlank() || supplier.isBlank() || price == 0.0 || calories < 1 || stock < 1) {
                throw new Exception(AppText.INVALID_INPUTS_ALL_FIELDS_MUST_BE_FILLED);
            }
            if (selectedFood == null) {
                Food food = new Food( name, price, calories, stock, date, supplier);
                FoodManager.getInstance().add(food);
            } else {
                if(!name.equals(selectedFood.getName())) selectedFood.setName(name);
                if(!supplier.equals(selectedFood.getSupplier())) selectedFood.setSupplier(supplier);
                if(price != selectedFood.getPrice()) selectedFood.setPrice(price);
                if(calories != selectedFood.getCalories()) selectedFood.setCalories(calories);
                if(stock != selectedFood.getStock()) selectedFood.setStock(stock);
                if(date != null) selectedFood.setExpiredDate(date);
            }
            goToDashboardScene();
        } catch (NumberFormatException e) {
            AlertManager.getInstance().showErrorAlert(AppText.PLEASE_ENTER_EITHER_A_VALID_PRICE_CALORIES_OR_STOCK);
        } catch (Exception e) {
            AlertManager.getInstance().showErrorAlert(e.getMessage());
        }

    }
    //endregion
}
