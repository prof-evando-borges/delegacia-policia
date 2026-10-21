package br.com.fiap.delegaciapolicia.chamadas.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 36)
    private String id;

    @NotBlank
    @Size(max = 36)
    @Column(name = "PROTOCOLO", nullable = false, length = 36, unique = true)
    private String protocolo;

    @NotBlank
    @Size(max = 100)
    @Column(name = "NATUREZA", nullable = false, length = 100)
    private String natureza;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_SOLICITANTE", nullable = false)
    private Solicitante solicitante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ATENDENTE")
    private Atendente atendente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_UNIDADE_RESPONSAVEL")
    private UnidadeResponsavel unidadeResponsavel;

    @NotBlank
    @Lob
    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @NotBlank
    @Size(max = 255)
    @Column(name = "ENDERECO", nullable = false, length = 255)
    private String endereco;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 30)
    private StatusChamada status = StatusChamada.ABERTA;

    @NotNull
    @Column(name = "DATA_HORA", nullable = false)
    private LocalDateTime dataHora;

    @Lob
    @Column(name = "DESFECHO")
    private String desfecho;
}
