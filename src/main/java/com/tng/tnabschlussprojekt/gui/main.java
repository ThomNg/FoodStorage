package com.tng.tnabschlussprojekt.gui;

/**
 * another dummy class used in CityTemperature class
 * to fetch city temperature, feels_like, temp_min, temp max, etc.
 */
public class main {
    //region Konstanten
    // endregion

    //region Attribute
    public double temp;
    public double feels_like;
    public double temp_min;
    public double temp_max;
    //endregion

    //region Konstruktoren
    //endregion

    //region Methoden

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(double feels_like) {
        this.feels_like = feels_like;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    //endregion
}
