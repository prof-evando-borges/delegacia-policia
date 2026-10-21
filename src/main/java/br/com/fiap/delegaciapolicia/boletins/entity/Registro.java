package br.com.fiap.delegaciapolicia.boletins.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

// Superclasse do projeto original, agora mapeada como @MappedSuperclass:
// nao vira tabela, so entrega o atributo id para as filhas.
@MappedSuperclass
public abstract class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}
