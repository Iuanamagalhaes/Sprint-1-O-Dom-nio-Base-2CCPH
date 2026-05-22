package br.com.motiva.main;

import br.com.motiva.model.EquipeManutencao;
import br.com.motiva.model.TrechoRodovia;

public class SistemaPrincipal {

    public static void main(String[] args) {

        System.out.println("--- Sistema de Monitoramento de Vegetação - Motiva ---");

        // Primeiro trecho
        TrechoRodovia trecho1 = new TrechoRodovia(10.0, 15.0, 20.0);

        // Segundo trecho
        TrechoRodovia trecho2 = new TrechoRodovia(30.0, 38.0, 5.0);

        System.out.println("\nTrechos cadastrados:");
        System.out.println("Trecho 1: KM " + trecho1.getQuilometroInicial()
                + " ao " + trecho1.getQuilometroFinal()
                + " | Vegetação: " + trecho1.getNivelVegetacao() + " cm");
        System.out.println("Trecho 2: KM " + trecho2.getQuilometroInicial()
                + " ao " + trecho2.getQuilometroFinal()
                + " | Vegetação: " + trecho2.getNivelVegetacao() + " cm");
        System.out.println("\n>>> Simulando crescimento de vegetação:");
        trecho1.registrarCrescimento(25.0);  // 20 + 25 = 45 cm (crítico)
        trecho2.registrarCrescimento(5.0);   //  5 +  5 = 10 cm (normal)

        System.out.println("\nTentando setar vegetação negativa no Trecho 1:");
        trecho1.setNivelVegetacao(-5.0); // Deve ser bloqueado

        System.out.println("Nível atual do Trecho 1 (deve continuar 45.0): "
                + trecho1.getNivelVegetacao() + " cm");

        EquipeManutencao equipeAlfa = new EquipeManutencao("Alfa", 6);

        System.out.println("\n Associando equipe ao trecho crítico:");
        equipeAlfa.atenderTrecho(trecho1);
    }
}