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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    private final ArrayList<String> calculos = new ArrayList<>();

    public ArrayList<String> getCalculos() {
        return calculos;
    }

    public void addCalculo(String calculadoraModel) {
        calculos.add(calculadoraModel);
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

    public void deletarArquivo() {
        String resourcesPath = System.getProperty("user.dir") + "/src/main/resources/out";
        File outDir = new File(resourcesPath);
        File calculoFile = new File(outDir, "calculos.csv");

        if (calculoFile.exists() && calculoFile.delete()) {
            System.out.println("Arquivo de cálculos deletado com sucesso.");
        } else {
            System.out.println("Falha ao deletar arquivo de cálculos.");
        }

        if (outDir.exists() && outDir.delete()) {
            System.out.println("Diretório de saída deletado com sucesso.");
        } else {
            System.out.println("Falha ao deletar diretório de saída.");
        }
    }

    public void criarArquivo() {
        String resourcesPath = System.getProperty("user.dir") + "/src/main/resources/out";
        File outDir = new File(resourcesPath);

        if (outDir.exists() || outDir.mkdirs()) {
            File historyFile = new File(outDir, "calculos.csv");
            escreverHistorico(historyFile);
        } else {
            System.err.println("Falha ao criar diretório: " + resourcesPath);
        }
    }

    private void escreverHistorico(File historyFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(historyFile))) {
            for (String calculo : calculos) {
                bw.write(calculo);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
            throw new ArithmeticException("Divisão por zero não é permitida.");
        }
        return num1 / num2;
    }

    public double multiplicar(double num1, double num2) {
        return num1 * num2;
    }

    public double calcularRaiz(double valor) {
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
            case 'p' -> calcularPctDeUm(num1, num2);
            case 'r' -> calcularRaiz(num1);
            //tá faltando o outro de porcentagem
            default -> throw new IllegalArgumentException("Operação desconhecida: " + operador);
        };
    }
}