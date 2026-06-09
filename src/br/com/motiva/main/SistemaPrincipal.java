package br.com.motiva.main;

import br.com.motiva.model.MonitoravelViaIoT;
import br.com.motiva.model.EquipeManutencao;
import br.com.motiva.model.TrechoRodovia;
import br.com.motiva.model.TrechoRodovia.TipoAmbiente;
import br.com.motiva.model.IntervencaoOperacional;
import br.com.motiva.model.Pulverizacao;
import br.com.motiva.model.RocadaMecanizada;
import br.com.motiva.model.MotorPrioridade;

import java.util.List;

public class SistemaPrincipal {

    public static void main(String[] args) {

        System.out.println("--- Sistema de Monitoramento de Vegetacao - Motiva ---");

        // Teste 1: Cadastro de trechos com diferentes tipos de ambiente
        TrechoRodovia trecho1 = new TrechoRodovia(
                10.0, 15.0, 20.0,
                TipoAmbiente.UMIDO, true
        );

        TrechoRodovia trecho2 = new TrechoRodovia(
                30.0, 38.0, 5.0,
                TipoAmbiente.SECO, false
        );

        TrechoRodovia trecho3 = new TrechoRodovia(
                50.0, 55.0, 35.0,
                TipoAmbiente.TROPICAL, false
        );

        TrechoRodovia trecho4 = new TrechoRodovia(
                70.0, 74.0, 8.0,
                TipoAmbiente.PADRAO, false
        );

        // Teste 2: Exibição dos dados cadastrados
        System.out.println("\nTrechos cadastrados:");

        System.out.println(
                "Trecho 1: KM " + trecho1.getQuilometroInicial()
                        + " ao " + trecho1.getQuilometroFinal()
                        + " | Vegetacao: " + trecho1.getNivelVegetacao()
                        + " cm | Ambiente: "
                        + trecho1.getTipoAmbiente().getDescricao()
        );

        System.out.println(
                "Trecho 2: KM " + trecho2.getQuilometroInicial()
                        + " ao " + trecho2.getQuilometroFinal()
                        + " | Vegetacao: " + trecho2.getNivelVegetacao()
                        + " cm | Ambiente: "
                        + trecho2.getTipoAmbiente().getDescricao()
        );

        System.out.println(
                "Trecho 3: KM " + trecho3.getQuilometroInicial()
                        + " ao " + trecho3.getQuilometroFinal()
                        + " | Vegetacao: " + trecho3.getNivelVegetacao()
                        + " cm | Ambiente: "
                        + trecho3.getTipoAmbiente().getDescricao()
        );

        System.out.println(
                "Trecho 4: KM " + trecho4.getQuilometroInicial()
                        + " ao " + trecho4.getQuilometroFinal()
                        + " | Vegetacao: " + trecho4.getNivelVegetacao()
                        + " cm | Ambiente: "
                        + trecho4.getTipoAmbiente().getDescricao()
        );

        // Teste 3: Simulação do crescimento da vegetação
        System.out.println("\nSimulando crescimento da vegetacao:");

        trecho1.registrarCrescimento(15.0);
        trecho2.registrarCrescimento(15.0);
        trecho3.registrarCrescimento(15.0);
        trecho4.registrarCrescimento(15.0);


        // Teste 4: Monitoramento por sensores IoT
        System.out.println("\nAtualizando dados via sensor IoT:");

        trecho1.transmitirDadosSensor(51.3);
        trecho2.transmitirDadosSensor(20.0);

        // Teste 5: Polimorfismo por interface
        MonitoravelViaIoT sensorAtivo = trecho1;

        System.out.println(
                "Sensor ativo: " + sensorAtivo.isSensorAtivo()
        );

        // Teste 6: Validação de regra de negócio
        System.out.println(
                "\nTentando setar vegetacao negativa no Trecho 1:"
        );

        trecho1.setNivelVegetacao(-99.0);

        System.out.println(
                "Nivel atual do Trecho 1: "
                        + trecho1.getNivelVegetacao()
                        + " cm"
        );

        // Teste 7: Geração automática de prioridades
        System.out.println("\nGerando relatorio de prioridade:");

        TrechoRodovia[] todosTrechos = {
                trecho1,
                trecho2,
                trecho3,
                trecho4
        };

        MotorPrioridade motor = new MotorPrioridade();

        List<IntervencaoOperacional> intervencoes =
                motor.gerarRelatorio(todosTrechos);

        // Teste 8: Criação das equipes de manutenção
        EquipeManutencao equipeAlfa =
                new EquipeManutencao("Equipe 01", 6);

        EquipeManutencao equipeBeta =
                new EquipeManutencao("Equipe 02", 4);

        // Teste 9: Execução de intervenção mecanizada
        System.out.println("\nExecutando intervencoes:");

        IntervencaoOperacional rocada =
                new RocadaMecanizada(
                        trecho3,
                        "Trator Valtra BH 180"
                );

        equipeAlfa.executarIntervencao(rocada);

        // Teste 10: Execução de intervenção química
        IntervencaoOperacional pulverizacao =
                new Pulverizacao(
                        trecho2,
                        "Glifosato 2%",
                        75.0
                );

        equipeBeta.executarIntervencao(pulverizacao);

        System.out.println("\nCiclo de monitoramento encerrado.");
    }
}