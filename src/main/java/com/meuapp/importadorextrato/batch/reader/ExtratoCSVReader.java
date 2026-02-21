package com.meuapp.importadorextrato.batch.reader;

import com.meuapp.importadorextrato.domain.dto.TransacaoDTO;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ExtratoCSVReader {

    @Bean
    @StepScope
    public FlatFileItemReader<TransacaoDTO> reader(
            @Value("#{jobParameters['caminhoArquivo']}") String caminhoArquivo) {


        return new FlatFileItemReaderBuilder<TransacaoDTO>()
                .name("LeitorDoExtratoCSV")
                .resource(new FileSystemResource(caminhoArquivo))
                .linesToSkip(1)
                .delimited()
                .delimiter(",")
                .names("data", "descricao", "valor", "tipo")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(TransacaoDTO.class);
                }})
                .build();


    }
}
