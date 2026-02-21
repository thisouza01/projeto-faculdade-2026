package com.meuapp.importadorextrato.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class ImportacaoService {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importarExtratoJob;

    @Value("${app.upload.dir}")
    private String uploadDir;

    public JobExecution executarImportacao(MultipartFile arquivo) throws Exception {

        // Valida se arquivo nao veio vazio
        if(arquivo.isEmpty()) {
            throw new IllegalArgumentException("Arquivo vazio");
        }

        // Valida se arquivo é CSV
        if(!arquivo.getOriginalFilename().endsWith(".csv")) {
            throw new IllegalArgumentException("Apenas arquivos CSV, são permitidos");
        }

        // Cria diretório se não existir
        File diretorio = new File(uploadDir);
        if(!diretorio.exists()){
            diretorio.mkdirs();
        }

        // Salva arquivo
        String nomeArquivo = System.currentTimeMillis() + "_" + arquivo.getOriginalFilename();
        Path caminhoCompleto = Paths.get(uploadDir, nomeArquivo);

        Files.copy(arquivo.getInputStream(), caminhoCompleto, StandardCopyOption.REPLACE_EXISTING);

        //Cria JobParameters (únicos para cada execução)
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis())
                .addString("nomeArquivo", nomeArquivo)
                .addString("caminhoArquivo", caminhoCompleto.toString())
                .toJobParameters();

        // Executo job

        return jobLauncher.run(importarExtratoJob, jobParameters);

    }

}
