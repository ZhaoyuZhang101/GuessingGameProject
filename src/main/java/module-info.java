module com.infs.birdhouseapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires javafx.media;
    requires com.google.gson;

    opens com.infs.birdhouseapp to javafx.fxml;
    exports com.infs.birdhouseapp;
    exports com.infs.birdhouseapp.JsonClasses;
    exports com.infs.birdhouseapp.Controllers to javafx.fxml;
    opens com.infs.birdhouseapp.Controllers to javafx.fxml;

}