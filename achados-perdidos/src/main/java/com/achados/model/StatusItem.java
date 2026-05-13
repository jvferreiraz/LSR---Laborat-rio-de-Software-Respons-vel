package com.achados.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "status_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStatusItem;

    @Column(nullable = false, length = 45)
    private String nome;
}
