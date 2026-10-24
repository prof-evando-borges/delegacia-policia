package br.com.fiap.delegaciapolicia.policiais.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_PLC_ENDERECO")
@Getter
@Setter
@NoArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 36)
    private String id;

    @NotBlank
    @Size(max = 150)
    @Column(name = "RUA", nullable = false, length = 150)
    private String rua;

    @NotNull
    @Positive
    @Column(name = "NUMERO", nullable = false, precision = 19)
    private Long numero;

    @NotBlank
    @Pattern(regexp = "\\d{8}")
    @Column(name = "CEP", nullable = false, length = 8)
    private String cep;

    @Size(max = 100)
    @Column(name = "COMPLEMENTO", length = 100)
    private String complemento;
}
