# Sistema de Atendimento de Clínica

## Projeto de Estrutura de Dados I

### Aluno
Gabriel Duarte Santos

---

## Descrição do Projeto

O projeto consiste em um sistema simples de atendimento de uma clínica,
desenvolvido em Java e executado pelo terminal.

O sistema permite cadastrar pacientes, listar os pacientes cadastrados,
buscar pacientes pelo nome, ordenar os pacientes em ordem alfabética e
registrar atendimentos.

O projeto foi desenvolvido com o objetivo de demonstrar, na prática,
a utilização de estruturas de dados estudadas na disciplina de
Estrutura de Dados I.

---

## Funcionalidades

O sistema possui as seguintes funcionalidades:

- Cadastrar paciente
- Listar pacientes
- Buscar paciente pelo nome
- Ordenar pacientes por nome
- Registrar atendimento
- Visualizar a última operação
- Desfazer a última operação
- Encerrar o sistema

---

## Estruturas de Dados Utilizadas

### Array

Foi utilizado um array de objetos `Paciente` para armazenar os pacientes
cadastrados no sistema.

O array é utilizado para:

- Armazenar os pacientes
- Percorrer os pacientes
- Buscar pacientes
- Ordenar os pacientes

### Enum

Foi criado o enum `Prioridade` para representar os níveis de prioridade
dos pacientes:

- `NORMAL`
- `URGENTE`

### Pilha

Foi implementada uma pilha própria utilizando a classe `Pilha<T>`.

A pilha possui as seguintes operações:

- Empilhar (`push`)
- Desempilhar (`pop`)
- Consultar o topo (`top`)

A pilha é utilizada para armazenar o histórico das operações realizadas
no sistema.

---

## Organização do Projeto

```text
java/
├── README.md
└── src/
    ├── Main.java
    ├── modelos/
    │   ├── Paciente.java
    │   └── Prioridade.java
    └── estruturas/
        └── Pilha.java