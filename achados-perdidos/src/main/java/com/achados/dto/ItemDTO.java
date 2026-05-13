package com.achados.dto;

import com.achados.model.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO {
    private Integer idItem;
    private String descricao;
    private LocalDate dataEncontro;
    private String observacao;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer categoriaId;
    private String categoriaNome;
    private Integer localEncontroId;
    private String localEncontroNome;
    private Integer statusItemId;
    private String statusItemNome;

    public static ItemDTO fromEntity(com.achados.model.Item item) {
        return ItemDTO.builder()
                .idItem(item.getIdItem())
                .descricao(item.getDescricao())
                .dataEncontro(item.getDataEncontro())
                .observacao(item.getObservacao())
                .status(item.getStatus() != null ? item.getStatus().getValue() : null)
                .createdAt(item.getCreatedAt())
                .updatedAt(item.getUpdatedAt())
                .categoriaId(item.getCategoria() != null ? item.getCategoria().getIdCategoria() : null)
                .categoriaNome(item.getCategoria() != null ? item.getCategoria().getNome() : null)
                .localEncontroId(item.getLocalEncontro() != null ? item.getLocalEncontro().getIdLocalEncontro() : null)
                .localEncontroNome(item.getLocalEncontro() != null ? item.getLocalEncontro().getNome() : null)
                .statusItemId(item.getStatusItem() != null ? item.getStatusItem().getIdStatusItem() : null)
                .statusItemNome(item.getStatusItem() != null ? item.getStatusItem().getNome() : null)
                .build();
    }
}
