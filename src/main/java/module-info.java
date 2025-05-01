module org.example.fichanewteam {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlinx.serialization.core;
    requires org.jdbi.v3.sqlobject.kotlin;
    requires org.jdbi.v3.sqlobject;
    requires logging.jvm;
    requires kotlin.result.jvm;
    requires org.jdbi.v3.kotlin;
    requires org.jdbi.v3.core;
    requires java.sql;


    opens org.example.fichanewteam to javafx.fxml;
    exports org.example.fichanewteam;
}