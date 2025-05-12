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
    requires kotlin.reflect;

    // Dependencias de la cache
    requires com.github.benmanes.caffeine;

    // Dependencias para serializar un JSON
    requires kotlinx.serialization.json;
    requires kotlinx.serialization.core;
    requires java.sql;

    opens org.example.fichanewteam to javafx.fxml;
    exports org.example.fichanewteam to javafx.fxml;

}
