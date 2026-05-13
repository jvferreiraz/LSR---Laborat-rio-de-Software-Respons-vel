package com.achados.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "local_encontro")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocalEncontro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLocalEncontro;

    @Column(nullable = false, length = 45)
    private String nome;

    @Column(length = 45)
    private String endereco;
}
