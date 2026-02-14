package com.meuapp.importadorextrato.batch.reader;

import com.meuapp.importadorextrato.domain.dto.TransacaoDTO;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class ExtratoCSVReader {

    @Bean
    public FlatFileItemReader<TransacaoDTO> reader() {

        return new FlatFileItemReaderBuilder<TransacaoDTO>()
                .name("LeitorDoExtratoCSV")
                .resource(new ClassPathResource("extrato-exemplo.csv"))
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
