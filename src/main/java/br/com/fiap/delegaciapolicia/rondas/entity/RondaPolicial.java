package br.com.fiap.delegaciapolicia.rondas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TB_RND_RONDA_POLICIAL", uniqueConstraints = @UniqueConstraint(name = "UK_RND_RONDA_POLICIAL_PAR", columnNames = {"ID_RONDA", "ID_POLICIAL"}))
public class RondaPolicial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RONDA", nullable = false, foreignKey = @ForeignKey(name = "FK_RND_RONDA_POLICIAL_RONDA"))
    private Ronda ronda;

    // TB_PLC_POLICIAL pertence a outro módulo: armazenamos apenas seu ID, conforme a atividade.
    @NotNull
    @Column(name = "ID_POLICIAL", nullable = false)
    private Long idPolicial;

    public RondaPolicial() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Ronda getRonda() { return ronda; }
    public void setRonda(Ronda ronda) { this.ronda = ronda; }
    public Long getIdPolicial() { return idPolicial; }
    public void setIdPolicial(Long idPolicial) { this.idPolicial = idPolicial; }
}

