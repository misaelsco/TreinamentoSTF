#language: pt
Funcionalidade: Teste para extrato bancário

  Esquema do Cenario: [extrato] - "<cenario>"
    Dado que exista a seguinte massa de dados cadastrada "<nomeDoJson>"
    E o filtro selecionado seja "<idConta>", "<dataInicial>" e "<dataFinal>"
    Quando uma requisicao de extrato for solicitada
    Entao devera retornar as transacoes para o periodo selecionado

    Exemplos: 
      | cenario                                        | nomeDoJson        | idConta | dataInicial | dataFinal  |
      | CT001 - Consulta de transacoes do mes corrente | massaInicial.json |    1234 | 01/12/2017  | 31/12/2018 |
      | CT001 - Consulta de transacoes do mes anterior | massaInicial.json |    1234 | 01/11/2017  | 30/11/2017 |

  Cenario: [extrato] - "CT001 - Consulta de transacoes do mes corrente"
    Dado que exista a seguinte massa de dados cadastrada "massaInicial.json"
    E o filtro selecionado seja "1234"
    Quando uma requisicao de extrato for solicitada
    Entao devera retornar as transacoes para o periodo selecionado

  Cenario: [extrato] - "CT001 - Consulta de transacoes do mes corrente"
    Dado que exista a seguinte massa de dados cadastrada:
      | idConta | valor  | dataEnvio  | dataProcessada | tipo   |
      |    1234 | 150.00 | 01/12/2017 | 01/12/2017     | DEBITO |
      |    1234 | 150.00 | 01/12/2017 | 01/12/2017     | DEBITO |
      |    1234 | 150.00 | 01/12/2017 | 01/12/2017     | DEBITO |
      |    1234 | 150.00 | 01/12/2017 | 01/12/2017     | DEBITO |
      |    1234 | 150.00 | 01/12/2017 | 01/12/2017     | DEBITO |
      |    1234 | 150.00 | 01/12/2017 | 01/12/2017     | DEBITO |
    E o filtro selecionado seja "1234"
    Quando uma requisicao de extrato for solicitada
    Entao devera retornar as transacoes para o periodo selecionado
