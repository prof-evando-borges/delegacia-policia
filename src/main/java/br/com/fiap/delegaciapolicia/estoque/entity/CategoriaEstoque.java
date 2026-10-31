package br.com.fiap.delegaciapolicia.estoque.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_EST_CATEGORIA")
@Getter
@Setter
@NoArgsConstructor
public class CategoriaEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 36)
    private String id;

    @NotBlank(message = "O nome da categoria é obrigatório")
    @Size(max = 50, message = "O nome da categoria deve ter no máximo 50 caracteres")
    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;

    @Size(max = 200, message = "A descrição da categoria deve ter no máximo 200 caracteres")
    @Column(name = "DESCRICAO", length = 200)
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria")
    private List<ItemEstoque> itens = new ArrayList<>();
}
