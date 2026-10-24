package br.com.fiap.delegaciapolicia.patio.entity;

public enum TipoVeiculo {
    CARRO {
        @Override
        public double calcularTaxa() { return 70.0; }
    },
    MOTO {
        @Override
        public double calcularTaxa() { return 50.0; }
    },
    CAMINHAO {
        @Override
        public double calcularTaxa() { return 100.0; }
    };

    public abstract double calcularTaxa();
}
