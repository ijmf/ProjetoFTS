Feature: Selecionar Produto
  Scenario Outline: Selecionar Produto com Sucesso
    Given que preciso comprar um presente
    When clico no <produto>
    Then exibe a pagina com o nome do <produto> e <preco>

    Examples:
      | produto                    | preco     |
      | "Sauce Labs Backpack"      | "$ 29.99" |
      | "Sauce Labs Fleece Jacket" | "$ 49.99" |
      | "Sauce Labs Bolt T-Shirt"  | "$ 15.99" |
      | "Sauce Labs Bike Light "   | "$ 9.99"  |