package muri.calc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //System.out.println(Font.loadFont(Objects.requireNonNull(HelloApplication.class.getResource("/font/HindSiliguri-Medium.ttf")).toExternalForm(), 10));

        //carregar a fonte
        Font.loadFont(Objects.requireNonNull(HelloApplication.class.getResource("/font/HindSiliguri-Medium.ttf")).toExternalForm(), 10);

        //carregar o fxml
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("calculadora-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("calculadora.css")).toExternalForm());

        //adiciona o icone do programa
        stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("icon.png"))));
        stage.setTitle("Calculadora");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}