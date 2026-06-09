package br.com.motiva.model;

public class EquipeManutencao {

    private String nome;
    private int quantidadeIntegrantes;

    public EquipeManutencao(String nome, int quantidadeIntegrantes) {
        this.setNome(nome);
        this.setQuantidadeIntegrantes(quantidadeIntegrantes);
    }

    public void atenderTrecho(TrechoRodovia trecho) {

        System.out.println("Equipe " + this.nome
                + " designada para o trecho KM "
                + trecho.getQuilometroInicial()
                + " ate KM "
                + trecho.getQuilometroFinal());

        System.out.println("Nivel de vegetacao encontrado: "
                + trecho.getNivelVegetacao()
                + " cm");
    }

    public void executarIntervencao(IntervencaoOperacional intervencao) {

        System.out.println("Equipe " + this.nome
                + " (" + this.quantidadeIntegrantes
                + " integrantes)");

        intervencao.iniciarIntervencao();
    }

    public String getNome() {
        return this.nome;
    }

    private void setNome(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Erro: O nome da equipe nao pode ser vazio.");
            return;
        }

        this.nome = nome;
    }

    public int getQuantidadeIntegrantes() {
        return this.quantidadeIntegrantes;
    }

    private void setQuantidadeIntegrantes(int quantidadeIntegrantes) {

        if (quantidadeIntegrantes <= 0) {
            System.out.println("Erro: A equipe deve ter ao menos um integrante.");
            return;
        }

        this.quantidadeIntegrantes = quantidadeIntegrantes;
    }
}