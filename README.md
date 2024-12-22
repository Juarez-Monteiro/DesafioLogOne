
# Controle de Agendamentos de Vagas

## Descrição do Projeto
Este projeto é uma aplicação para o **Controle de Agendamentos de Vagas**, desenvolvida como parte do teste prático para desenvolvedores Java. A aplicação permite o cadastro de vagas, solicitantes e agendamentos, além de consultas detalhadas sobre os agendamentos realizados.

O objetivo principal foi implementar um sistema capaz de suportar uma média de **300 agendamentos diários**, garantindo **performance** e **agilidade** nas respostas aos usuários, utilizando **boas práticas de desenvolvimento**, como **código limpo** e **design patterns**.

---

## Funcionalidades
### Cadastro de Vagas
- **Campos:** Data de início, data de término, quantidade de vagas disponíveis.
- **Validações:**
  - Não permite cadastro de vagas com datas retroativas.

### Cadastro de Solicitantes
- **Campos:** Nome do solicitante.
- **Validações:**
  - Todos os campos são obrigatórios.

### Cadastro de Agendamentos
- **Campos:** Data do agendamento, número, motivo, solicitante.
- **Validações:**
  - Não permite agendamento para períodos sem vagas disponíveis.
  - Limite máximo de **25% das vagas disponíveis** para um único solicitante no mesmo período.
  - Não permite múltiplos agendamentos para o mesmo solicitante no mesmo período.

### Consultas
1. **Consulta de Agendamentos por Período:**
   - Parâmetros: Data de início, data de término, solicitante.
   - Retorna lista de agendamentos com todos os dados no período informado.

2. **Consulta do Total de Agendamentos por Solicitante:**
   - Parâmetros: Data de início, data de término, solicitante.
   - Retorna:
     - Nome do solicitante.
     - Total de agendamentos realizados.
     - Quantidade de vagas disponíveis no período.
     - Percentual de ocupação das vagas pelo solicitante.

---

## Tecnologias Utilizadas
- **Backend:**
  - Java 17
  - Spring Boot
  - JPA / Hibernate
  - MapperStruct (para mapeamento entre DTOs e entidades)
  - Tratativa de Exceções (garantindo um fluxo adequado de erros)
- **Frontend:**
  - JSF
  - PrimeFaces
- **Banco de Dados:**
  - HSQLDB (banco de dados embarcado)
- **Gerenciamento de Dependências:**
  - Maven

---

## Arquitetura
A aplicação foi projetada utilizando uma arquitetura **MVC (Model-View-Controller)**. O backend gerencia a lógica de negócios e persistência de dados, enquanto o frontend cuida da interface do usuário.

### Camadas do Sistema
1. **Controller:**
   - Gerencia as requisições HTTP e interage com o serviço.
2. **Service:**
   - Contém a lógica de negócios e validações.
3. **Repository:**
   - Interface responsável pela comunicação com o banco de dados.
4. **Model:**
   - Representação das entidades do sistema.

### Design Patterns
- **Singleton:** Para gerenciamento de configurações globais.
- **DTO (Data Transfer Object):** Para transferência de dados entre camadas.
- **Repository:** Para abstração do acesso ao banco de dados.

### Funcionalidades Adicionais:
- **MapperStruct:** Utilizado para o mapeamento eficiente entre DTOs e as entidades do modelo, facilitando a conversão de dados e melhorando a legibilidade e performance do código.
- **Tratativas de Exceções:** Implementação de classes e métodos para tratar as exceções de forma adequada, proporcionando mensagens de erro claras e evitando falhas inesperadas.
- **JPAREPOSITORY Query Personalizada:** Consultas personalizadas no JPA, otimizadas para realizar operações complexas de busca de dados no banco, garantindo eficiência e precisão.

### CRUD Completo:
Foi implementado um **CRUD completo** (Criar, Ler, Atualizar, Deletar) para todas as entidades envolvidas, permitindo a manipulação fácil e eficaz dos dados no sistema.

---

## Configuração e Execução do Projeto

### Pré-requisitos
- **Java 17**
- **Maven**
- **Git**

### Passos para Configuração
1. **Clone o repositório:**

   git clone https://github.com/Juarez-Monteiro/DesafioLogOne.git

2. **Acesse o diretório do projeto**
Após clonar o repositório, navegue até o diretório do projeto com o comando:

cd Teste-Pratico-Desenvolvedor-Java

3. **Verifique as dependências do Maven**

Certifique-se de que o Maven está instalado e configurado corretamente no seu ambiente. Para verificar, execute o seguinte comando:

mvn -v

Caso o Maven não esteja instalado, siga os passos de instalação disponíveis na documentação oficial do Maven.

4. **Configure o banco de dados**

Este projeto utiliza o banco de dados HSQLDB em modo arquivo, localizado na pasta de database/agenda. Dependendo do seu sistema operacional, será necessário ajustar o caminho do banco de dados. 
**Obs: Substituir a parte em destaque por o endereço que coresponde sua pasta**

**No Linux:** No arquivo application.properties, ajuste a URL do banco para apontar para o caminho correto do banco de dados em seu sistema. Por exemplo:

spring.datasource.url=jdbc:hsqldb:file:**/media/juarez/c/LogOne**/Teste-Pratico-Desenvolvedor-Java/database/agenda/agenda;hsqldb.lock_file=false

**No Windows:** Caso esteja utilizando Windows, o caminho pode ser diferente. Ajuste a URL do banco de dados para refletir o caminho do seu diretório no sistema. Exemplo:

spring.datasource.url=jdbc:hsqldb:file:**C:/LogOne**/Teste-Pratico-Desenvolvedor-Java/database/agenda/agenda;hsqldb.lock_file=false

Certifique-se de que o diretório de banco de dados exista e esteja acessível.

5. **Compile o projeto**
Agora, compile o projeto usando Maven:

mvn clean install

Esse comando irá limpar qualquer build anterior e instalar todas as dependências necessárias, além de gerar o arquivo .jar da aplicação.

6. **Execute o projeto**
Para iniciar a aplicação, utilize o comando Maven:

mvn spring-boot:run

A aplicação será iniciada na porta 9292, que pode ser acessada no seu navegador.

7. **Acesso ao Sistema**
Após a execução, você poderá acessar as funcionalidades através de um navegador. Para isso:

Frontend (Interface Gráfica): Acesse a aplicação web através de http://localhost:9292.

