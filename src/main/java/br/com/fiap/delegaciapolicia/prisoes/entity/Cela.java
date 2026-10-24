package br.com.fiap.delegaciapolicia.prisoes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_PRS_CELA")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Cela {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 36)
    private String id;

    @NotBlank
    @Size(max = 10)
    @Column(name = "PAVILHAO", nullable = false, length = 10)
    private String pavilhao;

    @NotBlank
    @Size(max = 10)
    @Column(name = "BLOCO", nullable = false, length = 10)
    private String bloco;

    @NotBlank
    @Size(max = 10)
    @Column(name = "NUMERO", nullable = false, length = 10)
    private String numero;

    // O preso que ocupa a cela vem pelas prisoes (TB_PRS_PRISAO.ID_CELA)
    @OneToMany(mappedBy = "cela", fetch = FetchType.LAZY)
    private List<Prisao> prisoes = new ArrayList<>();
}
