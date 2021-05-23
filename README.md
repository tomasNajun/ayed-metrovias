# Metrovias TP

- Existen tres constantes en la clase Metrovías que se podrían pasar cómo configuración. Se harcodearon ya que el enunciado no lo pedía.
- Si se pidieran más estadísticas tendría sentido crear una clase propia que las maneje. Donde habría que pasar:
    - variable `revenue`
    - variable `totalAttentionSpan`
    - método `updateTotalAttentionSpan`
    - método `updateRevenue`
    - método `getAverageAttentionSpan`
- El método `attendCustomer` devuelve un `Optional`. Esto permite evitar posibles null pointers. Ya que este objeto te obliga a preguntar si el valor está presente.
- Uso de `final`: se utiliza para resaltar que es una constante, es decir, que no se le debe asignar otro valor. Si se intenta hacer el compilador falla.