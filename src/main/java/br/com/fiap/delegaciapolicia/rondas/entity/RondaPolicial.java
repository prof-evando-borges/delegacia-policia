package br.com.fiap.delegaciapolicia.rondas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TB_RND_RONDA_POLICIAL", uniqueConstraints = @UniqueConstraint(name = "UK_RND_RONDA_POLICIAL_PAR", columnNames = {"ID_RONDA", "ID_POLICIAL"}))
public class RondaPolicial {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 36, nullable = false)
    private String id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RONDA", nullable = false, foreignKey = @ForeignKey(name = "FK_RND_RONDA_POLICIAL_RONDA"))
    private Ronda ronda;

    // TB_PLC_POLICIAL pertence a outro módulo: armazenamos apenas seu ID, conforme a atividade.
    @NotBlank
    @Size(max = 36)
    @Column(name = "ID_POLICIAL", nullable = false, length = 36)
    private String idPolicial;

    public RondaPolicial() {}
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Ronda getRonda() { return ronda; }
    public void setRonda(Ronda ronda) { this.ronda = ronda; }
    public String getIdPolicial() { return idPolicial; }
    public void setIdPolicial(String idPolicial) { this.idPolicial = idPolicial; }
}

