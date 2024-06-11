package com.tng.tnabschlussprojekt.listview;

import com.tng.tnabschlussprojekt.model.Food;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class FoodCellFactory implements Callback<ListView<Food>, ListCell<Food>> {
    @Override
    public ListCell<Food> call(ListView<Food> param) {
        return new FoodCell();
    }
}
