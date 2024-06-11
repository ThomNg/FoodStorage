package com.tng.tnabschlussprojekt.gui;

/**
 * Dummy class to parse JSON object response
 */
public class CityTemperature {
    //region Konstanten
    // endregion

    //region Attribute
    public String name;
    public main main;
    //endregion

    //region Konstruktoren
    //endregion

    //region Methoden
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public com.tng.tnabschlussprojekt.gui.main getMain() {
        return main;
    }

    public void setMain(com.tng.tnabschlussprojekt.gui.main main) {
        this.main = main;
    }
    //endregion
}
