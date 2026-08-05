package com.trugdz.lembrationboyt.entity;

import com.trugdz.lembrationboyt.enums.StatusHistorico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_histoticos")
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "lembrete_id", nullable = false)
    private Lembrete lembrete;

    @Column(name = "data_envio")
    private LocalDateTime dataEnvio;

    @Column(name = "data_resposta")
    private LocalDateTime dataResposta;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_historico", nullable = false)
    private StatusHistorico status;

    @Column(name = "observacao", length = 1000)
    private String observacao;
}
