package br.com.motiva.model;

public class Pulverizacao extends IntervencaoOperacional {

    private String produtoQuimico;
    private double volumeLitros;

    public Pulverizacao(
            TrechoRodovia trechoAlvo,
            String produtoQuimico,
            double volumeLitros) {

        super("Pulverizacao Quimica", trechoAlvo);

        this.produtoQuimico = produtoQuimico;
        this.volumeLitros = volumeLitros;
    }

    @Override
    public void executarServico() {

        double nivelAtual =
                getTrechoAlvo().getNivelVegetacao();

        double nivelPosAplicacao =
                nivelAtual * 0.70;

        System.out.println(
                "Tipo: Pulverizacao Quimica"
        );

        System.out.println(
                "Produto utilizado: "
                        + produtoQuimico
        );

        System.out.println(
                "Volume aplicado: "
                        + volumeLitros
                        + " litros"
        );

        System.out.println(
                "Nivel antes da aplicacao: "
                        + nivelAtual
                        + " cm"
        );

        System.out.println(
                "Nivel estimado apos aplicacao: "
                        + nivelPosAplicacao
                        + " cm"
        );

        getTrechoAlvo().setNivelVegetacao(
                nivelPosAplicacao
        );
    }

    public String getProdutoQuimico() {
        return this.produtoQuimico;
    }

    public double getVolumeLitros() {
        return this.volumeLitros;
    }
}