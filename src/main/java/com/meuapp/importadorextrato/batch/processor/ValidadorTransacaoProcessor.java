package com.meuapp.importadorextrato.batch.processor;

import com.meuapp.importadorextrato.domain.dto.TransacaoDTO;
import com.meuapp.importadorextrato.domain.entity.Transacao;
import com.meuapp.importadorextrato.domain.enums.TipoTransacao;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class ValidadorTransacaoProcessor implements ItemProcessor<TransacaoDTO, Transacao>{

    @Override
    public Transacao process(TransacaoDTO dto) throws Exception{

        Transacao transacao = new Transacao();

        // Valida data
        try {
            LocalDate data = LocalDate.parse(dto.getData());

            // Está no futuro?
            if (data.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("A data não pode estar no futuro" + dto.getData());
            };

            transacao.setData(data);

        } catch (DateTimeParseException dataParseError) {
            throw new IllegalArgumentException("O formato da data é inválido! " + dto.getData(), dataParseError);
        }

        // Valida valor
        try {
            BigDecimal valor = new BigDecimal(dto.getValor());

            // Valor é positivo?
            if (valor.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("O valor deve ser maior do que 0! " + dto.getValor());
            }

            transacao.setValor(valor);

        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("Valor informado inválido! " + dto.getValor(), numberFormatException);
        }

        // Valida descrição

        // Descrição veio nula?
        if (dto.getDescricao() == null || dto.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode ser estar vazia!");
        }

        transacao.setDescricao(dto.getDescricao().trim());

        // Valida tipo

        try {
            TipoTransacao tipoTransacao = TipoTransacao.valueOf(dto.getTipo());

            transacao.setTipoTransacao(tipoTransacao);
        } catch (IllegalArgumentException tipoErro) {
            throw new IllegalArgumentException("Tipo inválido! " + dto.getTipo() + ". Use: ENTRADA ou SAIDA", tipoErro);
        }


        // Preenchimento de campos que não vem no CSV

        transacao.setDataImportacao(LocalDateTime.now());

        transacao.setIdExecucaoJob(null);

        return transacao;
    }

}
