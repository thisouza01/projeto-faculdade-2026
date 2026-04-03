package com.meuapp.importadorextrato.domain.entity;

import com.meuapp.importadorextrato.domain.enums.TipoTransacao;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    @Column(length = 200, nullable = false)
    private String descricao;

    @Column(precision = 15, scale = 2)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTransacao tipoTransacao;

    @Column(nullable = false)
    private LocalDateTime dataImportacao;

    private Long idExecucaoJob;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public LocalDateTime getDataImportacao() {
        return dataImportacao;
    }

    public void setDataImportacao(LocalDateTime dataImportacao) {
        this.dataImportacao = dataImportacao;
    }

    public Long getIdExecucaoJob() {
        return idExecucaoJob;
    }

    public void setIdExecucaoJob(Long idExecucaoJob) {
        this.idExecucaoJob = idExecucaoJob;
    }

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", data=" + data +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", tipoTransacao=" + tipoTransacao +
                ", dataImportacao=" + dataImportacao +
                ", idExecucaoJob=" + idExecucaoJob +
                '}';
    }
}
