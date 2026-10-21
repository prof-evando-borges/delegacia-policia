package br.com.fiap.delegaciapolicia.chamadas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    @Size(max = 20)
    @Column(name = "MATRICULA", nullable = false, length = 20, unique = true)
    private String matricula;

    @NotBlank
    @Size(max = 20)
    @Column(name = "TURNO", nullable = false, length = 20)
    private String turno;
}
