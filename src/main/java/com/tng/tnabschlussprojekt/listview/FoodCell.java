package com.tng.tnabschlussprojekt.listview;

import com.tng.tnabschlussprojekt.model.Food;
import javafx.scene.control.ListCell;
import javafx.scene.text.Font;
import java.time.format.DateTimeFormatter;

/**
 * class to transform ListView into ListCell
 */
public class FoodCell extends ListCell<Food> {
    @Override
    protected void updateItem(Food food, boolean isEmpty) {
        super.updateItem(food, isEmpty);
        if(isEmpty && food == null){
            setText(null);
            setGraphic(null);
        }else{
            setText(String.format(("%-15s %10.2f€ %10d Cal %10d-items %15s %15s"),food.getName(),food.getPrice(),food.getCalories(), food.getStock(),
                    food.getExpiredDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),food.getSupplier()));
            setFont(Font.font("Consolas"));
        }
    }
}
