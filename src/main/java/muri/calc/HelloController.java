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
    public Button botaoNove, botaoOito, botaoSete, botaoSeis, botaoCinco, botaoQuatro, botaoTres, botaoDois, botaoUm, botaoZero;

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
    public void botaoClicado(ActionEvent click) {
        String valor = ((Button) click.getSource()).getText();
        resultadoTexto.setText(resultadoTexto.getText() + valor);
        /*botoes numericos
        if (click.getSource() == botaoZero) {
            resultadoTexto.setText(resultadoTexto.getText() + "0");
        }
        if (click.getSource() == botaoUm) {
            resultadoTexto.setText(resultadoTexto.getText() + "1");
        }
        if (click.getSource() == botaoDois) {
            resultadoTexto.setText(resultadoTexto.getText() + "2");
        }
        if (click.getSource() == botaoTres) {
            resultadoTexto.setText(resultadoTexto.getText() + "3");
        }
        if (click.getSource() == botaoQuatro) {
            resultadoTexto.setText(resultadoTexto.getText() + "4");
        }
        if (click.getSource() == botaoCinco) {
            resultadoTexto.setText(resultadoTexto.getText() + "5");
        }
        if (click.getSource() == botaoSeis) {
            resultadoTexto.setText(resultadoTexto.getText() + "6");
        }
        if (click.getSource() == botaoSete) {
            resultadoTexto.setText(resultadoTexto.getText() + "7");
        }
        if (click.getSource() == botaoOito) {
            resultadoTexto.setText(resultadoTexto.getText() + "8");
        }
        if (click.getSource() == botaoNove) {
            resultadoTexto.setText(resultadoTexto.getText() + "9");
        }
        */

        //botoes de gerenciamento
        if (click.getSource() == botaoDecimal) {
            resultadoTexto.setText(resultadoTexto.getText() + ".");
        }

        if (click.getSource() == botaoMudarSinal) {
            if (calc.isOperadorSelecionado()) {
                double numero2 = calc.getNum2() * (-1);
                calc.setNum2(numero2);
                resultadoTexto.setText(String.valueOf(numero2));
            } else {
                double numero1 = calc.getNum1() * (-1);
                calc.setNum1(numero1);
                resultadoTexto.setText(String.valueOf(numero1));
            }
        }

        if (click.getSource() == botaoAc) {
            calc.setNum1(0.0);
            calc.setNum2(0.0);
            calc.setOperador(null);
            calc.setOperadorSelecionado(false);
            resultadoTexto.setText("");
        }

        if (click.getSource() == botaoApagar) {
            String numeroDigitado = resultadoTexto.getText();
            if (!numeroDigitado.isEmpty()) {
                String novoTexto = numeroDigitado.substring(0, numeroDigitado.length() - 1);
                //substring criando uma nova string só que nao tem o ultimo caracter
                resultadoTexto.setText(novoTexto);
            }
        }

        //botoes de operaçao
        if (click.getSource() == botaoSomar) {
            if (!calc.isOperadorSelecionado()) {
                calc.setNum1(Double.parseDouble(resultadoTexto.getText()));
                calc.setOperador('+');
                calc.setOperadorSelecionado(true);
                resultadoTexto.setText("");
            } else {
                calc.setOperador('+');
            }
        }
        if (click.getSource() == botaoSubtrair) {
            if (!calc.isOperadorSelecionado()) {
                calc.setNum1(Double.parseDouble(resultadoTexto.getText()));
                calc.setOperador('-');
                calc.setOperadorSelecionado(true);
                resultadoTexto.setText("");
            } else {
                calc.setOperador('-');
            }
        }
        if (click.getSource() == botaoDividir) {
            if (!calc.isOperadorSelecionado()) {
                calc.setNum1(Double.parseDouble(resultadoTexto.getText()));
                calc.setOperador('/');
                calc.setOperadorSelecionado(true);
                resultadoTexto.setText("");
            } else {
                calc.setOperador('/');
            }
        }
        if (click.getSource() == botaoMultiplicar) {
            if (!calc.isOperadorSelecionado()) {
                calc.setNum1(Double.parseDouble(resultadoTexto.getText()));
                calc.setOperador('*');
                calc.setOperadorSelecionado(true);
                resultadoTexto.setText("");
            } else {
                calc.setOperador('*');
            }
        }
        if (click.getSource() == botaoQuadrado) {
            if (!calc.isOperadorSelecionado()) {
                calc.setNum1(Double.parseDouble(resultadoTexto.getText()));
                calc.setOperador('e');
                calc.setOperadorSelecionado(true);
                resultadoTexto.setText("");
            } else {
                calc.setOperador('e');
            }
        }
        if (click.getSource() == botaoPorcent) {
            if (!calc.isOperadorSelecionado()) {
                calc.setNum1(Double.parseDouble(resultadoTexto.getText()));
                calc.setOperador('p');
                calc.setOperadorSelecionado(true);
                resultadoTexto.setText("");
            } else {
                calc.setOperador('p');
            }
        }
        if (click.getSource() == raizBotao) {
            if (!calc.isOperadorSelecionado()) {
                calc.setNum1(Double.parseDouble(resultadoTexto.getText()));
                calc.setOperador('r');
                calc.setOperadorSelecionado(true);
                resultadoTexto.setText("");
            } else {
                calc.setOperador('r');
            }
        }
        if (click.getSource() == botaoIgual) {
            calc.setNum2(Double.parseDouble(resultadoTexto.getText()));
            resultadoTexto.setText("");
            calc.setResultado(calc.mostrarResultado(calc.getNum1(), calc.getOperador(), calc.getNum2()));
            resultadoTexto.setText("" + calc.getResultado());
            calc.setNum1(calc.getResultado());
            calc.setOperador(null);
        }

    }
}



