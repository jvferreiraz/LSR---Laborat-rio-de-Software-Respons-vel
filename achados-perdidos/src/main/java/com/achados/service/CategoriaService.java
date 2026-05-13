package com.achados.service;

import com.achados.dto.CategoriaDTO;
import com.achados.model.Categoria;
import com.achados.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<CategoriaDTO> listarTodas() {
        return categoriaRepository.findAll().stream()
                .map(CategoriaDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<CategoriaDTO> obterPorId(Integer id) {
        return categoriaRepository.findById(id).map(CategoriaDTO::fromEntity);
    }

    @Transactional
    public CategoriaDTO criar(CategoriaDTO dto) {
        Categoria categoria = Categoria.builder()
                .nome(dto.getNome())
                .build();
        return CategoriaDTO.fromEntity(categoriaRepository.save(categoria));
    }

    @Transactional
    public CategoriaDTO atualizar(Integer id, CategoriaDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        categoria.setNome(dto.getNome());
        return CategoriaDTO.fromEntity(categoriaRepository.save(categoria));
    }

    @Transactional
    public void deletar(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
