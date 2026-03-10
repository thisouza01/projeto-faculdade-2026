# Importador de Extratos Bancários

Sistema acadêmico de importação e processamento de transações financeiras utilizando **Spring Batch**, desenvolvido como projeto de estudo para aplicação de conceitos de processamento em lote.

---

## Sobre o Projeto

Este projeto implementa um sistema completo de **importação de extratos bancários** a partir de arquivos CSV, com:

- ✅ **Processamento em lote** (Spring Batch)
- ✅ **Validações de negócio** robustas
- ✅ **API REST** para integração
- ✅ **Interface web** amigável
- ✅ **Persistência** em banco PostgreSQL
- ✅ **Arquitetura em camadas** bem definida

---

## Tecnologias Utilizadas

### **Backend:**
- **Java 21** - Linguagem de programação
- **Spring Boot 3.2.2** - Framework base
- **Spring Batch** - Processamento em lote
- **Spring Data JPA** - Persistência de dados
- **Spring Web** - API REST
- **Maven** - Gerenciamento de dependências

### **Banco de Dados:**
- **PostgreSQL 15** - Banco relacional
- **Docker** - Containerização do banco

### **Frontend:**
- **HTML5 + CSS3** - Estrutura e estilo
- **Bootstrap 5** - Framework CSS responsivo
- **JavaScript** - Interatividade
  
---

## Funcionalidades

### **1. Importação de Extratos CSV**
- Upload de arquivo via interface web ou API REST
- Validação de formato (apenas `.csv`)
- Processamento assíncrono via Spring Batch

### **2. Validações Implementadas**
- ✅ **Data:** Formato válido (yyyy-MM-dd) e não futura
- ✅ **Valor:** Numérico e maior que zero
- ✅ **Descrição:** Não vazia
- ✅ **Tipo:** Apenas ENTRADA ou SAIDA

### **3. API REST**
- `POST /api/importar` - Upload e processamento de CSV
- `GET /api/transacoes` - Listar todas as transações
- `GET /api/transacoes/{id}` - Buscar por ID
- `GET /api/transacoes/count` - Contar total

### **4. Interface Web**
- Formulário de upload responsivo
- Visualização de transações em tabela
- Estatísticas (total de entradas e saídas)
- Atualização automática após importação

---

### **5. Acessar a Aplicação**

- **Interface Web:** http://localhost:8080
- **API REST:** http://localhost:8080/api/transacoes

---

## Formato do Arquivo CSV

O arquivo deve seguir este formato:
```csv
data,descricao,valor,tipo
2025-02-01,Salário - Empresa XYZ,5000.00,ENTRADA
2025-02-03,Mercado Extra,150.50,SAIDA
2025-02-05,Uber - Centro,25.00,SAIDA
```

**Regras:**
- Primeira linha = cabeçalho (obrigatório)
- **data:** formato `yyyy-MM-dd`
- **descricao:** texto não vazio
- **valor:** número decimal (usar ponto)
- **tipo:** `ENTRADA` ou `SAIDA`

---

## Testando a Aplicação

### **Via Interface Web:**

1. Acesse http://localhost:8080
2. Clique em "Escolher Arquivo"
3. Selecione um arquivo `.csv`
4. Clique em "Importar Extrato"
5. Aguarde a mensagem de sucesso
6. Veja as transações na tabela abaixo


**Resposta esperada:**
```json
{
  "jobExecutionID": 1,
  "status": "COMPLETED",
  "mensagem": "Job iniciado com sucesso"
}
```

---

## Modelo de Dados

### **Tabela: tb_transacao**

| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | BIGINT (PK) | Identificador único |
| data | DATE | Data da transação |
| descricao | VARCHAR(200) | Descrição da transação |
| valor | DECIMAL(15,2) | Valor em reais |
| tipo_transacao | VARCHAR(20) | ENTRADA ou SAIDA |
| data_importacao | TIMESTAMP | Data/hora da importação |
| id_execucao_job | BIGINT | ID da execução do Job Batch |

---
