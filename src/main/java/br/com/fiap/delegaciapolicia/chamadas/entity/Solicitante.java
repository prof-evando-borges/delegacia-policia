package br.com.fiap.delegaciapolicia.chamadas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_CHM_SOLICITANTE")
@Getter
@Setter
@NoArgsConstructor
public class Solicitante {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 36)
    private String id;

    @NotBlank
    @Size(max = 150)
    @Column(name = "NOME", nullable = false, length = 150)
    private String nome;

    @NotBlank
    @Size(max = 20)
    @Column(name = "TELEFONE", nullable = false, length = 20)
    private String telefone;
}
