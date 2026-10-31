package br.com.fiap.delegaciapolicia.estoque.entity;

import java.time.LocalDateTime;

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
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_EST_MOVIMENTACAO")
@Getter
@Setter
@NoArgsConstructor
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 36)
    private String id;

    @NotNull(message = "O item da movimentação é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_ITEM", nullable = false)
    private ItemEstoque item;

    @NotBlank(message = "O id do policial responsável é obrigatório")
    @Size(max = 36, message = "O id do policial deve ter no máximo 36 caracteres")
    @Column(name = "ID_POLICIAL", nullable = false, length = 36)
    private String idPolicial;

    @NotNull(message = "O tipo da movimentação é obrigatório (ENTRADA ou SAIDA)")
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO", nullable = false, length = 10)
    private TipoMovimentacao tipo;

    @NotNull(message = "A quantidade é obrigatória")
    @Positive(message = "A quantidade deve ser maior que zero")
    @Column(name = "QUANTIDADE", nullable = false)
    private Integer quantidade;

    @Column(name = "DATA_MOVIMENTACAO", nullable = false)
    private LocalDateTime dataMovimentacao = LocalDateTime.now();

    @Size(max = 255, message = "A observação deve ter no máximo 255 caracteres")
    @Column(name = "OBSERVACAO", length = 255)
    private String observacao;
}
