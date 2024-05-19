module com.team.a2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.team2.a2 to javafx.fxml;
    exports com.team2.a2;
    exports com.team2.a2.View.Admin;
    opens com.team2.a2.View.Admin to javafx.fxml;
    exports com.team2.a2.View.Dependent;
    opens com.team2.a2.View.Dependent to javafx.fxml;
    exports com.team2.a2.View.PolicyHolder;
    opens com.team2.a2.View.PolicyHolder to javafx.fxml;
    exports com.team2.a2.View.PolicyOwner;
    opens com.team2.a2.View.PolicyOwner to javafx.fxml;
    exports com.team2.a2.View.InsuranceSurveyor;
    opens com.team2.a2.View.InsuranceSurveyor to javafx.fxml;
    exports com.team2.a2.View.InsuranceManager;
    opens com.team2.a2.View.InsuranceManager to javafx.fxml;
    exports com.team2.a2.View;
    opens com.team2.a2.View to javafx.fxml;
}