package com.tng.tnabschlussprojekt.listview;

import com.tng.tnabschlussprojekt.model.Food;
import javafx.scene.control.ListCell;
import javafx.scene.text.Font;

import java.time.format.DateTimeFormatter;

public class FoodCell extends ListCell<Food> {
    @Override
    protected void updateItem(Food food, boolean isEmpty) {
        super.updateItem(food, isEmpty);
        if(isEmpty && food == null){
            setText(null);
            setGraphic(null);
        }else{
            setText(String.format(("%-10s %5.2f€ %5d Cal %5d-items %15s %15s"),food.getName(),food.getPrice(),food.getCalories(), food.getStock(),
                    food.getExpiredDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),food.getSupplier()));
            setFont(Font.font("Consolas"));
        }
    }
}
