# Sistema de Monitoramento de Vegetação — Motiva

> Protótipo de sistema para monitoramento e priorização de roçada de vegetação em rodovias, desenvolvido em Java com foco nos pilares da Orientação a Objetos.

---

## Sobre o Projeto

O sistema nasceu da necessidade de gerenciar o crescimento de vegetação às margens de rodovias, permitindo que equipes de manutenção sejam alocadas de forma eficiente nos trechos mais críticos.

Esta **Sprint 2** expande o domínio base com um Motor de Regras, Classes Abstratas e Interfaces, entregando um protótipo funcional em console que simula crescimento diferenciado por ambiente, classifica automaticamente a criticidade dos trechos e executa intervenções polimórficas.

---

## Estrutura do Projeto

```
Sprint-2-Motor-Regras-Abstracao-2CCPH/
├── src/
│   └── br/com/motiva/
│       ├── main/
│       │   └── SistemaPrincipal.java
│       └── model/
│           ├── MonitoravelViaIoT.java
│           ├── TrechoRodovia.java
│           ├── EquipeManutencao.java
│           ├── IntervencaoOperacional.java
│           ├── RocadaManual.java
│           ├── RocadaMecanizada.java
│           ├── Pulverizacao.java
│           └── MotorPrioridade.java
├── .gitignore
└── README.md
```

---

## Classes e Responsabilidades

### `TrechoRodovia` 
Representa um segmento físico de rodovia monitorado pelo sistema. Agora implementa `MonitoravelViaIoT` e possui um `TipoAmbiente` que influencia o crescimento.

| Atributo               | Tipo            | Descrição                                      |
|------------------------|-----------------|------------------------------------------------|
| `quilometroInicial`    | `double`        | Início do trecho (em KM)                       |
| `quilometroFinal`      | `double`        | Fim do trecho (em KM)                          |
| `nivelVegetacao`       | `double`        | Altura atual da vegetação (em cm)              |
| `tipoAmbiente`         | `TipoAmbiente`  | Enum com fator de crescimento por ambiente     |
| `possuiSensorIoT`      | `boolean`       | Indica se o trecho tem sensor instalado        |

**Enum `TipoAmbiente`:**

| Valor       | Fator de Crescimento | Descrição                           |
|-------------|----------------------|-------------------------------------|
| `UMIDO`     | ×1.8                 | Cresce 80% mais rápido que o padrão |
| `TROPICAL`  | ×1.4                 | Cresce 40% mais rápido              |
| `PADRAO`    | ×1.0                 | Crescimento base                    |
| `SECO`      | ×0.7                 | Cresce 30% mais devagar             |

**Comportamentos:**
- `registrarCrescimento(double taxaBase)` — aplica o fator do ambiente à taxa base antes de somar.
- `transmitirDadosSensor(double leitura)` — implementação da interface IoT; atualiza nível sem inspeção visual.
- `isSensorAtivo()` — retorna se o sensor está operacional.

---

### `IntervencaoOperacional` 

Abstração base para todas as intervenções de campo. Não pode ser instanciada diretamente.

| Atributo       | Tipo              | Descrição                            |
|----------------|-------------------|--------------------------------------|
| `descricao`    | `String`          | Nome da intervenção                  |
| `trechoAlvo`   | `TrechoRodovia`   | Trecho onde a intervenção ocorre     |

**Comportamentos:**
- `executarServico()` — método **abstrato**; cada subclasse implementa sua lógica específica.
- `iniciarIntervencao()` — Template Method: exibe cabeçalho e chama `executarServico()`.

**Subclasses concretas:**

| Classe               | Indicação                   | Efeito no nível de vegetação         |
|----------------------|-----------------------------|--------------------------------------|
| `RocadaMecanizada`   | Vegetação ≥ 40 cm (CRÍTICO) | Reduz ao nível residual de 2.0 cm    |
| `RocadaManual`       | Vegetação ≥ 20 cm (ATENÇÃO) | Reduz ao nível residual de 5.0 cm    |
| `Pulverizacao`       | Vegetação ≥ 10 cm (ALERTA)  | Reduz 30% (efeito herbicida residual)|

---

### `MonitoravelViaIoT` 

Contrato de comportamento para trechos com sensores remotos instalados.

| Método                                   | Descrição                                            |
|------------------------------------------|------------------------------------------------------|
| `transmitirDadosSensor(double leitura)`  | Atualiza o nível de vegetação via leitura do sensor  |
| `isSensorAtivo()`                        | Informa se o sensor está operacional                 |

---

### `MotorPrioridade` 

Varre um array de `TrechoRodovia`, classifica por criticidade e gera o Relatório de Prioridade.

**Regras de Negócio:**

| Nível de Vegetação | Classificação |
| ------------------ | ------------- |
| ≥ 40 cm            | Crítico       |
| ≥ 20 cm            | Atenção       |
| ≥ 10 cm            | Alerta        |
| < 10 cm            | Normal        |

Os trechos são ordenados por nível de vegetação (decrescente) antes de gerar o relatório.

---

### `EquipeManutencao`

