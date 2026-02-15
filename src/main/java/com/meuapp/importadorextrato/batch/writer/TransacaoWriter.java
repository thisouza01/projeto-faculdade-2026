package com.meuapp.importadorextrato.batch.writer;

import com.meuapp.importadorextrato.domain.entity.Transacao;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransacaoWriter {

    @Bean
    public JpaItemWriter<Transacao> writer(EntityManagerFactory entityManagerFactory) {

        JpaItemWriter<Transacao> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);

        return writer;

    }

}
