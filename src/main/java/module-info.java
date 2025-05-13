module org.example.fichanewteam {

    // Dependencias de JAVA FX
    requires javafx.controls;
    requires javafx.fxml;

    // Dependencias de KOTLIN
    requires kotlin.stdlib;

    // Dependencias del Logger
    requires logging.jvm;
    requires org.slf4j;

    // Dependencias Result
    requires kotlin.result.jvm;

    // Dependencias de JDBI
    requires org.jdbi.v3.sqlobject;
    requires org.jdbi.v3.core;
    requires org.jdbi.v3.kotlin;
    requires org.jdbi.v3.sqlobject.kotlin;
    requires io.leangen.geantyref;

    // Dependencias de la cache
    requires com.github.benmanes.caffeine;

    // Dependencias para serializar un JSON
    requires kotlinx.serialization.json;
    requires kotlinx.serialization.core;

    //SQL
    requires java.sql;

    // Open Vadin --> PARA ABRIR EN NAVEGADOR
    requires open;

    //Test

    //requires org.junit.jupiter.api;
    //requires org.mockito;
    //requires org.mockito.junit.jupiter;


    //APP
    opens org.example.fichanewteam to javafx.graphics;
    exports org.example.fichanewteam to javafx.graphics;

    //Controller
    opens org.example.fichanewteam.controllers to javafx.fxml;
    exports org.example.fichanewteam.controllers to javafx.fxml;

    //Routes manager
    opens org.example.fichanewteam.routes to javafx.fxml;
    exports org.example.fichanewteam.routes to javafx.fxml;

}
