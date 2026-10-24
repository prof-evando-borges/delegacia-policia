package br.com.fiap.delegaciapolicia.fichas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_FIC_CRIMINAL")
public class Criminal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 36)
    private String id;

    @NotBlank
    @Size(max = 150)
    @Column(name = "NOME", length = 150, nullable = false)
    private String nome;

    @NotBlank
    @Size(min = 11, max = 11)
    @Column(name = "CPF", length = 11, nullable = false, unique = true)
    private String cpf;

    @NotNull
    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private LocalDate dataNascimento;

    @Size(max = 20)
    @Column(name = "GENERO", length = 20)
    private String genero;

    @Size(max = 50)
    @Column(name = "NACIONALIDADE", length = 50)
    private String nacionalidade;

    @Size(max = 255)
    @Column(name = "ENDERECO", length = 255)
    private String endereco;

    @Size(max = 20)
    @Column(name = "TELEFONE", length = 20)
    private String telefone;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;

    
}