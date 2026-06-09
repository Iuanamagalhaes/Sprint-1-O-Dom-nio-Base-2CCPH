package br.com.motiva.model;

public class RocadaManual extends IntervencaoOperacional {

    private static final double NIVEL_POS_INTERVENCAO = 5.0;

    private int numeroDePessoas;

    public RocadaManual(
            TrechoRodovia trechoAlvo,
            int numeroDePessoas) {

        super("Rocada Manual", trechoAlvo);

        this.numeroDePessoas = numeroDePessoas;
    }

    @Override
    public void executarServico() {

        System.out.println(
                "Tipo: Rocada Manual"
        );

        System.out.println(
                "Quantidade de pessoas: "
                        + numeroDePessoas
        );

        System.out.println(
                "Nivel antes da rocada: "
                        + getTrechoAlvo().getNivelVegetacao()
                        + " cm"
        );

        System.out.println(
                "Nivel apos a rocada: "
                        + NIVEL_POS_INTERVENCAO
                        + " cm"
        );

        getTrechoAlvo().setNivelVegetacao(
                NIVEL_POS_INTERVENCAO
        );
    }

    public int getNumeroDePessoas() {
        return this.numeroDePessoas;
    }
}