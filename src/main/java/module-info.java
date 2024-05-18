module com.team2.a2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.team2.a2.View to javafx.fxml;
    exports com.team2.a2;
}