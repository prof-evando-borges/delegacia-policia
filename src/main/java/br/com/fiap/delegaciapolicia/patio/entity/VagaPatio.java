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

    @NotBlank(message = "O código da vaga é obrigatório")
    @Size(max = 10, message = "O código da vaga deve ter no máximo 10 caracteres")
    @Column(name = "CODIGO", nullable = false, length = 10)
    private String codigo;

    @NotBlank(message = "O setor da vaga é obrigatório")
    @Size(max = 30, message = "O setor deve ter no máximo 30 caracteres")
    @Column(name = "SETOR", nullable = false, length = 30)
    private String setor;

    @Column(name = "OCUPADA", nullable = false)
    private Boolean ocupada = false;

    @Override
    public String toString() {
        return "VagaPatio{id=" + id + ", codigo='" + codigo + "', setor='" + setor + "', ocupada=" + ocupada + "}";
    }
}
