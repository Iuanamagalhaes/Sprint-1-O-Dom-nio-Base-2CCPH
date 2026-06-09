package br.com.motiva.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MotorPrioridade {

    private static final double LIMIAR_CRITICO = 40.0;
    private static final double LIMIAR_ATENCAO = 20.0;
    private static final double LIMIAR_ALERTA = 10.0;

    public List<IntervencaoOperacional> gerarRelatorio(
            TrechoRodovia[] trechos) {

        List<TrechoRodovia> ordenados =
                ordenarPorCriticidade(trechos);

        List<IntervencaoOperacional> intervencoes =
                new ArrayList<>();

        System.out.println("\nRelatorio de Prioridade:");

        for (TrechoRodovia trecho : ordenados) {

            String prioridade =
                    classificarPrioridade(trecho);

            String recomendacao =
                    definirRecomendacao(trecho);

            System.out.println(
                    "Trecho KM "
                            + trecho.getQuilometroInicial()
                            + " ao KM "
                            + trecho.getQuilometroFinal()
                            + " | Vegetacao: "
                            + trecho.getNivelVegetacao()
                            + " cm"
                            + " | Prioridade: "
                            + prioridade
                            + " | Recomendacao: "
                            + recomendacao
            );

            intervencoes.add(
                    criarIntervencao(trecho)
            );
        }

        imprimirResumo(intervencoes);

        return intervencoes;
    }

    private List<TrechoRodovia> ordenarPorCriticidade(
            TrechoRodovia[] trechos) {

        List<TrechoRodovia> lista =
                new ArrayList<>(List.of(trechos));

        lista.sort(
                Comparator.comparingDouble(
                        TrechoRodovia::getNivelVegetacao
                ).reversed()
        );

        return lista;
    }

    private String classificarPrioridade(
            TrechoRodovia trecho) {

        double nivel = trecho.getNivelVegetacao();

        if (nivel >= LIMIAR_CRITICO) {
            return "CRITICO";
        }

        if (nivel >= LIMIAR_ATENCAO) {
            return "ATENCAO";
        }

        if (nivel >= LIMIAR_ALERTA) {
            return "ALERTA";
        }

        return "NORMAL";
    }

    private String definirRecomendacao(
            TrechoRodovia trecho) {

        double nivel = trecho.getNivelVegetacao();

        if (nivel >= LIMIAR_CRITICO) {
            return "Rocada Mecanizada";
        }

        if (nivel >= LIMIAR_ATENCAO) {
            return "Rocada Manual";
        }

        if (nivel >= LIMIAR_ALERTA) {
            return "Pulverizacao";
        }

        return "Monitoramento";
    }

    private IntervencaoOperacional criarIntervencao(
            TrechoRodovia trecho) {

        double nivel = trecho.getNivelVegetacao();

        if (nivel >= LIMIAR_CRITICO) {
            return new RocadaMecanizada(
                    trecho,
                    "Trator c/ Triturador Florestal"
            );
        }

        if (nivel >= LIMIAR_ATENCAO) {
            return new RocadaManual(
                    trecho,
                    4
            );
        }

        if (nivel >= LIMIAR_ALERTA) {
            return new Pulverizacao(
                    trecho,
                    "Herbicida Glifosato 2%",
                    50.0
            );
        }

        return new Pulverizacao(
                trecho,
                "Produto de Monitoramento Preventivo",
                10.0
        ) {
            @Override
            public void executarServico() {
                System.out.println(
                        "Nivel normal. Apenas monitoramento."
                );
            }
        };
    }

    private void imprimirResumo(
            List<IntervencaoOperacional> intervencoes) {

        long criticos = intervencoes.stream()
                .filter(i -> i instanceof RocadaMecanizada)
                .count();

        long atencao = intervencoes.stream()
                .filter(i -> i instanceof RocadaManual)
                .count();

        long pulverizacao = intervencoes.stream()
                .filter(i -> i instanceof Pulverizacao)
                .count();

        System.out.println("\nResumo:");

        System.out.println(
                "Rocada Mecanizada: "
                        + criticos
                        + " trecho(s)"
        );

        System.out.println(
                "Rocada Manual: "
                        + atencao
                        + " trecho(s)"
        );

        System.out.println(
                "Pulverizacao: "
                        + pulverizacao
                        + " trecho(s)"
        );
    }
}