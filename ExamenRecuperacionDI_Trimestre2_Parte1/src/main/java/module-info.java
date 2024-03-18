module main.exameninterfaces {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires java.desktop;
    requires jasperreports;

    opens main.exameninterfaces to javafx.fxml;
    exports main.exameninterfaces;
}