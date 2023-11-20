module com.ga.genalg_with_userinterface {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ga.genalg_with_userinterface to javafx.fxml;
    exports com.ga.genalg_with_userinterface;
}