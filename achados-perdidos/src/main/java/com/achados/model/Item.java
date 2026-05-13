package com.achados.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItem;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(name = "data_encontro")
    private LocalDate dataEncontro;

    @Column(columnDefinition = "TEXT")
    private String observacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusEnum status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "categorias_id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "local_encontro_id_local_encontro")
    private LocalEncontro localEncontro;

    @ManyToOne
    @JoinColumn(name = "status_item_id_status_item")
    private StatusItem statusItem;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum StatusEnum {
        PERDIDO("perdido"),
        ENCONTRADO("encontrado"),
        DEVOLVIDO("devolvido");

        private final String value;

        StatusEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
