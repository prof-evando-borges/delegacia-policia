package br.com.fiap.delegaciapolicia.prisoes.domain.entity;

import br.com.fiap.delegaciapolicia.policiais.entity.Policial;
import br.com.fiap.delegaciapolicia.prisoes.domain.enums.PrisaoStatus;
import br.com.fiap.delegaciapolicia.prisoes.domain.enums.TipoPrisao;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_PRS_PRISAO")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Prisao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PrisaoStatus status;

    @Column(nullable = false)
    private LocalDateTime dataPrisao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPrisao tipo;

    @Column(nullable = false, length = 1000)
    private String motivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policial_id", nullable = false)
    private Policial policial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preso_id", nullable = false)
    private Presidiario presidiario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cela_id", nullable = false)
    private Cela cela;

    @Column(nullable = false)
    private boolean reincidente;
}
