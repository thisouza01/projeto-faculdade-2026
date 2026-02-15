package com.meuapp.importadorextrato.config;

import com.meuapp.importadorextrato.domain.dto.TransacaoDTO;
import com.meuapp.importadorextrato.domain.entity.Transacao;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfiguration {

    // Cria o JOB
    @Bean
    public Job importarExtratoJob(JobRepository jobRepository, Step processarTransacoesStep) {

        return new JobBuilder("importarExtratoJob", jobRepository)
                .start(processarTransacoesStep)
                .build();
    }

    @Bean
    public Step processarTransacoesStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            ItemReader<TransacaoDTO> itemReader,
            ItemProcessor<TransacaoDTO, Transacao> processor,
            ItemWriter<Transacao> writer) {

        return new StepBuilder("processarTransacoesStep", jobRepository)
                .<TransacaoDTO, Transacao>chunk(100, transactionManager)
                .reader(itemReader)
                .processor(processor)
                .writer(writer)
                .build();

    }

}
