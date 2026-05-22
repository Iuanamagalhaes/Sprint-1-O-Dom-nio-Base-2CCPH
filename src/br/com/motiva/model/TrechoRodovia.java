package br.com.motiva.model;

public class TrechoRodovia {

    // Atributos
    private double quilometroInicial;
    private double quilometroFinal;
    private double nivelVegetacao; // em centímetros

    public TrechoRodovia(double quilometroInicial, double quilometroFinal, double nivelVegetacao) {
        this.setQuilometroInicial(quilometroInicial);
        this.setQuilometroFinal(quilometroFinal);
        this.setNivelVegetacao(nivelVegetacao);
    }

    public void registrarCrescimento(double taxa) {
        if (taxa < 0) {
            System.out.println("Erro: A taxa de crescimento não pode ser negativa.");
            return;
        }
        this.nivelVegetacao += taxa;
        System.out.println("Crescimento registrado. Novo nível: " + this.nivelVegetacao + " cm");
    }

    public double getQuilometroInicial() {
        return this.quilometroInicial;
    }

    private void setQuilometroInicial(double quilometroInicial) {
        if (quilometroInicial < 0) {
            System.out.println("Erro: O quilômetro inicial não pode ser negativo.");
            return;
        }
        this.quilometroInicial = quilometroInicial;
    }

    public double getQuilometroFinal() {
        return this.quilometroFinal;
    }

    private void setQuilometroFinal(double quilometroFinal) {
        if (quilometroFinal <= this.quilometroInicial) {
            System.out.println("Erro: O quilômetro final deve ser maior que o inicial.");
            return;
        }
        this.quilometroFinal = quilometroFinal;
    }

    public double getNivelVegetacao() {
        return this.nivelVegetacao;
    }

    public void setNivelVegetacao(double nivelVegetacao) {
        if (nivelVegetacao < 0) {
            System.out.println("Erro de Segurança: Tentativa de definir vegetação negativa bloqueada!");
            return;
        }
        this.nivelVegetacao = nivelVegetacao;
    }
}