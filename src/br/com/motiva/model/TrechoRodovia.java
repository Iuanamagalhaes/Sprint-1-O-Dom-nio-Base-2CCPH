package br.com.motiva.model;

public class TrechoRodovia implements MonitoravelViaIoT {

    public enum TipoAmbiente {

        UMIDO(1.8, "Umido"),
        SECO(0.7, "Seco"),
        TROPICAL(1.4, "Tropical"),
        PADRAO(1.0, "Padrao");

        private double fatorCrescimento;
        private String descricao;

        TipoAmbiente(
                double fatorCrescimento,
                String descricao) {

            this.fatorCrescimento = fatorCrescimento;
            this.descricao = descricao;
        }

        public double getFatorCrescimento() {
            return this.fatorCrescimento;
        }

        public String getDescricao() {
            return this.descricao;
        }
    }

    private double quilometroInicial;
    private double quilometroFinal;
    private double nivelVegetacao;
    private TipoAmbiente tipoAmbiente;
    private boolean possuiSensorIoT;

    public TrechoRodovia(
            double quilometroInicial,
            double quilometroFinal,
            double nivelVegetacao) {

        this(
                quilometroInicial,
                quilometroFinal,
                nivelVegetacao,
                TipoAmbiente.PADRAO,
                false
        );
    }

    public TrechoRodovia(
            double quilometroInicial,
            double quilometroFinal,
            double nivelVegetacao,
            TipoAmbiente tipoAmbiente) {

        this(
                quilometroInicial,
                quilometroFinal,
                nivelVegetacao,
                tipoAmbiente,
                false
        );
    }

    public TrechoRodovia(
            double quilometroInicial,
            double quilometroFinal,
            double nivelVegetacao,
            TipoAmbiente tipoAmbiente,
            boolean possuiSensorIoT) {

        this.setQuilometroInicial(quilometroInicial);
        this.setQuilometroFinal(quilometroFinal);
        this.setNivelVegetacao(nivelVegetacao);

        this.tipoAmbiente = tipoAmbiente;
        this.possuiSensorIoT = possuiSensorIoT;
    }

    public void registrarCrescimento(double taxaBase) {

        if (taxaBase < 0) {
            System.out.println(
                    "Erro: A taxa de crescimento nao pode ser negativa."
            );
            return;
        }

        double taxaReal =
                taxaBase
                        * this.tipoAmbiente.getFatorCrescimento();

        this.nivelVegetacao += taxaReal;

        System.out.println(
                "Crescimento registrado: "
                        + taxaReal
                        + " cm"
        );

        System.out.println(
                "Nivel atual: "
                        + this.nivelVegetacao
                        + " cm"
        );
    }

    @Override
    public void transmitirDadosSensor(
            double leituraDoSensor) {

        if (!this.possuiSensorIoT) {

            System.out.println(
                    "Trecho nao possui sensor IoT."
            );

            return;
        }

        if (leituraDoSensor < 0) {

            System.out.println(
                    "Leitura invalida recebida do sensor."
            );

            return;
        }

        this.nivelVegetacao =
                leituraDoSensor;

        System.out.println(
                "Leitura recebida do sensor: "
                        + leituraDoSensor
                        + " cm"
        );
    }

    @Override
    public boolean isSensorAtivo() {
        return this.possuiSensorIoT;
    }

    public double getQuilometroInicial() {
        return this.quilometroInicial;
    }

    private void setQuilometroInicial(
            double quilometroInicial) {

        if (quilometroInicial < 0) {

            System.out.println(
                    "Erro: O quilometro inicial nao pode ser negativo."
            );

            return;
        }

        this.quilometroInicial =
                quilometroInicial;
    }

    public double getQuilometroFinal() {
        return this.quilometroFinal;
    }

    private void setQuilometroFinal(
            double quilometroFinal) {

        if (quilometroFinal
                <= this.quilometroInicial) {

            System.out.println(
                    "Erro: O quilometro final deve ser maior que o inicial."
            );

            return;
        }

        this.quilometroFinal =
                quilometroFinal;
    }

    public double getNivelVegetacao() {
        return this.nivelVegetacao;
    }

    public void setNivelVegetacao(
            double nivelVegetacao) {

        if (nivelVegetacao < 0) {

            System.out.println(
                    "Erro: Tentativa de definir vegetacao negativa."
            );

            return;
        }

        this.nivelVegetacao =
                nivelVegetacao;
    }

    public TipoAmbiente getTipoAmbiente() {
        return this.tipoAmbiente;
    }

    public boolean isPossuiSensorIoT() {
        return this.possuiSensorIoT;
    }
}