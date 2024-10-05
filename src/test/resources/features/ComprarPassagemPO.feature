Feature: Comprar Passagem

  Scenario: Comprar Passagem de "San Diego" para "Berlin"
    Given que acesso a pagina inicial PO
    When seleciono origem "San Diego" e destino "Berlin" PO
    When clico no botao Find Flights PO
    Then exibe pagina de voos entre "San Diego" e "Berlin" disponiveis PO
