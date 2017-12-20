| Data | Autor | Comentarios | Versao |
| --- | --- | --- | --- |
| 15/05/2017 | Wagner Alves | Versao inicial | 1.0.0.RELEASE |  

#Microservico Projeto Treinamento
##treinamento teste microservico.
Versao: 1.0.0.SNAPSHOT


#Comecando

1.Instalacao Ambiente de execucao Docker:

Ver em [VSTS/Documentos](https://fastalelo.visualstudio.com/FAST/_git/Documentos?path=%2FRefer%C3%AAncia%2Fdocker-compose%2Fdocker-compose.md&version=GBmaster&_a=contents).

#Inicializando o microservico no Eclipse / STS

```
Em um dos adapters de INBOUND encontre o pacote: br.com.treinamento.tr.adapter.inbound.application.
Na classe TreinamentoStarterApplication.java, clique com o botao direito do mouse em Run As > Java Application.
```

#Executando projeto de testes integrados.

Clique no modulo treinamentoteste-parent, clique em Run As > Run Configurations... 

Preencha os campos:
```
'Name' com 'treinamentoteste-integrado'
'Goals' com 'clean test'
```
Obs: para execucao dos testes integrados, os servicos de inbound, outbound e microservico Projeto Treinamento,
devem estar em execucao no [Docker](https://www.docker.com/).
Tambem e possivel testar o microservico em execucao no [Eclipse / STS](https://spring.io/tools).
