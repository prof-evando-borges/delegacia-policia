package br.com.fiap.delegaciapolicia.rondas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_RND_VIATURA")
public class Viatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotBlank @Size(max = 10)
    @Column(name = "PREFIXO", length = 10, nullable = false, unique = true)
    private String prefixo;

    @NotBlank @Size(max = 10)
    @Column(name = "PLACA", length = 10, nullable = false, unique = true)
    private String placa;

    @Size(max = 50)
    @Column(name = "MODELO", length = 50)
    private String modelo;

    @NotNull @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 20, nullable = false)
    private ViaturaStatus status = ViaturaStatus.DISPONIVEL;

    @OneToMany(mappedBy = "viatura")
    private List<RondaViatura> participacoes = new ArrayList<>();

    public Viatura() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPrefixo() { return prefixo; }
    public void setPrefixo(String prefixo) { this.prefixo = prefixo; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public ViaturaStatus getStatus() { return status; }
    public void setStatus(ViaturaStatus status) { this.status = status; }
    public List<RondaViatura> getParticipacoes() { return participacoes; }
}