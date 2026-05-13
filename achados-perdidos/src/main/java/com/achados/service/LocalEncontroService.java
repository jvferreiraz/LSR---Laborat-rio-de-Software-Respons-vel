package com.achados.service;

import com.achados.dto.LocalEncontroDTO;
import com.achados.model.LocalEncontro;
import com.achados.repository.LocalEncontroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocalEncontroService {
    private final LocalEncontroRepository localEncontroRepository;

    @Transactional(readOnly = true)
    public List<LocalEncontroDTO> listarTodos() {
        return localEncontroRepository.findAll().stream()
                .map(LocalEncontroDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<LocalEncontroDTO> obterPorId(Integer id) {
        return localEncontroRepository.findById(id).map(LocalEncontroDTO::fromEntity);
    }

    @Transactional
    public LocalEncontroDTO criar(LocalEncontroDTO dto) {
        LocalEncontro local = LocalEncontro.builder()
                .nome(dto.getNome())
                .endereco(dto.getEndereco())
                .build();
        return LocalEncontroDTO.fromEntity(localEncontroRepository.save(local));
    }

    @Transactional
    public LocalEncontroDTO atualizar(Integer id, LocalEncontroDTO dto) {
        LocalEncontro local = localEncontroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Local não encontrado"));
        local.setNome(dto.getNome());
        local.setEndereco(dto.getEndereco());
        return LocalEncontroDTO.fromEntity(localEncontroRepository.save(local));
    }

    @Transactional
    public void deletar(Integer id) {
        localEncontroRepository.deleteById(id);
    }
}
