package br.com.fiap.delegaciapolicia.fichas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_FIC_FICHA")
@Getter
@Setter
@NoArgsConstructor
public class FichaCriminal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 36)
    private String id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CRIMINAL", nullable = false)
    private Criminal criminal; // mesmo módulo → relacionamento

    @NotBlank
    @Size(max = 36)
    @Column(name = "ID_POLICIAL", nullable = false, length = 36)
    private String idPolicial; // outro módulo (TB_PLC_POLICIAL) → só o id

    @NotNull
    @Column(name = "DATA_REGISTRO", nullable = false)
    private LocalDate dataRegistro;

    @NotNull
    @Column(name = "DATA_OCORRENCIA", nullable = false)
    private LocalDate dataOcorrencia;

    @Size(max = 255)
    @Column(name = "LOCAL_CRIME", length = 255)
    private String localCrime;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_INVESTIGACAO", length = 20, nullable = false)
    private StatusInvestigacao statusInvestigacao;

    // Preenchida pelo banco (DEFAULT SYSTIMESTAMP)
    @Column(name = "DATA_CRIACAO", nullable = false, insertable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "ficha")
    private java.util.List<Crime> crimes = new java.util.ArrayList<>();

    public enum StatusInvestigacao {
        ABERTA, EM_ANDAMENTO, ENCERRADA
    }
}
