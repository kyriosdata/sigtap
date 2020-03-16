# Projeto Acesso Fácil

Tradicionalmente os dados das tabelas de procedimentos do SUS são consultados por meio de um portal web e inclusive de uma aplicação _desktop_. A versão destes produtos não é atualizada desde 2011 (conforme manuais de documentação). Neste contexto, o objetivo do presente projeto é

> Realizar a transformação digital necessária para prover solução atualizada, de acesso fácil e rápido ao conteúdo das tabelas de procedimentos, medicamentos e outros do SUS.

A transformação digital será realizada por meio de quatro produtos: (a) [API](api) para acesso aos dados geridos pelo SIGTAP; (b) cliente [web](web); (c) cliente [mobile](mobile) e (d) cliente via linha de comandos ([cli](cli)).

# SIGTAP (corrente)

SIGTAP é o Sistema de Gerenciamento da Tabela de Procedimentos, Medicamentos, Órteses, Próteses e Materiais Especiais do SUS.
Onde obter detalhes:

- No portal do [SIGTAP](http://sigtap.datasus.gov.br/) onde, inclusive, poderá fazer uso da versão web para consultas.
- Na [wiki](https://wiki.saude.gov.br/sigtap) do SIGTAP.

## Dados (downloads)

Os dados disponibilizados pelo SIGTAP podem ser baixados pelo [link](http://sigtap.datasus.gov.br/tabela-unificada/app/download.jsp). Observe que os dados são atualizados mensalmente e cada mês é denominado de "competência". Além dos dados você pode baixar a versão desktop e também manuais.
