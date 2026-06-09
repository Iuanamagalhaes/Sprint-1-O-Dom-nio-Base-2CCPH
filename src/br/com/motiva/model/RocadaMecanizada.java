package br.com.motiva.model;

public class RocadaMecanizada extends IntervencaoOperacional {

    private static final double NIVEL_POS_INTERVENCAO = 2.0;

    private String tipoEquipamento;

    public RocadaMecanizada(
            TrechoRodovia trechoAlvo,
            String tipoEquipamento) {

        super("Rocada Mecanizada", trechoAlvo);

        this.tipoEquipamento = tipoEquipamento;
    }

    @Override
    public void executarServico() {

        System.out.println(
                "Tipo: Rocada Mecanizada"
        );

        System.out.println(
                "Equipamento utilizado: "
                        + tipoEquipamento
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

    public String getTipoEquipamento() {
        return this.tipoEquipamento;
    }
}