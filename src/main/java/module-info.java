module sio.tp5s2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens sio.tp5s2.Model;
    opens sio.tp5s2 to javafx.fxml;
    exports sio.tp5s2;
}