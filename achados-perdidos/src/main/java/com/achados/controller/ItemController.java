package com.achados.controller;

import com.achados.dto.ItemDTO;
import com.achados.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<Page<ItemDTO>> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(itemService.listarTodos(pageable));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<ItemDTO>> buscar(
            @RequestParam String termo,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);

        if (status != null && !status.isEmpty()) {
            return ResponseEntity.ok(itemService.buscarPorTermoEStatus(termo, status, pageable));
        }
        return ResponseEntity.ok(itemService.buscarPorTermo(termo, pageable));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<Page<ItemDTO>> buscarPorStatus(
            @PathVariable String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(itemService.buscarPorStatus(status, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> obterPorId(@PathVariable Integer id) {
        return itemService.obterPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemDTO> criar(@RequestBody ItemDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(itemService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> atualizar(
            @PathVariable Integer id,
            @RequestBody ItemDTO dto) {
        return ResponseEntity.ok(itemService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        itemService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
