module com.tng.tnabschlussprojekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.mariadb.jdbc;
    requires com.google.gson;
    requires java.net.http;
    requires org.checkerframework.checker.qual;


    opens com.tng.tnabschlussprojekt to javafx.fxml;
    exports com.tng.tnabschlussprojekt;
    exports com.tng.tnabschlussprojekt.model;
    exports com.tng.tnabschlussprojekt.gui;
    opens com.tng.tnabschlussprojekt.gui to javafx.fxml;
}