package br.com.fiap.delegaciapolicia.chamadas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    // Policial é do módulo 01 (policiais): guardamos só o id, sem herança nem @ManyToOne
    @NotNull
    @Column(name = "ID_POLICIAL", nullable = false)
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
