package com.meuapp.importadorextrato.controller;

import com.meuapp.importadorextrato.domain.dto.ImportacaoResponseDTO;
import com.meuapp.importadorextrato.service.ImportacaoService;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/importar")
public class ImportacaoController {

    @Autowired
    private ImportacaoService importacaoService;

    @PostMapping
    public ResponseEntity<ImportacaoResponseDTO> importarExtrato(
            @RequestParam("arquivo") MultipartFile arquivo) {

        try {

            JobExecution jobExecution = importacaoService.executarImportacao(arquivo);

            ImportacaoResponseDTO responseDTO = new ImportacaoResponseDTO(
                    jobExecution.getJobId(),
                    jobExecution.getStatus().name(),
                    "Job iniciado com sucesso"
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

        } catch (IllegalArgumentException e) {
            ImportacaoResponseDTO response = new ImportacaoResponseDTO(
                    null,
                    "ERRO",
                    e.getMessage()
            );
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            ImportacaoResponseDTO response = new ImportacaoResponseDTO(
                    null,
                    "ERRO",
                    "Erro ao processar arquivo: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
