module com.example.simpledbproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.simpledbproject to javafx.fxml;
    exports com.example.simpledbproject;
}