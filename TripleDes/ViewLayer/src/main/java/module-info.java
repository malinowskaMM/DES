module com.example.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires Model;
    requires java.desktop;
    requires org.apache.commons.io;


    opens com.example.viewlayer to javafx.fxml;
    exports com.example.viewlayer;
}