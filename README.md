# Sistema de Vendas (PDV)
## O sistema:
        O Sistema de Vendas (PDV) é composto 2 categorias principais: Product e Sale. A primeira é composta por uma classe
    que elabora métodos relacionados aos itens (cada item terá um código, nome, preço, estoque e operações relacionadas)
    e a classe ProductRepository, que serve como um gerenciador central que "comunica" com cada item. A segunda [sale], é composta
    pelo conjunto de classes que compôem o lado que gerencia as vendas, sendo responsável por todo o processo de efetuar, guardar e resumir
    dado sobre as vendas.
        Por fim, a classe Main demonstrará todas essas classes funcionando conjuntamente, sendo guiado pelos inputs do usuário. Vale salientar,
    também, que a Main oferece "segunda chances" quando o que foi digitado não faz parte do planejado pelo programa, pois foi decidido que seria
    melhor apenas repetir prompts ao invés de encerrar o funcionamento do programa.

## Pré-requisitos:
- Java Development Kit (JDK)
## Seguir as seguintes instruções para uso do programa
    Abrir o terminal e executar os seguintes comandos em ordem
### Clonar repositorio
- via https
    ```bash
    git clone https://github.com/yasminazevedo127/PDV.git
    ```
- via SSH
    ```bash
    git clone git@github.com:yasminazevedo127/PDV.git
    ```

### Mover para o diretorio do projeto
```bash
    cd PDV
```
### Compilação:
```bash
    javac -d bin --source-path src src/sale/*.java src/product/*.java src/module-info.java
```
### Execução:
```bash
    java --module-path bin -m PDV/sale.Main
```

## Integrantes:
    - Alan Cesar Rebouças de Araújo Carvalho
    - Pedro Vinícius Barbosa Pereira
    - Yasmin Pinheiro Azevedo
