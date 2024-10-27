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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

/**
 * @author Murilo Nunes & Hartur Sales
 * @date 08/10/2024
 * @brief Class HelloController
 */

public class HelloController {
    //TE AMO TAYLOR SWIFT
    private Scene scene;
    private final NumberFormat FORMATAR = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
    private static final DecimalFormat FORMATO_DECIMAL = new DecimalFormat("#.#####");

    CalculadoraModel calc = new CalculadoraModel();

    //botoes de operaçao
    @FXML
    private Button botaoDividir, botaoMultiplicar, botaoSubtrair, botaoSomar, botaoIgual, botaoQuadrado, botaoPorcent, raizBotao;

    //botoes de numeros
    @FXML
    private Button botaoUm, botaoDois, botaoTres, botaoQuatro, botaoCinco, botaoSeis, botaoSete, botaoOito, botaoNove, botaoZero;

    //botoes de gerenciamento
    @FXML
    private Button botaoMudarSinal, botaoAc, botaoDecimal, botaoApagar, botaoTema;

    @FXML
    private Label resultadoTexto, reviewTexto;

    @FXML
    private ImageView imagemTema, deleteImagem;

    private final String temaEscuro = Objects.requireNonNull(getClass().getResource("/styles/calculadora.css")).toExternalForm();
    private final String temaClaro = Objects.requireNonNull(getClass().getResource("/styles/calculadora-branca.css")).toExternalForm();
    private boolean temaAtual = true; //true para escuro, false para claro

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage stage) {
        stage.setOnCloseRequest(this::fecharApp);
    }

    private void fecharApp(WindowEvent event) {
        calc.criarArquivo();
    }

    @FXML
    private void initialize() {
        resultadoTexto.setText("");
        reviewTexto.setText("");
    }

    public void mudarTema() {
        if (temaAtual) {
            aplicarTema(temaClaro, "/images/dark-mode.png", "/images/backspace-dark.png");
        } else {
            aplicarTema(temaEscuro, "/images/light-mode.png", "/images/backspace.png");
        }
        temaAtual = !temaAtual;
    }

    private void aplicarTema(String tema, String temaImgPath, String deleteImgPath) {
        atualizarImagens(temaImgPath, deleteImgPath);
        scene.getStylesheets().remove(temaAtual ? temaEscuro : temaClaro);
        scene.getStylesheets().add(tema);
    }

    private void atualizarImagens(String temaImg, String deleteImg) {
        imagemTema.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(temaImg))));
        deleteImagem.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(deleteImg))));
    }

    //qual botão foi clicado
    public void numeroClicado(ActionEvent click) {
        if (calc.isExibindoErro()) {
            limpar();
        }
        String valor = ((Button) click.getSource()).getText();

        if (!calc.isResultadoCalculado()) {
            resultadoTexto.setText(resultadoTexto.getText() + valor);
        } else {
            limpar();
            resultadoTexto.setText(resultadoTexto.getText() + valor);
        }
    }

    public void botaoGerenClicado(ActionEvent e) {
        if (calc.isExibindoErro()) {
            limpar();
        }

        String textoAtual = resultadoTexto.getText();

        if (e.getSource() == botaoDecimal) {
            if (textoAtual.isEmpty() || calc.isResultadoCalculado()) {
                limpar();
                resultadoTexto.setText("0,");
            } else if (!textoAtual.contains(",")) {
                resultadoTexto.setText(textoAtual + ",");
            }
        } else if (e.getSource() == botaoMudarSinal) {
            double numero = Double.parseDouble(textoAtual) * -1;
            resultadoTexto.setText(formatarResultado(numero));
        } else if (e.getSource() == botaoAc) {
            limpar();
        } else if (e.getSource() == botaoApagar && !textoAtual.isEmpty()) {
            resultadoTexto.setText(textoAtual.substring(0, textoAtual.length() - 1));
        }
    }

    public void botaoOperacaoClicado(ActionEvent actionEvent) throws ParseException {
        if (!resultadoTexto.getText().isEmpty()) {
            double numero = FORMATAR.parse(resultadoTexto.getText()).doubleValue();
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
                reviewTexto.setText("√" + formatarResultado(calc.getNum1()));
                resultadoTexto.setText(formatarResultado(calc.getResultado()));
                calc.setNum1(calc.getResultado());
                calc.addCalculo(reviewTexto.getText() + " = " + resultadoTexto.getText());
                calc.setResultadoCalculado(true);
                return;
            }
            reviewTexto.setText(formatarResultado(calc.getNum1()) + " " + calc.getOperador());
        }
    }

    //OBRIGADO TAYLOR SWIFT
    public void botaoIgualClicado() {
        try {
            if (!resultadoTexto.getText().isEmpty()) {
                double num2 = FORMATAR.parse(resultadoTexto.getText()).doubleValue();
                if (calc.getOperador() != '\0') {
                    calc.setNum2(num2);
                    reviewTexto.setText(formatarResultado(calc.getNum1()) + " " + calc.getOperador() + " "
                            + formatarResultado(calc.getNum2()) + " =");
                    calc.setResultado(calc.definirOperacao(calc.getNum1(), calc.getOperador(), calc.getNum2()));
                    resultadoTexto.setText(formatarResultado(calc.getResultado()));
                    calc.setNum1(calc.getResultado());
                    calc.addCalculo(reviewTexto.getText() + " " + resultadoTexto.getText());
                } else {
                    reviewTexto.setText(formatarResultado(num2) + " =");
                    resultadoTexto.setText(formatarResultado(num2));
                    calc.addCalculo(reviewTexto.getText() + " " + resultadoTexto.getText());
                }
            }
            calc.setOperadorSelecionado(false);
            calc.setResultadoCalculado(true);
            restaurarFonte();
        } catch (ArithmeticException | ParseException e) {
            exibirErro(e.getMessage());
        }
    }

    private void limpar() {
        calc.setNum1(0.0);
        calc.setNum2(0.0);
        calc.setResultado(0.0);
        calc.setOperador('\0');
        calc.setOperadorSelecionado(false);
        calc.setResultadoCalculado(false);
        resultadoTexto.setText("");
        reviewTexto.setText("");
        restaurarFonte();
    }

    private void exibirErro(String mensagemErro) {
        resultadoTexto.setStyle("-fx-font-size: " + 20 + "px;");
        resultadoTexto.setText(mensagemErro);
        calc.setExibindoErro(true);
    }

    private void restaurarFonte() {
        resultadoTexto.setStyle("-fx-font-size: " + 70 + "px;");
        calc.setExibindoErro(false);
    }

    private String formatarResultado(double valor) {
        return (valor % 1 != 0) ? FORMATO_DECIMAL.format(valor) : String.valueOf((int) valor);
    }
}