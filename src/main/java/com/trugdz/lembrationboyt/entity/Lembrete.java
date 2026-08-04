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

    private String mensagem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoLembrete tipo;

    @Enumerated(EnumType.STRING)
    private StatusLembrete status;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fim")
    private LocalTime horaFim;

    @Column(name = "intervalo_minutos")
    private Integer intervaloMinutos;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @PrePersist
    public void prePersist(){
        this.dataCriacao = LocalDateTime.now();
    }

}
