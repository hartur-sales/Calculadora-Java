module muri.calc {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens muri.calc to javafx.fxml;
    exports muri.calc;
}