Agora possui o método `executarIntervencao(IntervencaoOperacional)`, que demonstra **polimorfismo**: a equipe não precisa conhecer o tipo concreto da intervenção — apenas chama o contrato da classe abstrata.

---

### `SistemaPrincipal` 

Responsável por executar e demonstrar todas as funcionalidades do sistema através de testes em console.

Os testes realizados são:

1. Cadastro dos trechos rodoviários
2. Exibição dos dados cadastrados
3. Simulação do crescimento da vegetação
4. Monitoramento via sensores IoT
5. Polimorfismo utilizando a interface `MonitoravelViaIoT`
6. Validação de dados de vegetação
7. Geração automática de prioridades
8. Criação das equipes de manutenção
9. Execução de roçada mecanizada
10. Execução de pulverização química
11. Encerramento do ciclo de monitoramento

---

### 3. Encapsulamento *(Sprint 1)*

Se `nivelVegetacao` fosse público, qualquer classe poderia executar `trecho.nivelVegetacao = -999` sem validação, corrompendo silenciosamente os dados. O setter validado garante que **nenhuma operação externa coloque o sistema em estado inválido**.

---

### 4. Motor de Regras e `TipoAmbiente` *(Sprint 2 — novo)*

O `TipoAmbiente` é um `enum` que encapsula o fator de crescimento de cada tipo de ambiente. Ao chamar `registrarCrescimento(taxaBase)`, o trecho aplica automaticamente `taxaBase × fatorCrescimento`, sem que o chamador precise conhecer essa lógica. Isso é o princípio **Tell, Don't Ask**: o objeto sabe como crescer de acordo com seu tipo — ninguém de fora decide isso por ele.

---

### 5. Classes Abstratas — `IntervencaoOperacional` *(Sprint 2)*

#### Por que `IntervencaoOperacional` é abstrata?

**Não faz sentido para a Motiva que uma equipe execute apenas uma "Intervenção Operacional" genérica sem especificar qual é**, pois:

- No mundo real, toda operação de campo exige especificidade: equipamentos diferentes, técnicas diferentes, custos diferentes e equipes com habilitações diferentes.
- Uma "intervenção genérica" não pode ser agendada, orçada ou executada — ela não tem procedimento definido.
- A abstração existe como **contrato e molde**: garante que toda intervenção concreta terá um método `executarServico()`, mas não executa nada por conta própria.
- Tentar `new IntervencaoOperacional(...)` causaria erro de compilação — e isso é intencional. O compilador se torna um guardião do modelo de negócio.


### 6. Interfaces — `MonitoravelViaIoT` *(Sprint 2)*

#### Diferença arquitetural: Classe Abstrata vs. Interface

| Aspecto               | Classe Abstrata (`IntervencaoOperacional`) | Interface (`MonitoravelViaIoT`)              |
|-----------------------|-------------------------------------------|----------------------------------------------|
| **Relação expressa**  | "É UM" (herança de identidade)            | "PODE FAZER" (capacidade de comportamento)   |
| **Compartilha estado**| Sim (atributos herdados)                | Não (apenas constantes)                    |
| **Herança múltipla**  | Java permite apenas uma classe mãe      |  Uma classe pode implementar N interfaces   |
| **Quando usar**       | Hierarquias com lógica compartilhada      | Contratos desacoplados de hierarquia         |
| **Exemplo no sistema**| Toda intervenção "é uma" operação         | Um trecho "pode ser" monitorável via IoT     |

**Na prática:** `TrechoRodovia` poderia herdar de uma hipotética classe abstrata `ElementoRodoviario` (compartilhando dados como `id`, `rodovia`, `dataCadastro`) **e ao mesmo tempo** implementar `MonitoravelViaIoT` — sem conflito. A interface não compete com a hierarquia; ela adiciona uma capacidade ortogonal.

#### Aplicação do ISP (Interface Segregation Principle)

`MonitoravelViaIoT` tem apenas dois métodos: `transmitirDadosSensor()` e `isSensorAtivo()`. Não existe um método `gerarRelatorio()` nessa interface, mesmo que trechos possam aparecer em relatórios. Cada interface tem uma única responsabilidade — o ISP garante que nenhuma classe seja forçada a implementar métodos que não usa.

---

### 7. Polimorfismo *(Sprint 2)*

`EquipeManutencao.executarIntervencao(IntervencaoOperacional)` recebe qualquer subclasse concreta sem precisar conhecê-la. O método correto (`executarServico()`) é resolvido em tempo de execução pela JVM — isso é polimorfismo. A mesma equipe pode executar uma `RocadaMecanizada` ou uma `Pulverizacao` com o mesmo código de chamada.

---

### 8. Algoritmo de Varredura e Relatório de Prioridade *(Sprint 2)*

O `MotorPrioridade` aplica o padrão de **Clean Code com constantes nomeadas** (sem *magic numbers*):

```java
private static final double LIMIAR_CRITICO = 40.0;
private static final double LIMIAR_ATENCAO = 20.0;
private static final double LIMIAR_ALERTA  = 10.0;
```

O algoritmo ordena os trechos por criticidade antes de gerar o relatório, garantindo que os mais urgentes apareçam primeiro — facilitando a tomada de decisão da equipe de campo.