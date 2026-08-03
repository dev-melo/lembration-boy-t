package com.trugdz.lembrationboyt.entity;

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
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "telegram_id", nullable = false, unique = true)
    private Long telegramId;

    @Column(nullable = false)
    private String nome;

    private String username;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @PrePersist
    public void prePersist(){
        this.dataCadastro = LocalDateTime.now();
    }

}
