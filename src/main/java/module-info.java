module muri.calc {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    exports muri.calc.controller;
    opens muri.calc.controller to javafx.fxml;
    exports muri.calc.model.operacoes;
    opens muri.calc.model.operacoes to javafx.fxml;
    exports muri.calc.model.historico;
    opens muri.calc.model.historico to javafx.fxml;
    exports muri.calc.view;
    opens muri.calc.view to javafx.fxml;
}