package br.com.fiap.delegaciapolicia.prisoes.entity;

import br.com.fiap.delegaciapolicia.prisoes.entity.enums.Genero;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_PRS_PRESIDIARIO")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Presidiario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;

    @Past
    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private Double peso;

    @Column(nullable = false)
    private Double altura;

    @Column(nullable = false)
    private String endereco;

    @OneToMany(mappedBy = "presidiario", fetch = FetchType.LAZY)
    private List<Prisao> prisoes = new ArrayList<>();

    @Transient
    public int getIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}
