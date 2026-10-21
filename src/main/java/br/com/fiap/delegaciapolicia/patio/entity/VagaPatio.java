package br.com.fiap.delegaciapolicia.patio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODIGO", nullable = false, length = 10)
    private String codigo;

    @Column(name = "SETOR", nullable = false, length = 30)
    private String setor;

    @Column(name = "OCUPADA", nullable = false)
    private Boolean ocupada;

    @Override
    public String toString() {
        return "VagaPatio{id=" + id + ", codigo='" + codigo + "', setor='" + setor + "', ocupada=" + ocupada + "}";
    }
}
