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

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Murilo Nunes & Hartur Sales
 * @date 08/10/2024
 * @brief Class HelloController
 */

public class HelloController {

    //botoes de operaçao
    @FXML
    public Button dividirBotao, multiplicarBotao, subtrairBotao, somarBotao, igualBotao, quadradoBotao, porcentBotao, raizBotao;

    //botoes de numeros
    @FXML
    public Button noveBotao, seteBotao, oitoBotao, quatroBotao, cincoBotao, seisBotao, umBotao, tresBotao, doisBotao, zeroBotao;

    //botoes de gerenciamento
    @FXML
    public Button alterarBotao, acBotao, decimalBotao, apagarBotao;

    @FXML
    public Label resultadoTexto;

    @FXML
    private void initialize() {
        //resultadoTexto.setText("");
        noveBotao.getStyleClass().add("button");
        //TODO
    }
}