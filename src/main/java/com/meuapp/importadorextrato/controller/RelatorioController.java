package com.meuapp.importadorextrato.controller;

import com.meuapp.importadorextrato.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @GetMapping("/por-categoria")
    public List<Map<String, Object>> relatorioPorCategoria() {

        // Busca dados agregados do banco
        List<Object[]> dados = transacaoRepository.somarPorCategoria();

        // Converte para formato JSON amigável
        List<Map<String, Object>> resultado = new ArrayList<>();

        for (Object[] linha : dados) {
            Map<String, Object> item = new HashMap<>();
            item.put("categoria", linha[0] != null ? linha[0] : "Sem Categoria");
            item.put("total", linha[1]);
            resultado.add(item);
        }

        return resultado;
    }
}