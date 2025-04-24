module org.example.fichanewteam {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens org.example.fichanewteam to javafx.fxml;
    exports org.example.fichanewteam;
}