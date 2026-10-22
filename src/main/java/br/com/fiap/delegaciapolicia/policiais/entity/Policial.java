package br.com.fiap.delegaciapolicia.policiais.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_PLC_POLICIAL")
@Getter
@Setter
@NoArgsConstructor
public class Policial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Size(max = 120)
    @Column(name = "NOME", nullable = false, length = 120)
    private String nome;

    @NotBlank
    @Pattern(regexp = "\\d{11}")
    @Column(name = "CPF", nullable = false, length = 11, unique = true)
    private String cpf;

    @NotBlank
    @Size(max = 20)
    @Column(name = "MATRICULA", nullable = false, length = 20, unique = true)
    private String matricula;

    @NotBlank
    @Size(max = 60)
    @Column(name = "CARGO", nullable = false, length = 60)
    private String cargo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_DELEGACIA", nullable = false)
    private Delegacia delegacia;
}
