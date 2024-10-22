/*
 * Copyright (C) 2024. Murilo Nunes & Hartur Sales
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package muri.calc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Murilo Nunes & Hartur Sales
 * @date 08/10/2024
 * @brief Class HelloApplication
 */

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //System.out.println(Font.loadFont(Objects.requireNonNull(HelloApplication.class.getResource("/font/HindSiliguri-Medium.ttf")).toExternalForm(), 10));

        //carregar a fonte
        Font.loadFont(Objects.requireNonNull(HelloApplication.class.getResource("/font/HindSiliguri-Medium.ttf")).toExternalForm(), 10);

        //carregar o fxml
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("calculadora-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/calculadora.css")).toExternalForm());

        HelloController controller = fxmlLoader.getController();
        controller.setScene(scene);

        //adiciona o icone do programa
        stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("/images/icon.png"))));
        stage.setTitle("Calculadora");
        stage.setScene(scene);
        controller.setStage(stage);
        stage.show();
        controller.calc.deletarArquivo();
    }

    public static void main(String[] args) {
        launch();
    }
}