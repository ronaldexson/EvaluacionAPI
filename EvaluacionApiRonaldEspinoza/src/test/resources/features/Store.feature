@tiendaStore
Feature: Pagina Petstore orders


  Scenario Outline:  Creación de Order
    When creo la siguiente orden con el id "<idOrder>", petId "<petIdOrder>", cantidad "<canOrder>"
    Then valido el codigo de respuesta es <codRespuesta>
    And valido si el campo complete es <complete>
    Examples:
      | idOrder |  | petIdOrder | canOrder | complete | codRespuesta |
      | 1       |  | 5          | 10       | true     | 200          |
      | 2       |  | 10         | 15       | true     | 200          |
      | 3       |  | 6          | 2        | true     | 200          |

  Scenario Outline: Consulta de Order
    Given que ingreso a la url "https://petstore.swagger.io/v2"
    When busco por el orderId <idOrder>
    Then valido que el codigo de respuesta es <codRespuesta>
    And valido si el campo status es "<status>"
    Examples:
      | idOrder |  | codRespuesta | status |
      | 1       |  | 200          | placed |
      | 2       |  | 200          | placed |
      | 3       |  | 200          | placed |