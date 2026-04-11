package com.meuapp.importadorextrato.controller;

import com.meuapp.importadorextrato.domain.entity.Categoria;
import com.meuapp.importadorextrato.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @GetMapping
    public List<Categoria> listarTodas() {
        return repository.findAll();
    }
}