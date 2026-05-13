package com.achados.controller;

import com.achados.dto.LocalEncontroDTO;
import com.achados.service.LocalEncontroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locais")
@RequiredArgsConstructor
public class LocalEncontroController {
    private final LocalEncontroService localEncontroService;

    @GetMapping
    public ResponseEntity<List<LocalEncontroDTO>> listarTodos() {
        return ResponseEntity.ok(localEncontroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalEncontroDTO> obterPorId(@PathVariable Integer id) {
        return localEncontroService.obterPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LocalEncontroDTO> criar(@RequestBody LocalEncontroDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(localEncontroService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalEncontroDTO> atualizar(
            @PathVariable Integer id,
            @RequestBody LocalEncontroDTO dto) {
        return ResponseEntity.ok(localEncontroService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        localEncontroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
