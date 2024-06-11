package com.tng.tnabschlussprojekt.test;

import com.tng.tnabschlussprojekt.model.Food;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Temporary class, to test the progress of the current implementation
 */
public class TestData {
    //region Konstanten
    // endregion

    //region Attribute
    //endregion

    //region Konstruktoren
    private TestData(){

    }
    //endregion

    //region Methoden
    public static List<Food> getFoods(){
        List<Food> foods = new ArrayList<>();
        foods.add(new Food("Brocolli",1.99, 10, 32, LocalDate.of(2021, 3,23),"Aldi"));
        foods.add(new Food("Carrots",4.99, 198, 15, LocalDate.of(2013,12,23),"Lidl"));
        foods.add(new Food("Butter",0.99, 18, 15, LocalDate.of(2000,1,23),"Kaufland"));
        foods.add(new Food("Mayonaise",1.99, 18, 53, LocalDate.of(2000,1,23),"REWE"));
        foods.add(new Food("Red Wine",8.99, 18, 41, LocalDate.of(2000,1,23),"NORMA"));

        return foods;
    }
    //endregion
}
