package br.com.fiap.delegaciapolicia.rondas.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_RND_RONDA_VIATURA", uniqueConstraints = @UniqueConstraint(name = "UK_RND_RONDA_VIATURA_PAR", columnNames = {"ID_RONDA", "ID_VIATURA"}))
public class RondaViatura {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 36, nullable = false)
    private String id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RONDA", nullable = false, foreignKey = @ForeignKey(name = "FK_RND_RONDA_VIATURA_RONDA"))
    private Ronda ronda;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VIATURA", nullable = false, foreignKey = @ForeignKey(name = "FK_RND_RONDA_VIATURA_VIATURA"))
    private Viatura viatura;

    public RondaViatura() {}
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Ronda getRonda() { return ronda; }
    public void setRonda(Ronda ronda) { this.ronda = ronda; }
    public Viatura getViatura() { return viatura; }
    public void setViatura(Viatura viatura) { this.viatura = viatura; }
}
