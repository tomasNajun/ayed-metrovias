# Metrovias TP

- Existen dos constantes en la clase `Metrovías` que se podrían pasar cómo configuración. Se harcodearon ya que el enunciado no lo pedía.
- Lo mismo pasa con el precio en la clase `TicketSeller`. Se usa un entero para simplificar. Pero siempre que se quiere representar plata hay que utilizar un tipo llamado `BigDecimal`.
- Si se pidieran más estadísticas tendría sentido crear una clase propia que las maneje. Donde habría que pasar:
    - variable `revenue`
    - variable `totalAttentionSpan`
    - método `updateTotalAttentionSpan`
    - método `updateRevenue`
    - método `getAverageAttentionSpan`
- El método `attendCustomer` devuelve un `Optional`. Esto permite evitar posibles null pointers. Ya que este objeto te obliga a preguntar si el valor está presente.
- Uso de `final`: se utiliza para resaltar que es una constante, es decir, que no se le debe asignar otro valor. Si se intenta hacer el compilador falla.
- Se tomó el tiempo de atención como el tiempo de espera, ya que el enunciado no aclara nada de tiempos con respecto a la atención en ventanilla.