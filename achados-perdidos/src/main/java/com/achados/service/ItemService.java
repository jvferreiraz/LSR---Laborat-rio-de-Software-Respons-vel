package com.achados.service;

import com.achados.dto.ItemDTO;
import com.achados.model.AuditLog;
import com.achados.model.Item;
import com.achados.repository.AuditLogRepository;
import com.achados.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final AuditLogRepository auditLogRepository;

    @Transactional(readOnly = true)
    public Page<ItemDTO> listarTodos(Pageable pageable) {
        Page<Item> items = itemRepository.findAll(pageable);
        return items.map(ItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDTO> buscarPorTermo(String termo, Pageable pageable) {
        Page<Item> items = itemRepository.buscarPorTermo(termo, pageable);
        return items.map(ItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDTO> buscarPorStatus(String status, Pageable pageable) {
        try {
            Item.StatusEnum statusEnum = Item.StatusEnum.valueOf(status.toUpperCase());
            Page<Item> items = itemRepository.buscarPorStatus(statusEnum, pageable);
            return items.map(ItemDTO::fromEntity);
        } catch (IllegalArgumentException e) {
            return new PageImpl<>(java.util.Collections.emptyList(), pageable, 0);
        }
    }

    @Transactional(readOnly = true)
    public Page<ItemDTO> buscarPorTermoEStatus(String termo, String status, Pageable pageable) {
        try {
            Item.StatusEnum statusEnum = Item.StatusEnum.valueOf(status.toUpperCase());
            Page<Item> items = itemRepository.buscarPorTermoEStatus(termo, statusEnum, pageable);
            return items.map(ItemDTO::fromEntity);
        } catch (IllegalArgumentException e) {
            return new PageImpl<>(java.util.Collections.emptyList(), pageable, 0);
        }
    }

    @Transactional(readOnly = true)
    public Optional<ItemDTO> obterPorId(Integer id) {
        return itemRepository.findById(id).map(ItemDTO::fromEntity);
    }

    @Transactional
    public ItemDTO criar(ItemDTO dto) {
        Item item = Item.builder()
                .descricao(dto.getDescricao())
                .dataEncontro(dto.getDataEncontro())
                .observacao(dto.getObservacao())
                .status(Item.StatusEnum.valueOf(dto.getStatus().toUpperCase()))
                .build();

        Item saved = itemRepository.save(item);
        registrarAuditoria(saved, "Item cadastrado");
        return ItemDTO.fromEntity(saved);
    }

    @Transactional
    public ItemDTO atualizar(Integer id, ItemDTO dto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        item.setDescricao(dto.getDescricao());
        item.setDataEncontro(dto.getDataEncontro());
        item.setObservacao(dto.getObservacao());
        item.setStatus(Item.StatusEnum.valueOf(dto.getStatus().toUpperCase()));

        Item updated = itemRepository.save(item);
        registrarAuditoria(updated, "Item atualizado");
        return ItemDTO.fromEntity(updated);
    }

    @Transactional
    public void deletar(Integer id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
        itemRepository.delete(item);
        registrarAuditoria(item, "Item deletado");
    }

    private void registrarAuditoria(Item item, String acao) {
        AuditLog log = AuditLog.builder()
                .item(item)
                .acao(acao)
                .build();
        auditLogRepository.save(log);
    }
}
