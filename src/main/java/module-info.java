module org.example.fichanewteam {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jdbi.v3.sqlobject.kotlin;
    requires org.jdbi.v3.sqlobject;
    requires logging.jvm;
    requires kotlin.result.jvm;
    requires org.jdbi.v3.kotlin;
    requires org.jdbi.v3.core;
    requires com.github.benmanes.caffeine;
    requires kotlinx.serialization.json;


    opens org.example.fichanewteam to javafx.fxml;
    exports org.example.fichanewteam;
}