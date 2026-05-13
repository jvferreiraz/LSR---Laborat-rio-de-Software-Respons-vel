package com.achados.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocalEncontroDTO {
    private Integer idLocalEncontro;
    private String nome;
    private String endereco;

    public static LocalEncontroDTO fromEntity(com.achados.model.LocalEncontro local) {
        return LocalEncontroDTO.builder()
                .idLocalEncontro(local.getIdLocalEncontro())
                .nome(local.getNome())
                .endereco(local.getEndereco())
                .build();
    }
}
