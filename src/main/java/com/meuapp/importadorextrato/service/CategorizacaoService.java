package com.meuapp.importadorextrato.service;

import com.meuapp.importadorextrato.domain.entity.Categoria;
import com.meuapp.importadorextrato.repository.CategoriaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class CategorizacaoService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    private Map<String, String> palavrasChaveMap = new HashMap<>();

    @PostConstruct
    @Transactional
    public void inicializarCategorias() {

        // Alimentação
        salvarSeNaoExistir("Alimentação",
                "mercado,supermercado,ifood,uber eats,restaurante,padaria,açougue,hortifruti,lanche,fast food,pizzaria,churrascaria,café,cafeteria,delivery,comida,refeição");

        // Moradia
        salvarSeNaoExistir("Moradia",
                "aluguel,condominio,agua,luz,energia,gas,internet,telefone,iptu,manutencao,conserto,reforma");

        // Saúde
        salvarSeNaoExistir("Saúde",
                "farmacia,remedios,consulta,medico,dentista,laboratorio,exame,hospital,plano de saude,clinica");

        // Carregar mapa de palavras-chave em memória
        carregarPalavrasChave();
    }

    /**
     * Salva categoria apenas se não existir
     */
    private void salvarSeNaoExistir(String nome, String palavrasChave) {
        if (categoriaRepository.findByNome(nome).isEmpty()) {
            Categoria categoria = new Categoria();
            categoria.setNome(nome);
            categoria.setPalavrasChave(palavrasChave);
            categoriaRepository.save(categoria);
        }
    }

    private void carregarPalavrasChave() {
        categoriaRepository.findAll().forEach(categoria -> {
            if (categoria.getPalavrasChave() != null && !categoria.getPalavrasChave().isEmpty()) {
                palavrasChaveMap.put(categoria.getNome(), categoria.getPalavrasChave());
            }
        });
    }

    public Categoria categorizar(String descricao) {

        if (descricao == null || descricao.trim().isEmpty()) {
            return null;
        }

        String descricaoLower = descricao.toLowerCase();

        // Tenta encontrar match com palavras-chave
        for (Map.Entry<String, String> entry : palavrasChaveMap.entrySet()) {
            String[] palavras = entry.getValue().split(",");

            for (String palavra : palavras) {
                if (descricaoLower.contains(palavra.trim())) {
                    // Achou! Retorna a categoria
                    return categoriaRepository.findByNome(entry.getKey()).orElse(null);
                }
            }
        }

        // Não encontrou nenhuma categoria
        return null;
    }
}