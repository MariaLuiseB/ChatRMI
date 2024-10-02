# Java RMI Chat Application

## 📩 Descrição
Este é um sistema de chat simples implementado usando **Java RMI (Remote Method Invocation)**. Ele permite que vários clientes se conectem a um servidor central e troquem mensagens entre si em tempo real. Cada cliente que se conecta recebe um emoji de cartinha 📩 antes do seu nome, e a comunicação é feita de forma interativa no console.

### Principais Funcionalidades:
- Enviar e receber mensagens de múltiplos clientes em tempo real.
- Cada cliente recebe uma cor única e um emoji 📩 que precede o seu nome.
- O servidor gerencia a lista de clientes conectados e retransmite mensagens para todos os clientes ativos.
- Uso de **multithreading** para permitir que o cliente continue digitando enquanto recebe mensagens de outros clientes.
- Desconexão automática quando um cliente digita "sair".

## 🛠️ Tecnologias Utilizadas:
- **Java RMI (Remote Method Invocation)** para comunicação remota entre cliente e servidor.
- **ANSI Colors** para colorir as mensagens de diferentes clientes.
- **Multithreading** para permitir que o cliente continue digitando enquanto recebe mensagens.

## 💻 Diagrama UML
### Diagrama UML básico de interação entre as classes:

![image](https://github.com/user-attachments/assets/b539585b-6e8a-45ff-87a8-ace3e622649e)

- **InterfaceProc**: Interface remota implementada pelos clientes para receber mensagens do servidor.
- **InterfaceServidor**: Interface remota implementada pelo servidor para gerenciar clientes e enviar mensagens.
- **Proc.java**: Cliente que se conecta ao servidor e recebe mensagens.
- **Servidor.java**: O servidor que gerencia as conexões dos clientes e retransmite as mensagens para todos.

## 🚀 Como Executar

### Pré-requisitos:
- **Java JDK** instalado (versão 8 ou superior).
- **Configuração do ambiente**:
  - Certifique-se de que o Java está corretamente configurado no PATH.
  - Necessário ter a política de segurança (`policy.all`) configurada.

### Passos para executar:

1. **Compilar os Arquivos**:
   - Navegue até o diretório onde estão os arquivos `.java` e compile-os com o seguinte comando:
     ```bash
     javac InterfaceProc.java InterfaceServidor.java Servidor.java Proc.java
     ```

2. **Executar o `rmiregistry`**:
   - No diretório onde estão os arquivos compilados, inicie o `rmiregistry`:
     ```bash
     rmiregistry
     ```

3. **Executar o Servidor**:
   - Inicie o servidor em um terminal separado:
     ```bash
     java -Djava.security.policy=policy.all Servidor
     ```

4. **Executar os Clientes**:
   - Para cada cliente, abra um novo terminal e execute:
     ```bash
     java -Djava.security.policy=policy.all Proc
     ```

   - Cada cliente será solicitado a digitar um nome, que será precedido por um emoji de cartinha 📩 e uma cor única será atribuída ao cliente.

5. **Chat Interativo**:
   - Agora, os clientes podem trocar mensagens entre si. Ao digitar "sair", o cliente será desconectado e o programa será encerrado.

### Estrutura do Projeto:
```
/src
  |-- InterfaceProc.java
  |-- InterfaceServidor.java
  |-- Servidor.java
  |-- Proc.java
  |-- ChatColors.java
  |-- policy.all
```

- **InterfaceProc.java**: Interface remota do cliente.
- **InterfaceServidor.java**: Interface remota do servidor.
- **Servidor.java**: Implementação do servidor que gerencia clientes e mensagens.
- **Proc.java**: Implementação do cliente que envia e recebe mensagens.
- **ChatColors.java**: Define cores para cada cliente no chat.
- **policy.all**: Arquivo de política de segurança para permitir a execução do RMI.

## 🔧 Ajustes e Melhorias Futuras
- **Interface gráfica (GUI)**: Implementar uma versão com interface gráfica (Swing/JavaFX) para melhorar a experiência do usuário.
- **Histórico de mensagens**: Adicionar uma funcionalidade para salvar o histórico de chat em um arquivo.
- **Autenticação de clientes**: Implementar um sistema de login para diferenciar melhor os clientes conectados.

---

### Se precisar de ajuda para executar ou configurar o projeto, sinta-se à vontade para abrir uma *issue* ou entrar em contato!

