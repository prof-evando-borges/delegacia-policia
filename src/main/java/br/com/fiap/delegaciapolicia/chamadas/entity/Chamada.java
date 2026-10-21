package br.com.fiap.delegaciapolicia.chamadas.entity;

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

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_CHM_CHAMADA")
@Getter
@Setter
@NoArgsConstructor
public class Chamada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(name = "PROTOCOLO", nullable = false, length = 30, unique = true)
    private String protocolo;

    @NotBlank
    @Size(max = 60)
    @Column(name = "NATUREZA", nullable = false, length = 60)
    private String natureza;

    // Vítima/solicitante é de outro módulo: guardamos só o id
    @NotNull
    @Column(name = "ID_SOLICITANTE", nullable = false)
    private Long idSolicitante;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_ATENDENTE", nullable = false)
    private Atendente atendente;

    @NotBlank
    @Size(max = 500)
    @Column(name = "DESCRICAO", nullable = false, length = 500)
    private String descricao;

    @NotBlank
    @Size(max = 200)
    @Column(name = "ENDERECO", nullable = false, length = 200)
    private String endereco;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 20)
    private StatusChamada status;

    @NotNull
    @Column(name = "DATA_HORA", nullable = false)
    private LocalDateTime dataHora;

    @Size(max = 500)
    @Column(name = "DESFECHO", length = 500)
    private String desfecho;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_UNIDADE_RESPONSAVEL")
    private UnidadeResponsavel unidadeResponsavel;
}
