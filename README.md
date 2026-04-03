# Autonomous Desktop

Este repositório contém um sistema desktop desenvolvido em Java para gestão de freelancers de uma sorveteria. O projeto faz parte de uma solução acadêmica desenvolvida no 5º semestre da faculdade, com o objetivo de atender uma demanda real de uma comunidade externa à instituição de ensino. A aplicação permite gerenciar colaboradores freelancers, facilitando o controle de atividades, organização e apoio à operação do negócio.

---

### Estrutura do Projeto

| Pacote | Responsabilidade
| :- | :- |
| **`clients`** | Comunicação com a API externa, encapsulando chamadas HTTP e tratamento de respostas|
| **`controllers`** | Intermediam as requisições vindas da interface, organizando o fluxo e delegando as ações para os serviços apropriados.|
| **`dtos`** | Objetos utilizados para transportar dados entre camadas e na comunicação com a API.|
| **`entities`** | Representações das entidades de domínio, geralmente espelhando a estrutura retornada pela API.|
| **`enums`** | Definição de constantes tipadas para controle de valores fixos e padronização de regras.|
| **`services`** | Contêm as regras de negócio da aplicação, coordenando operações e aplicando validações.|

---

### Como Executar o Projeto

#### Pré-requisitos

* Java JDK 24 ou superior
* IDE (IntelliJ, Eclipse ou VS Code)

#### Passos

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/autonomous-desktop.git

# Acesse a pasta
cd autonomous-desktop
```

1. Abra o projeto na sua IDE
2. Localize a classe principal
3. Execute a aplicação

**`Obseravação:`** Para o programa funcionar como esperado é necessário rodar também a API.

---

### Outros Componentes da Solução

Este programa faz parte de uma solução completa que inclui:

* API: https://github.com/FeMartins2002/Faculdade-Autonomous-API
* Web: 
