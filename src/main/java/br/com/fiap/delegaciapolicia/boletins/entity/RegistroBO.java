package br.com.fiap.delegaciapolicia.boletins.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "TB_BOL_REGISTRO_BO")
public class RegistroBO extends Registro {

    @NotBlank
    @Size(max = 20)
    @Column(name = "NUMERO_BO", length = 20)
    private String numeroBO;

    @NotBlank
    @Size(max = 500)
    @Column(name = "DESCRICAO", length = 500)
    private String descricao;

    @NotBlank
    @Size(max = 200)
    @Column(name = "LOCAL_OCORRENCIA", length = 200)
    private String localOcorrencia;

    @NotNull
    @Column(name = "DATA_OCORRENCIA")
    private LocalDate dataOcorrencia;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ID_TIPO_OCORRENCIA")
    private TipoOcorrencia tipoOcorrencia;


    // Policial atendente: TB_PLC_POLICIAL (modulo 01) -> so o id
    @NotBlank
    @Size(max = 36)
    @Column(name = "ID_POLICIAL", length = 36)
    private String idPolicial;

    
    @Size(max = 36)
    @Column(name = "ID_CRIMINOSO", length = 36)
    private String idCriminoso;

   
    @Size(max = 36)
    @Column(name = "ID_SOLICITANTE", length = 36)
    private String idSolicitante;

    public RegistroBO() {
    }

    public RegistroBO(String numeroBO, String descricao, String localOcorrencia,
                      LocalDate dataOcorrencia, TipoOcorrencia tipoOcorrencia,
                      String idPolicial, String idCriminoso, String idSolicitante) {
        this.numeroBO = numeroBO;
        this.descricao = descricao;
        this.localOcorrencia = localOcorrencia;
        this.dataOcorrencia = dataOcorrencia;
        this.tipoOcorrencia = tipoOcorrencia;
        this.idPolicial = idPolicial;
        this.idCriminoso = idCriminoso;
        this.idSolicitante = idSolicitante;
    }

    public String getNumeroBO() { return numeroBO; }
    public void setNumeroBO(String numeroBO) { this.numeroBO = numeroBO; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getLocalOcorrencia() { return localOcorrencia; }
    public void setLocalOcorrencia(String localOcorrencia) { this.localOcorrencia = localOcorrencia; }
    public LocalDate getDataOcorrencia() { return dataOcorrencia; }
    public void setDataOcorrencia(LocalDate dataOcorrencia) { this.dataOcorrencia = dataOcorrencia; }
    public TipoOcorrencia getTipoOcorrencia() { return tipoOcorrencia; }
    public void setTipoOcorrencia(TipoOcorrencia tipoOcorrencia) { this.tipoOcorrencia = tipoOcorrencia; }
    public String getIdPolicial() { return idPolicial; }
    public void setIdPolicial(String idPolicial) { this.idPolicial = idPolicial; }
    public String getIdCriminoso() { return idCriminoso; }
    public void setIdCriminoso(String idCriminoso) { this.idCriminoso = idCriminoso; }
    public String getIdSolicitante() { return idSolicitante; }
    public void setIdSolicitante(String idSolicitante) { this.idSolicitante = idSolicitante; }
}
