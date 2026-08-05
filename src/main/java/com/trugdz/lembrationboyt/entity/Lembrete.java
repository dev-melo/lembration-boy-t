package com.trugdz.lembrationboyt.entity;

import com.trugdz.lembrationboyt.enums.StatusLembrete;
import com.trugdz.lembrationboyt.enums.TipoLembrete;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_lembretes")
public class Lembrete {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 500)
    private String mensagem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoLembrete tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status_lembrete")
    private StatusLembrete status;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fim")
    private LocalTime horaFim;

    @Column(name = "intervalo_minutos", nullable = false)
    private Integer intervaloMinutos;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "lembrete", fetch = FetchType.LAZY)
    private List<Historico> historicos;

    @PrePersist
    public void prePersist(){
        this.dataCriacao = LocalDateTime.now();
    }

}
