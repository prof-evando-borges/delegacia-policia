package br.com.fiap.delegaciapolicia.estoque.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_EST_ITEM")
@Getter
@Setter
@NoArgsConstructor
public class ItemEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 36)
    private String id;

    @NotNull(message = "A categoria do item é obrigatória")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_CATEGORIA", nullable = false)
    private CategoriaEstoque categoria;

    @NotBlank(message = "O código do item é obrigatório")
    @Size(max = 30, message = "O código do item deve ter no máximo 30 caracteres")
    @Column(name = "CODIGO", nullable = false, unique = true, length = 30)
    private String codigo;

    @NotBlank(message = "O nome do item é obrigatório")
    @Size(max = 100, message = "O nome do item deve ter no máximo 100 caracteres")
    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Lob
    @Column(name = "DESCRICAO")
    private String descricao;

    @Size(max = 100, message = "A localização deve ter no máximo 100 caracteres")
    @Column(name = "LOCALIZACAO", length = 100)
    private String localizacao;

    @NotNull(message = "A quantidade é obrigatória")
    @PositiveOrZero(message = "A quantidade não pode ser negativa")
    @Column(name = "QUANTIDADE", nullable = false)
    private Integer quantidade = 0;

    @NotNull(message = "O valor unitário é obrigatório")
    @PositiveOrZero(message = "O valor unitário não pode ser negativo")
    @Column(name = "VALOR_UNITARIO", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorUnitario = BigDecimal.ZERO;

    @NotNull(message = "Informe se o item está ativo")
    @Column(name = "ATIVO", nullable = false)
    private Boolean ativo = true;

    @JsonIgnore
    @OneToMany(mappedBy = "item")
    private List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();
}
