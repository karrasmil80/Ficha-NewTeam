module org.example.fichanewteam {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;
    requires kotlinx.serialization.core;


    opens org.example.fichanewteam to javafx.fxml;
    exports org.example.fichanewteam;
    // Controlador
    exports org.example.fichanewteam.controllers;
}