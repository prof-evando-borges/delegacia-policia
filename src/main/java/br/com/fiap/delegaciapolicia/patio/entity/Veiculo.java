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
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "A placa é obrigatória")
    @Pattern(regexp = "^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$",
        message = "A placa deve estar no formato ABC1234 ou ABC1D23, com letras maiúsculas")
    @Column(name = "PLACA", nullable = false, length = 7)
    private String placa;

    @NotBlank(message = "A marca é obrigatória")
    @Size(max = 40, message = "A marca deve ter no máximo 40 caracteres")
    @Column(name = "MARCA", nullable = false, length = 40)
    private String marca;

    @NotBlank(message = "O modelo é obrigatório")
    @Size(max = 60, message = "O modelo deve ter no máximo 60 caracteres")
    @Column(name = "MODELO", nullable = false, length = 60)
    private String modelo;

    @NotBlank(message = "A cor é obrigatória")
    @Size(max = 30, message = "A cor deve ter no máximo 30 caracteres")
    @Column(name = "COR", nullable = false, length = 30)
    private String cor;

    @NotNull(message = "O tipo do veículo é obrigatório (CARRO, MOTO ou CAMINHAO)")
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO", nullable = false, length = 20)
    private TipoVeiculo tipo;

    @NotNull(message = "A data de entrada é obrigatória")
    @PastOrPresent(message = "A data de entrada não pode estar no futuro")
    @Column(name = "DATA_ENTRADA", nullable = false)
    private LocalDate dataEntrada;

    @PastOrPresent(message = "A data de saída não pode estar no futuro")
    @Column(name = "DATA_SAIDA")
    private LocalDate dataSaida;

    // EAGER: a vaga aparece no JSON e o open-in-view esta desligado
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VAGA_PATIO")
    private VagaPatio vaga;

    // Tabela de outro modulo (07-boletins, TB_BOL_REGISTRO_BO): guarda so o id
    @Size(max = 36, message = "O id do registro de BO deve ter no máximo 36 caracteres")
    @Column(name = "ID_REGISTRO", length = 36)
    private String idRegistro;

    @Override
    public String toString() {
        return "Veiculo{" +
            "id='" + id + '\'' +
            ", placa='" + placa + '\'' +
            ", marca='" + marca + '\'' +
            ", modelo='" + modelo + '\'' +
            ", cor='" + cor + '\'' +
            ", tipo=" + tipo +
            ", dataEntrada=" + dataEntrada +
            ", dataSaida=" + dataSaida +
            ", vaga=" + vaga +
            ", idRegistro='" + idRegistro + '\'' +
            '}';
    }
}
