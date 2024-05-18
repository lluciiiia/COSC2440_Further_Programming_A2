module com.team2.a2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.team2.a2.View to javafx.fxml;
    opens com.team2.a2.View.Admin to javafx.fxml;
    opens com.team2.a2.View.Insurance to javafx.fxml;
    opens com.team2.a2.View.Login to javafx.fxml;
    opens com.team2.a2.View.Dependent to javafx.fxml;
    opens com.team2.a2.View.Policy to javafx.fxml;
    exports com.team2.a2;
}