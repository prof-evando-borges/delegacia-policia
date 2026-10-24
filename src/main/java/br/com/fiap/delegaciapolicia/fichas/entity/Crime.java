package br.com.fiap.delegaciapolicia.fichas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_FIC_CRIME")
@Getter
@Setter
@NoArgsConstructor
public class Crime {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 36)
    private String id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_FICHA", nullable = false)
    private FichaCriminal ficha;

    @NotBlank
    @Lob
    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @NotBlank
    @Size(max = 60)
    @Column(name = "TIPO", length = 60, nullable = false)
    private String tipo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "GRAVIDADE", length = 10, nullable = false)
    private Gravidade gravidade;

    // Preenchida pelo banco (DEFAULT SYSTIMESTAMP)
    @Column(name = "DATA_CRIACAO", nullable = false, insertable = false, updatable = false)
    private LocalDateTime dataCriacao;

    public enum Gravidade {
        LEVE, MEDIA, GRAVE
    }
}
