package br.com.fiap.delegaciapolicia.prisoes.entity;

import br.com.fiap.delegaciapolicia.prisoes.entity.enums.PrisaoStatus;
import br.com.fiap.delegaciapolicia.prisoes.entity.enums.TipoPrisao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_PRS_PRISAO")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Prisao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 36)
    private String id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 20)
    private PrisaoStatus status;

    @NotNull
    @Column(name = "DATA_PRISAO", nullable = false)
    private LocalDateTime dataPrisao;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO", nullable = false, length = 20)
    private TipoPrisao tipo;

    @NotBlank
    @Size(max = 200)
    @Column(name = "MOTIVO", nullable = false, length = 200)
    private String motivo;

    // Pessoa presa: cadastro TB_FIC_CRIMINAL do modulo 02 (dono dos dados de pessoa). Outro modulo -> so o id.
    @NotBlank
    @Size(max = 36)
    @Column(name = "ID_CRIMINAL", nullable = false, length = 36)
    private String idCriminal;

    // Policial que realizou a prisao: TB_PLC_POLICIAL do modulo 01. Outro modulo -> so o id.
    @NotBlank
    @Size(max = 36)
    @Column(name = "ID_POLICIAL", nullable = false, length = 36)
    private String idPolicial;

    // Mesmo modulo -> relacionamento JPA
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CELA", nullable = false)
    private Cela cela;

    // Peso e altura no ingresso na prisao (vinham da classe Presidiario)
    @Positive
    @Column(name = "PESO", precision = 5, scale = 2)
    private BigDecimal peso;

    @Positive
    @Column(name = "ALTURA", precision = 3, scale = 2)
    private BigDecimal altura;

    @NotNull
    @Column(name = "REINCIDENTE", nullable = false)
    private Boolean reincidente;
}
