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


package muri.calc.model.historico;

import muri.calc.model.operacoes.CalculadoraModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author Murilo Nunes & Hartur Sales
 * @date 28/10/2024
 * @brief Class HelloController
 */

public class HistoricoModel {
    public CalculadoraModel calculoModel = new CalculadoraModel();

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

    public void criarArquivo(List<String> calculoList) {
        String resourcesPath = System.getProperty("user.dir") + "/src/main/resources/out";
        File outDir = new File(resourcesPath);

        //ao criar o jar, trocar essa parte do código
        //File historyFile = new File(System.getProperty("user.dir"), "calculos.csv");
        //escreverHistorico(historyFile, calculoList);

        if (outDir.exists() || outDir.mkdirs()) {
            File historyFile = new File("user.home", "calculos.csv");
            escreverHistorico(historyFile, calculoList);
        } else {
            System.err.println("Falha ao criar diretório: " + resourcesPath);
        }

    }

    private void escreverHistorico(File historyFile, List<String> calculoList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(historyFile, true))) {
            for (String calculo : calculoList) {
                bw.write(calculo);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
