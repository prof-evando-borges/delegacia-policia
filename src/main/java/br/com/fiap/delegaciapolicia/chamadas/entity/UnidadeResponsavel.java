package br.com.fiap.delegaciapolicia.chamadas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_CHM_UNIDADE_RESPONSAVEL")
@Getter
@Setter
@NoArgsConstructor
public class UnidadeResponsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 36)
    private String id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "UNIDADE", nullable = false, length = 100)
    private String unidade;

    @Size(max = 500)
    @Column(name = "DESCRICAO", length = 500)
    private String descricao;

    @Size(max = 20)
    @Column(name = "TELEFONE", length = 20)
    private String telefone;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "DISPONIBILIDADE", nullable = false, length = 20)
    private Disponibilidade disponibilidade = Disponibilidade.DISPONIVEL;

    @OneToMany(mappedBy = "unidadeResponsavel")
    private List<Chamada> chamadasVinculadas = new ArrayList<>();
}
