package com.meuapp.importadorextrato.repository;

import com.meuapp.importadorextrato.domain.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long>{

    List<Transacao> findByCategoriaId(Long categoriaId);
    List<Transacao> findByCategoriaIsNull();

}
