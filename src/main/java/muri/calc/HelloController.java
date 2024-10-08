package muri.calc;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

}