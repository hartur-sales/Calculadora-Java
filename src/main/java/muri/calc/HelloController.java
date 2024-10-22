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

    CalculadoraModel calc = new CalculadoraModel();

    //botoes de operaçao
    @FXML
    public Button botaoDividir, botaoMultiplicar, botaoSubtrair, botaoSomar, botaoIgual, botaoQuadrado, botaoPorcent, raizBotao;

    //botoes de numeros
    @FXML
    public Button botaoUm, botaoDois, botaoTres, botaoQuatro, botaoCinco, botaoSeis, botaoSete, botaoOito, botaoNove, botaoZero;

    //botoes de gerenciamento
    @FXML
    public Button botaoMudarSinal, botaoAc, botaoDecimal, botaoApagar, botaoTema;

    @FXML
    public Label resultadoTexto, reviewTexto;

    @FXML
    public ImageView imagemTema, deleteImagem;

    private final String temaEscuro = Objects.requireNonNull(getClass().getResource("/styles/calculadora.css")).toExternalForm();
    private final String temaClaro = Objects.requireNonNull(getClass().getResource("/styles/calculadora-branca.css")).toExternalForm();
    private boolean temaAtual = true; //true para escuro, false para claro


    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    private void initialize() {
        resultadoTexto.setText("");
        reviewTexto.setText("");
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

    //qual botão foi clicado
    public void numeroClicado(ActionEvent click) {
        String valor = ((Button) click.getSource()).getText();
        resultadoTexto.setText(resultadoTexto.getText() + valor);
    }

    public void botaoGerenClicado(ActionEvent e) {
        String textoAtual = resultadoTexto.getText();

        if (e.getSource() == botaoDecimal) {
            if (!textoAtual.contains(".")) {
                resultadoTexto.setText(textoAtual + ".");
            }
            if (textoAtual.isEmpty()) {
                resultadoTexto.setText("0.");
            }
        } else if (e.getSource() == botaoMudarSinal) {
            double numero = Double.parseDouble(textoAtual) * -1;
            resultadoTexto.setText(String.valueOf(numero));
        } else if (e.getSource() == botaoAc) {
            // Zerar todos os valores do modelo
            calc.setNum1(0.0);
            calc.setNum2(0.0);
            calc.setResultado(0.0);
            calc.setOperador('\0'); // Define o operador como vazio
            calc.setOperadorSelecionado(false);
            resultadoTexto.setText("");
            reviewTexto.setText("");
        } else if (e.getSource() == botaoApagar && !textoAtual.isEmpty()) {
            resultadoTexto.setText(textoAtual.substring(0, textoAtual.length() - 1));
        }
    }


    public void botaoOperacaoClicado(ActionEvent actionEvent) {
        if (!resultadoTexto.getText().isEmpty()) {
            double numero = Double.parseDouble(resultadoTexto.getText());
            calc.setNum1(numero);
            resultadoTexto.setText("");

            if (actionEvent.getSource() == botaoSomar) {
                calc.setOperador('+');
            } else if (actionEvent.getSource() == botaoSubtrair) {
                calc.setOperador('-');
            } else if (actionEvent.getSource() == botaoMultiplicar) {
                calc.setOperador('x');
            } else if (actionEvent.getSource() == botaoDividir) {
                calc.setOperador('/');
            } else if (actionEvent.getSource() == botaoQuadrado) {
                calc.setOperador('^');
            } else if (actionEvent.getSource() == botaoPorcent) {
                calc.setOperador('p');
            } else if (actionEvent.getSource() == raizBotao) {
                calc.setOperador('r');
                calc.setResultado(calc.calcularRaiz(numero));
                reviewTexto.setText("√" + calc.getNum1());
                resultadoTexto.setText(String.valueOf(calc.getResultado()));
                calc.setNum1(calc.getResultado());
                return;
            }
            reviewTexto.setText(calc.getNum1() + " " + calc.getOperador());
        }
    }

    public void botaoIgualClicado(ActionEvent act) {
        try {
            if (!resultadoTexto.getText().isEmpty()) {
                double num2 = Double.parseDouble(resultadoTexto.getText());
                if (calc.getOperador() != '\0') {
                    calc.setNum2(num2);
                    reviewTexto.setText(calc.getNum1() + " " + calc.getOperador() + " " + calc.getNum2() + " =");
                    calc.setResultado(calc.mostrarResultado(calc.getNum1(), calc.getOperador(), calc.getNum2()));
                    resultadoTexto.setText(String.valueOf(calc.getResultado()));
                    calc.setNum1(calc.getResultado());
                    calc.setOperadorSelecionado(false);
                } else {
                    reviewTexto.setText(num2 + " =");
                    resultadoTexto.setText(String.valueOf(num2));
                }
            }
        } catch (ArithmeticException e) {
            resultadoTexto.setText(e.getMessage());
        }
    }
}