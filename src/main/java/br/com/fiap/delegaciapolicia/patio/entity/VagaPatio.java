package br.com.fiap.delegaciapolicia.patio.entity;

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
@Table(name = "TB_PAT_VAGA_PATIO")
@Getter
@Setter
@NoArgsConstructor
public class VagaPatio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 36)
    private String id;

    @NotBlank
    @Size(max = 10)
    @Column(name = "CODIGO", nullable = false, length = 10)
    private String codigo;

    @NotBlank
    @Size(max = 30)
    @Column(name = "SETOR", nullable = false, length = 30)
    private String setor;

    @NotNull
    @Column(name = "OCUPADA", nullable = false)
    private Boolean ocupada;

    @Override
    public String toString() {
        return "VagaPatio{id=" + id + ", codigo='" + codigo + "', setor='" + setor + "', ocupada=" + ocupada + "}";
    }
}
