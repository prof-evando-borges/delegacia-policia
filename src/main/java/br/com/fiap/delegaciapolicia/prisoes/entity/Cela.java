package br.com.fiap.delegaciapolicia.prisoes.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_PRS_CELA")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Cela {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String pavilhao;

    @Column(nullable = false)
    private String bloco;

    @Column(nullable = false)
    private String numero;

    @OneToMany(mappedBy = "cela", fetch = FetchType.LAZY)
    private List<Prisao> prisoes = new ArrayList<>();
}
