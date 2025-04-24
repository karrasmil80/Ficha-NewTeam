module org.example.fichanewteam {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlinx.serialization.core;


    opens org.example.fichanewteam to javafx.fxml;
    exports org.example.fichanewteam;
}