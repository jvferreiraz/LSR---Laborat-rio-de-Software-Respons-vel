package com.achados.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String acao;

    @Column(name = "data_acao", nullable = false)
    private LocalDateTime dataAcao;

    @ManyToOne
    @JoinColumn(name = "item_id_item")
    private Item item;

    @PrePersist
    protected void onCreate() {
        if (dataAcao == null) {
            dataAcao = LocalDateTime.now();
        }
    }
}
