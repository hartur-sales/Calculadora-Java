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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * @author Murilo Nunes & Hartur Sales
 * @date 08/10/2024
 * @brief Class HelloController
 */

public class HelloController {
    private Scene scene;

    //botoes de operaçao
    @FXML
    public Button dividirBotao, multiplicarBotao, subtrairBotao, somarBotao, igualBotao, quadradoBotao, porcentBotao, raizBotao;

    //botoes de numeros
    @FXML
    public Button noveBotao, seteBotao, oitoBotao, quatroBotao, cincoBotao, seisBotao, umBotao, tresBotao, doisBotao, zeroBotao;

    //botoes de gerenciamento
    @FXML
    public Button alterarBotao, acBotao, decimalBotao, apagarBotao, temaBotao;

    @FXML
    public Label resultadoTexto, reviewTexto;

    @FXML
    public ImageView imagemTema, deleteImagem;

    private final String temaEscuro = Objects.requireNonNull(getClass().getResource("calculadora.css")).toExternalForm();
    private final String temaClaro = Objects.requireNonNull(getClass().getResource("calculadora-branca.css")).toExternalForm();
    private boolean temaAtual = true; //true para escuro, false para claro

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    private void initialize() {
        //resultadoTexto.setText("");
        //reviewTexto.setText("");
        //TODO
    }

    public void mudarTema(ActionEvent actionEvent) {
        if (temaAtual) {
            //tema claro
            aplicarTemaClaro();
        } else {
            //tema escuro
            aplicarTemaEscuro();
        }
        //muda a variavel do tema
        temaAtual = !temaAtual;
    }

    public void aplicarTemaClaro() {
        imagemTema.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/dark-mode.png"))));
        scene.getStylesheets().remove(temaEscuro);
        scene.getStylesheets().add(temaClaro);
        deleteImagem.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/backspace-dark.png"))));
    }

    public void aplicarTemaEscuro() {
        imagemTema.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/light-mode.png"))));
        scene.getStylesheets().remove(temaClaro);
        scene.getStylesheets().add(temaEscuro);
        deleteImagem.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/backspace.png"))));
    }
}
