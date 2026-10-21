package br.com.fiap.delegaciapolicia.boletins.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TB_BOL_TIPO_OCORRENCIA")
public class TipoOcorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @NotBlank
    @Size(max = 80)
    @Column(name = "NOME", length = 80)
    private String nome;

    @Size(max = 200)
    @Column(name = "DESCRICAO", length = 200)
    private String descricao;

    public TipoOcorrencia() {
    }

    public TipoOcorrencia(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
