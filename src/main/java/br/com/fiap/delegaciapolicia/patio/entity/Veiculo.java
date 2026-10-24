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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 36)
    private String id;

    @NotBlank
    @Size(max = 7)
    @Column(name = "PLACA", nullable = false, length = 7)
    private String placa;

    @NotBlank
    @Size(max = 40)
    @Column(name = "MARCA", nullable = false, length = 40)
    private String marca;

    @NotBlank
    @Size(max = 60)
    @Column(name = "MODELO", nullable = false, length = 60)
    private String modelo;

    @NotBlank
    @Size(max = 30)
    @Column(name = "COR", nullable = false, length = 30)
    private String cor;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO", nullable = false, length = 20)
    private TipoVeiculo tipo;

    @NotNull
    @Column(name = "DATA_ENTRADA", nullable = false)
    private LocalDate dataEntrada;

    @Column(name = "DATA_SAIDA")
    private LocalDate dataSaida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VAGA_PATIO")
    private VagaPatio vaga;

    // Tabela de outro modulo (07-boletins, TB_BOL_REGISTRO_BO): guarda so o id
    @Column(name = "ID_REGISTRO", length = 36)
    private String idRegistro;
}
