# Projeto SIGTAP

Tradicionalmente os dados das tabelas de procedimentos do SUS são consultados por meio de um portal web e inclusive de uma aplicação _desktop_. A versão destes produtos não é atualizada desde 2011 (conforme manuais de documentação). Neste contexto, o objetivo do presente projeto é

> Realizar a transformação digital necessária para prover solução atualizada, de acesso fácil e rápido ao conteúdo das tabelas de procedimentos, medicamentos e outros do SUS.

## Organização da solução

A solução compreende quatro componentes: (a) [API](solucao/api) para acesso aos dados geridos pelo SIGTAP; (b) cliente [web](solucao/web); (c) cliente [mobile](solucao/mobile) e (d) cliente via linha de comandos ([cli](solucao/cli)). Estes quatro componentes fazem uso, direto ou indiretamente, do módulo [core](solucao/core), que implementa a funcionalidade de busca de procedimento.

# Sistema existente

SIGTAP é o Sistema de Gerenciamento da Tabela de Procedimentos, Medicamentos, Órteses, Próteses e Materiais Especiais do SUS. Observe que o SIGTAP é um sistema existente produzido pelo DATASUS, enquanto o
Projeto SIGTAP, o presente projeto, visa a confecção de uma solução alternativa. Detalhes do sistema
SIGTAP produzido pelo DATASUS podem ser encontrados:

- No portal do [SIGTAP](http://sigtap.datasus.gov.br/) onde, inclusive, poderá fazer uso da versão web para consultas.
- Na [wiki](https://wiki.saude.gov.br/sigtap) do SIGTAP.


## Projetos correlatos

- [CBO](https://github.com/kyriosdata/cbo)
- [CIAP-2](https://github.com/kyriosdata/ciap2)
- [CNES](https://github.com/kyriosdata/cnes)
- [CID-10](https://github.com/kyriosdata/cid10)

