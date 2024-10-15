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

import java.util.ArrayList;

/**
 * @author Murilo Nunes & Hartur Sales
 * @date 13/10/2024
 * @brief Class CalculadoraModel
 */

public class CalculadoraModel {
    private Double num1;
    private Character operador;
    private Double num2;
    private boolean operadorSelecionado = false;
    private Double resultado;

//    private ArrayList<String> calculos = new ArrayList<>();

    //getters setters add remove

//    public ArrayList<String> getCalculos() {
//        return calculos;
//    }
//
//    public void addCalculo(String calculadoraModel) {
//        calculos.add(calculadoraModel);
//    }
//
//    public void removeCalculo(String calculadoraModel) {
//        calculos.remove(calculadoraModel);
//    }

    public double getNum1() {
        return num1;
    }

    public void setNum1(Double num1) {
        this.num1 = num1;
    }

    public Character getOperador() {
        return operador;
    }

    public void setOperador(Character operador) {
        this.operador = operador;
    }

    public double getNum2() {
        return num2;
    }

    public void setNum2(Double num2) {
        this.num2 = num2;
    }

    public boolean isOperadorSelecionado() {
        return operadorSelecionado;
    }

    public void setOperadorSelecionado(boolean operadorSelecionado) {
        this.operadorSelecionado = operadorSelecionado;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
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
            return 0;
        }
        return num1 / num2;
    }


    public double multiplicar(double num1, double num2) {
        return num1 * num2;
    }

    public double calcularRaiz(double valor) {
        return Math.sqrt(valor);
    }


    //entre dois valores
//    public double elevarPotencia(double base, double expoente) {
//        return Math.pow(base, expoente);
//    }

    //ao quadrado
    public double elevarQuadrado(double valor) {
        return Math.pow(valor, 2);
    }

//    public double calcularPctAumentado(double valor, double percentual) {
//        return valor * (1 + percentual / 100);
//    }

    public double calcularPctDeUm(double valor, double percentual) {
        return valor * (percentual / 100);
    }

    public double mostrarResultado(double num1, char operador, double num2) {
        //era um switch que ao inves de break, tinha return, isso é só uma recomendaçao da IDE
        return switch (operador) {
            case '+' -> somar(num1, num2);
            case '-' -> subtrair(num1, num2);
            case '*' -> multiplicar(num1, num2);
            case '/' -> dividir(num1, num2);
            case 'e' -> elevarQuadrado(num1);
            case 'p' -> calcularPctDeUm(num1, num2);
            case 'r' -> calcularRaiz(num1);
            //tá faltando o outro de porcentagem
            default -> throw new IllegalArgumentException("Operação desconhecida: " + operador);
        };
    }

}