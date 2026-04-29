package com.meuapp.importadorextrato.repository;

import com.meuapp.importadorextrato.domain.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long>{

    List<Transacao> findByCategoriaId(Long categoriaId);
    List<Transacao> findByCategoriaIsNull();

    @Query("SELECT COALESCE(c.nome, 'Sem Categoria'), SUM(t.valor) " +
            "FROM Transacao t LEFT JOIN t.categoria c " +
            "WHERE t.tipoTransacao = 'SAIDA' " +
            "GROUP BY c.nome")
    List<Object[]> somarPorCategoria();
}
