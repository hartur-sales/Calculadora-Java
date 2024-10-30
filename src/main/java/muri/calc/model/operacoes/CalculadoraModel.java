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

package muri.calc.model.operacoes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author Murilo Nunes & Hartur Sales
 * @date 13/10/2024
 * @brief Class CalculadoraModel
 */

public class CalculadoraModel {
    private double num1;
    private char operador;
    private boolean operadorSelecionado = false;
    private double num2;
    private double resultado;
    private boolean resultadoCalculado = false;
    private boolean exibindoErro = false;

    public final ArrayList<String> logging = new ArrayList<>();

    public ArrayList<String> getLogging() {
        return logging;
    }

    public void addCalculo(String calculoFeito) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime data = LocalDateTime.now();
        logging.add(data.format(fmt) + " -> " + calculoFeito);
    }

    public double getNum1() {
        return num1;
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public char getOperador() {
        return operador;
    }

    public void setOperador(char operador) {
        this.operador = operador;
    }

    public double getNum2() {
        return num2;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    //parte do operador
    public boolean isOperadorSelecionado() {
        return operadorSelecionado;
    }

    public void setOperadorSelecionado(boolean operadorSelecionado) {
        this.operadorSelecionado = operadorSelecionado;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public boolean isResultadoCalculado() {
        return resultadoCalculado;
    }

    public void setResultadoCalculado(boolean resultadoCalculado) {
        this.resultadoCalculado = resultadoCalculado;
    }

    public boolean isExibindoErro() {
        return exibindoErro;
    }

    public void setExibindoErro(boolean exibindoErro) {
        this.exibindoErro = exibindoErro;
    }

    //agora os metodos usados pela calculadora
    public double somar(double num1, double num2) {
        return num1 + num2;
    }

    public double subtrair(double num1, double num2) {
        return num1 - num2;
    }

    public double dividir(double num1, double num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Não é possível dividir por zero");
        }
        return num1 / num2;
    }

    public double multiplicar(double num1, double num2) {
        return num1 * num2;
    }

    public double calcularRaiz(double valor) {
        if (valor < 0) {
            throw new ArithmeticException("Entrada inválida");
        }
        return Math.sqrt(valor);
    }

    public double calcularPotencia(double valor1, double valor2) {
        return Math.pow(valor1, valor2);
    }

    public double calcularPctDeUm(double valor, double percentual) {
        return valor * (percentual / 100);
    }

    public double definirOperacao(double num1, char operador, double num2) {
        //era um switch que ao inves de break, tinha return, isso é só uma recomendaçao da IDE
        return switch (operador) {
            case '+' -> somar(num1, num2);
            case '-' -> subtrair(num1, num2);
            case 'x' -> multiplicar(num1, num2);
            case '/' -> dividir(num1, num2);
            case '^' -> calcularPotencia(num1, num2);
            case '%' -> calcularPctDeUm(num1, num2);
            case 'r' -> calcularRaiz(num1);
            //tá faltando o outro de porcentagem
            default -> throw new IllegalArgumentException("Operação desconhecida: " + operador);
        };
    }
}