package br.com.motiva.model;

public abstract class IntervencaoOperacional {

    private String descricao;
    private TrechoRodovia trechoAlvo;

    public IntervencaoOperacional(String descricao, TrechoRodovia trechoAlvo) {

        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Descricao da intervencao nao pode ser vazia."
            );
        }

        if (trechoAlvo == null) {
            throw new IllegalArgumentException(
                    "O trecho da intervencao nao pode ser nulo."
            );
        }

        this.descricao = descricao;
        this.trechoAlvo = trechoAlvo;
    }

    public abstract void executarServico();

    public void iniciarIntervencao() {

        System.out.println(
                "Iniciando intervencao: " + this.descricao
        );

        System.out.println(
                "Trecho: KM "
                        + trechoAlvo.getQuilometroInicial()
                        + " ao KM "
                        + trechoAlvo.getQuilometroFinal()
        );

        executarServico();

        System.out.println("Intervencao concluida.");
    }

    public String getDescricao() {
        return this.descricao;
    }

    public TrechoRodovia getTrechoAlvo() {
        return this.trechoAlvo;
    }
}
