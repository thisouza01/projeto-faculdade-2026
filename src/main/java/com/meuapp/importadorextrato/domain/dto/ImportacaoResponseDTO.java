package com.meuapp.importadorextrato.domain.dto;



public class ImportacaoResponseDTO {

    private Long jobExecutionID;
    private String status;
    private String mensagem;

    public ImportacaoResponseDTO() {
    }

    public ImportacaoResponseDTO(Long jobExecutionID, String status, String mensagem) {
        this.jobExecutionID = jobExecutionID;
        this.status = status;
        this.mensagem = mensagem;
    }

    public Long getJobExecutionID() {
        return jobExecutionID;
    }

    public void setJobExecutionID(Long jobExecutionID) {
        this.jobExecutionID = jobExecutionID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
