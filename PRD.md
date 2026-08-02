Vou montar esse documento como se fosse uma especificação técnica de um projeto real. A ideia é que ele sirva tanto como documentação quanto como guia de desenvolvimento durante todo o projeto.

Ele vai conter:

* ✅ Visão geral do projeto
* ✅ Objetivos
* ✅ Tecnologias utilizadas
* ✅ Arquitetura do sistema
* ✅ Estrutura de pastas
* ✅ Modelo do banco de dados
* ✅ Explicação detalhada de cada entidade
* ✅ Fluxo completo do bot
* ✅ Diagramas em texto
* ✅ Regras de negócio
* ✅ Backlog completo de tarefas
* ✅ Roadmap das próximas versões
* ✅ Checklist de desenvolvimento
* ✅ Como acompanhar o andamento do projeto
* ✅ Como executar localmente
* ✅ Como subir para Docker
* ✅ Como fazer deploy na VPS
* ✅ Ideias para futuras versões (relatórios, dashboard web, IA, etc.)

---

# Telegram Reminder Bot

## Documento de Especificação Técnica

**Versão:** 1.0

---

# Objetivo

Criar um bot para Telegram responsável por gerenciar lembretes inteligentes.

O usuário poderá cadastrar tarefas recorrentes ou únicas e receber notificações automaticamente durante o dia.

Toda interação ficará registrada para geração de relatórios futuros.

---

# Tecnologias

## Backend

* Java 21
* Spring Boot
* Spring Data JPA
* Spring Scheduling
* PostgreSQL
* Maven

## Integrações

* Telegram Bot API

Biblioteca Java recomendada:

```
org.telegram:telegrambots
```

---

## Banco

PostgreSQL

---

## Deploy

Primeira fase

```
Notebook
```

Segunda fase

```
Docker
```

Terceira fase

```
Oracle Cloud VPS
```

---

# Arquitetura

```
Telegram

      │

Telegram Bot

      │

Reminder Service

      │

Banco PostgreSQL

      │

Scheduler Spring
```

---

# Entidades

## Usuário

Representa uma pessoa que conversa com o bot.

### Campos

```
id

telegramId

nome

username

dataCadastro
```

Relacionamentos

```
1 Usuário

↓

N Lembretes
```

---

## Lembrete

Representa uma regra de lembrete.

Não representa um envio.

Representa a configuração.

### Campos

```
id

usuario

mensagem

tipo

horaInicio

horaFim

intervalo

ativo

confirmacaoObrigatoria

status

dataCriacao
```

### Tipo

```
Único

Diário
```

### Status

```
ATIVO

DESATIVADO

CANCELADO
```

Relacionamentos

```
1 Lembrete

↓

N Históricos
```

---

## Histórico do Lembrete

Representa cada envio realizado.

Cada vez que o bot envia uma mensagem será criado um histórico.

Campos

```
id

lembrete

dataHoraEnvio

dataHoraResposta

status

observacao
```

Status

```
ENVIADO

REALIZADO

ADIADO

SEM_RESPOSTA

ERRO
```

---

# Modelo Relacional

```
USUARIO

id

telegram_id

nome

username

data_cadastro
```

↓

```
LEMBRETE

id

usuario_id

mensagem

tipo

hora_inicio

hora_fim

intervalo

ativo

status

confirmacao

data_criacao
```

↓

```
HISTORICO

id

lembrete_id

data_envio

data_resposta

status

observacao
```

---

# Fluxo do Cadastro

```
Usuário

↓

/novo

↓

Deseja criar um lembrete?

↓

SIM

↓

É diário?

↓

SIM ou NÃO
```

Se SIM

```
↓

Qual horário?

↓

Qual mensagem?

↓

Salvar
```

Se NÃO

```
↓

Qual horário inicial?

↓

Qual horário final?

↓

Qual intervalo?

↓

Qual mensagem?

↓

Salvar
```

---

# Fluxo do Scheduler

```
Scheduler

↓

Busca lembretes ativos

↓

Existe algum para executar?

↓

SIM

↓

Enviar mensagem

↓

Criar histórico

↓

Aguardar resposta
```

---

# Fluxo da Resposta

Mensagem enviada

↓

Botões

```
✅ Realizado

⏰ Adiar

❌ Cancelar
```

## Realizado

```
Atualiza histórico

↓

Marca como realizado

↓

Fim
```

---

## Adiar

```
Atualiza histórico

↓

Agenda novo envio
```

---

## Cancelar

```
Desativa lembrete

↓

Nunca mais dispara
```

---

# Regras de Negócio

## Lembrete Único

Executa apenas uma vez.

Após executar:

```
Status

↓

DESATIVADO
```

Nunca será apagado.

---

## Lembrete Diário

Executa todos os dias.

---

## Intervalo

Exemplo

```
Início

10:00

Fim

22:00

Intervalo

60 minutos
```

Disparos

```
10

11

12

13

...

22
```

---

## Validações

Não permitir

```
Hora inicial menor que agora.
```

Não permitir

```
Hora final menor que hora inicial.
```

Não permitir

```
Intervalo igual a zero.
```

Não permitir

```
Mensagem vazia.
```

