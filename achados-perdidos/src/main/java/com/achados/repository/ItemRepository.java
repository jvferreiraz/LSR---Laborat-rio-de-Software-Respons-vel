package com.achados.repository;

import com.achados.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    
    @Query("SELECT i FROM Item i WHERE LOWER(i.descricao) LIKE LOWER(CONCAT('%', :termo, '%')) " +
           "OR LOWER(i.observacao) LIKE LOWER(CONCAT('%', :termo, '%'))")
    Page<Item> buscarPorTermo(@Param("termo") String termo, Pageable pageable);

    @Query("SELECT i FROM Item i WHERE i.status = :status")
    Page<Item> buscarPorStatus(@Param("status") Item.StatusEnum status, Pageable pageable);

    @Query("SELECT i FROM Item i WHERE LOWER(i.descricao) LIKE LOWER(CONCAT('%', :termo, '%')) " +
           "AND i.status = :status")
    Page<Item> buscarPorTermoEStatus(@Param("termo") String termo, 
                                      @Param("status") Item.StatusEnum status, 
                                      Pageable pageable);

    @Query("SELECT i FROM Item i WHERE i.categoria.idCategoria = :categoriaId")
    Page<Item> buscarPorCategoria(@Param("categoriaId") Integer categoriaId, Pageable pageable);

    @Query("SELECT i FROM Item i WHERE i.localEncontro.idLocalEncontro = :localId")
    Page<Item> buscarPorLocal(@Param("localId") Integer localId, Pageable pageable);
}
