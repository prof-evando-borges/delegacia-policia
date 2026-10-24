package br.com.fiap.delegaciapolicia.rondas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_RND_RONDA")
public class Ronda {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 36, nullable = false)
    private String id;

    @NotNull
    @Column(name = "DATA_INICIO", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "DATA_FIM")
    private LocalDate dataFim;

    @Size(max = 5)
    @Column(name = "HORA_INICIO", length = 5)
    private String horaInicio;

    @Size(max = 5)
    @Column(name = "HORA_FIM", length = 5)
    private String horaFim;

    @NotBlank
    @Size(max = 36)
    @Column(name = "ID_SETOR", nullable = false, length = 36)
    private String idSetor;

    @Size(max = 4000)
    @Column(name = "OBSERVACOES", length = 4000)
    private String observacoes;

    @OneToMany(mappedBy = "ronda")
    private List<RondaViatura> viaturas = new ArrayList<>();

    @OneToMany(mappedBy = "ronda")
    private List<RondaPolicial> policiais = new ArrayList<>();

    public Ronda() {}
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
    public String getHoraInicio() { return horaInicio; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }
    public String getHoraFim() { return horaFim; }
    public void setHoraFim(String horaFim) { this.horaFim = horaFim; }
    public String getIdSetor() { return idSetor; }
    public void setIdSetor(String idSetor) { this.idSetor = idSetor; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public List<RondaViatura> getViaturas() { return viaturas; }
    public List<RondaPolicial> getPoliciais() { return policiais; }
}
