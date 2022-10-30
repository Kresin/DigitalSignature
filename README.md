### Autor: Gabriel Kresin

### Sobre o projeto:
O objetivo deste projeto é realizar uma demonstração da implementação de Assinatura Digital existente no JAVA. Mais 
detalhes sobre o trabalho podem ser conferidos diretamente no [enunciado](L09%20-%20Assinatura%20digital.pdf).

**O que acontece ao validar a assinatura do arquivo usando uma chave pública diferente?**

O programa *printa* no terminal a mensagem "Não ok" após a chamada do método `validateSignature()`. 

### Como importar as dependências:
Este projeto utiliza a lib commons-codec para fazer a conversão de arrays do tipo byte para Strinhs hexadecimais. Para
importar a lib na sua IDE, basta seguir o passo a passo abaixo:

**IntelliJ:**
* Acesse File > Project Structure
* Seleciona o menu modules e aba dependências
* Clique no ícone de + e selecione a opção Jars or Directories
* Selecione o [JAR](libs/commons-codec-1.15.jar) contido nesse projeto
* Clique no botão OK e Apply

**Eclipse:**
* Botão direito no seu projeto
* Build Path > Configure Build Path
* Clique em Libraries e selecione a opção Add External JARs
* Selecione o [JAR](libs/commons-codec-1.15.jar) contido nesse projeto
* Apply e OK