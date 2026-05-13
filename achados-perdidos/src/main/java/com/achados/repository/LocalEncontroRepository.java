package com.achados.repository;

import com.achados.model.LocalEncontro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalEncontroRepository extends JpaRepository<LocalEncontro, Integer> {
}
