# Dados

## Download

- Diretório FTP onde competências são disponibilizadas: ftp://ftp2.datasus.gov.br/pub/sistemas/tup/downloads
- Usar [Apache Commons Net](https://commons.apache.org/proper/commons-net/) para acesso ao FTP Server do Datasus, onde competências são disponibilizadas.
- Usar [Apache Camel](https://camel.apache.org/) para a integração do software a ser criado com o depósito de dados oferecido pelo DATASUS. Observe que deve considerar esta conexão como "volátil". [Univocity-parser](https://www.univocity.com/pages/univocity_parsers_fixed_width.html#working-with-fixed-width) para acesso a arquivos texto com campos de tamanho fixo.

## Descompressão

- Arquivo ZIP baixado (download) deve ser descomprimido. Veja [aqui](https://www.journaldev.com/960/java-unzip-file-example) como descomprimir.

## Processamento de arquivos texto

- Arquivo (representa um arquivo txt)
- Esquema (representa um esquema, por exemplo, posição inicial e quantidade de caracteres)
- Ler registro (linha de arquivo txt) conforme esquema fornecido.
- Do registro lido obter campos conforme o esquema fornecido.
- Criar enums para campos cujos valores são predefinidos (lista restrita), por exemplo, código de financiamento na tabela financiamento.

## Versão local (dos dados)

Formato interno da aplicação deverá ser produzida dos dados baixados.
Abaixo segue a definição do formato local, otimizado para eficência da busca e tamanho.
Serializar em XML, JSON, outro formato mais compacto?

## Base local

Manter lista de competências e versões locais dos dados correspondentes.
