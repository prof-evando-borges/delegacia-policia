package br.com.fiap.delegaciapolicia.patio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "TB_PAT_VEICULO")
@Getter
@Setter
@NoArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PLACA", nullable = false, length = 7)
    private String placa;

    @Column(name = "MARCA", nullable = false, length = 40)
    private String marca;

    @Column(name = "MODELO", nullable = false, length = 60)
    private String modelo;

    @Column(name = "COR", nullable = false, length = 30)
    private String cor;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO", nullable = false, length = 20)
    private TipoVeiculo tipo;

    @Column(name = "DATA_ENTRADA", nullable = false)
    private LocalDate dataEntrada;

    @Column(name = "DATA_SAIDA")
    private LocalDate dataSaida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VAGA_PATIO")
    private VagaPatio vaga;

    // Falta criar o atributo Registro

    // private Registro registro
}
