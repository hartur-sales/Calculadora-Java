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
    private double primeiroValor;
    private String operador;
    private double valorSeguinte;

    private ArrayList<String> calculos = new ArrayList<>();

    public ArrayList<String> getCalculos() {
        return calculos;
    }

    public void addCalculo(String calculadoraModel) {
        calculos.add(calculadoraModel);
    }

    public void removeCalculo(String calculadoraModel) {
        calculos.remove(calculadoraModel);
    }

    public double getPrimeiroValor() {
        return primeiroValor;
    }

    public void setPrimeiroValor(double primeiroValor) {
        this.primeiroValor = primeiroValor;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public double getValorSeguinte() {
        return valorSeguinte;
    }

    public void setValorSeguinte(double valorSeguinte) {
        this.valorSeguinte = valorSeguinte;
    }
}