---

# Estrutura de Pastas

```
src

 ├── config

 ├── controller

 ├── dto

 ├── entity

 ├── enum

 ├── repository

 ├── scheduler

 ├── service

 ├── telegram

 ├── util

 └── exception
```

---

# Backlog

## Projeto

* [ ] Criar repositório Git
* [ ] Criar projeto Spring Boot
* [ ] Configurar Java 21
* [ ] Configurar PostgreSQL
* [ ] Configurar variáveis de ambiente
* [ ] Configurar Docker posteriormente

---

## Banco

* [ ] Criar entidade Usuário
* [ ] Criar entidade Lembrete
* [ ] Criar entidade Histórico
* [ ] Criar Repositories
* [ ] Criar migrations

---

## Telegram

* [ ] Criar BotFather
* [ ] Configurar Token
* [ ] Registrar Webhook ou Polling
* [ ] Receber mensagens
* [ ] Enviar mensagens
* [ ] Implementar botões
* [ ] Tratar callbacks

---

## Cadastro

* [ ] Fluxo de criação
* [ ] Fluxo diário
* [ ] Fluxo único
* [ ] Validações
* [ ] Persistência

---

## Scheduler

* [ ] Buscar lembretes ativos
* [ ] Verificar horário
* [ ] Enviar mensagens
* [ ] Criar histórico
* [ ] Atualizar histórico

---

## Respostas

* [ ] Realizado
* [ ] Adiar
* [ ] Cancelar

---

## Logs

* [ ] Registrar envio
* [ ] Registrar resposta
* [ ] Registrar erros

---

## Testes

* [ ] Testes Unitários
* [ ] Testes Integração
* [ ] Testes Scheduler

---

## Docker

* [ ] Dockerfile
* [ ] docker-compose
* [ ] Banco
* [ ] Aplicação

---

## Deploy

* [ ] Oracle Cloud
* [ ] Variáveis ambiente
* [ ] Docker Compose
* [ ] Backup Banco

---

# Roadmap

## V1

* Cadastro
* Agendamento
* Scheduler
* Telegram
* Histórico

---

## V2

* Relatórios

Exemplos

```
Quantidade de tarefas realizadas

Taxa de conclusão

Dias mais produtivos

Horários com maior produtividade

Quantidade de adiamentos

Quantidade de cancelamentos
```

---

## V3

Dashboard Web

```
Spring Boot

+

Frontend (React ou Next.js)
```

---

## V4

Categorias

```
Saúde

Estudos

Trabalho

Financeiro

Academia
```

---

## V5

IA

Exemplos

```
Você costuma concluir essa tarefa às 21h.

Deseja alterar automaticamente o horário?
```

---

# Como acompanhar o projeto

Durante o desenvolvimento, utilize este checklist:

```
Projeto criado

☐

Banco criado

☐

Telegram funcionando

☐

Cadastro funcionando

☐

Scheduler funcionando

☐

Envio funcionando

☐

Histórico funcionando

☐

Docker funcionando

☐

Deploy realizado

☐
```

Sempre que concluir uma funcionalidade:

1. Faça o commit no Git.
2. Marque o item correspondente como concluído (`[x]`).
3. Atualize este documento caso uma regra de negócio mude.
4. Crie uma nova branch para funcionalidades maiores.
5. Abra um Pull Request (mesmo trabalhando sozinho, isso ajuda a manter um histórico organizado).

---

# Fluxo Geral do Sistema

```text
                Telegram
                    │
                    ▼
             Usuário conversa
                    │
                    ▼
          Bot recebe a mensagem
                    │
                    ▼
      ReminderService processa fluxo
                    │
      ┌─────────────┴─────────────┐
      ▼                           ▼
 Salva no PostgreSQL        Agenda execução
      │                           │
      └─────────────┬─────────────┘
                    ▼
          Spring Scheduler verifica
                    │
                    ▼
           Horário chegou?
                    │
             Sim ───┘
                    ▼
          Envia mensagem Telegram
                    │
                    ▼
      Usuário responde pelos botões
                    │
                    ▼
      Atualiza Histórico e Lembrete
```

---

# Comandos úteis

## Executar localmente

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

## Executar os testes

```bash
./mvnw test
```

## Gerar o JAR

```bash
./mvnw clean package
```

## Construir a imagem Docker (futuro)

```bash
docker build -t telegram-reminder-bot .
```

## Subir com Docker Compose

```bash
docker compose up -d
```

---

## Minha sugestão

O projeto já nasceu com uma estrutura muito boa. A única mudança que eu faria em relação ao que conversamos é adicionar uma quarta entidade desde o início:

**Agendamento** (`ReminderSchedule`).

Hoje você consegue fazer tudo com apenas três entidades, mas quando quiser permitir coisas como:

* dias específicos da semana;
* vários horários para o mesmo lembrete;
* pausas temporárias;
* exceções de datas (feriados, férias etc.);

ter uma entidade separando a **regra do lembrete** da **agenda de execução** evita refatorações grandes. Para a V1, porém, as três entidades (**Usuário**, **Lembrete** e **Histórico**) são suficientes e mantêm o desenvolvimento simples e rápido.
