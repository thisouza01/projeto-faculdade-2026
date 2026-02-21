package com.meuapp.importadorextrato.controller;

import com.meuapp.importadorextrato.domain.entity.Transacao;
import com.meuapp.importadorextrato.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoRepository repository;

    @GetMapping
    public List<Transacao> listarTodas() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transacao> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/count")
    public Long contar() {
        return repository.count();
    }
}
