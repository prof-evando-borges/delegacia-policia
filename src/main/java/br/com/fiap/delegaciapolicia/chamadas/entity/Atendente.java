package br.com.fiap.delegaciapolicia.chamadas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_CHM_ATENDENTE")
@Getter
@Setter
@NoArgsConstructor
public class Atendente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 36)
    private String id;

    @NotNull
    @Column(name = "ID_POLICIAL", nullable = false, unique = true)
    private Long idPolicial;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TURNO", nullable = false, length = 20)
    private Turno turno;
}
