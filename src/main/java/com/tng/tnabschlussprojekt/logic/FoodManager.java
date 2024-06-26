package com.tng.tnabschlussprojekt.logic;


import com.tng.tnabschlussprojekt.logic.db.DBManager;
import com.tng.tnabschlussprojekt.model.Food;
import com.tng.tnabschlussprojekt.test.TestData;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;


/**
 * A Singleton-class to manage food,
 * where in this class we have Observable list of food
 */
public class FoodManager {
    //region Konstanten
    private static FoodManager instance;
    private ObservableList<Food> foods;
    // endregion

    //region Attribute
    //endregion

    //region Konstruktoren
    private FoodManager() {

        foods = FXCollections.observableList(DBManager.getInstance().fetchAll(), food -> new Observable[]{
                food.nameProperty(), food.priceProperty(), food.caloriesProperty(), food.stockProperty(), food.expiredDateProperty(), food.supplierProperty()
        });

        foods.addListener((ListChangeListener<Food>) change -> {
            while(change.next()){
                if(change.wasUpdated()) {
                    System.out.println("Updated "+ change.wasUpdated());
                    Food food = change.getList().get(change.getFrom());
                    DBManager.getInstance().update(food);
                }
                else if(change.wasAdded()){
                    System.out.println("added "+ change.getFrom());
                    Food food = change.getAddedSubList().getFirst();
                    DBManager.getInstance().insert(food);
                }else if(change.wasRemoved()){
                    System.out.println("removed "+ change.getRemoved());
                    Food food = change.getRemoved().getFirst();
                    DBManager.getInstance().delete(food);
                }
            }
        });
    }
    //endregion

    //region Methoden
    public static synchronized FoodManager getInstance() {
        if (instance == null) {
            instance = new FoodManager();
        }
        return instance;
    }
    public void add(Food food){foods.add(food);}
    public void remove(Food food){foods.remove(food);}
    public void replace(Food food){foods.set(foods.indexOf(food),food);}

    public ObservableList<Food> filterBy(String s){
        return foods.filtered(food -> food.getName().contains(s)
                || String.valueOf(food.getCalories()).contains(s)
                || String.valueOf(food.getPrice()).contains(s)
                || food.getExpiredDate().toString().contains(s)
                || food.getSupplier().contains(s));
    }
    public ObservableList<Food> getFoods(){return  foods;}
    //endregion
}
