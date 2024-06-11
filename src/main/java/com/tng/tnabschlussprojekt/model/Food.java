package com.tng.tnabschlussprojekt.model;


import javafx.beans.property.*;

import java.time.LocalDate;

public class Food {
    //region Konstanten
    // endregion

    //region Attribute
    private int id;
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty calories;
    private IntegerProperty stock;
    private ObjectProperty<LocalDate> expiredDate;
    private StringProperty supplier;

    //endregion

    //region Konstruktoren
    public Food(String name, double price, int calories, int stock,
                LocalDate expiredDate, String supplier) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.calories = new SimpleIntegerProperty(calories);
        this.stock = new SimpleIntegerProperty(stock);
        this.expiredDate = new SimpleObjectProperty<>(expiredDate);
        this.supplier = new SimpleStringProperty(supplier);
    }
    //endregion

    //region Methoden

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getCalories() {
        return calories.get();
    }

    public IntegerProperty caloriesProperty() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories.set(calories);
    }

    public int getStock() {
        return stock.get();
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public LocalDate getExpiredDate() {
        return expiredDate.get();
    }

    public ObjectProperty<LocalDate> expiredDateProperty() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate.set(expiredDate);
    }

    public String getSupplier() {
        return supplier.get();
    }

    public StringProperty supplierProperty() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier.set(supplier);
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name=" + name +
                ", price=" + price +
                ", calories=" + calories +
                ", stock=" + stock +
                ", expiredDate=" + expiredDate +
                ", supplier=" + supplier +
                '}';
    }
//endregion
}
