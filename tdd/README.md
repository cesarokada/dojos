# DOJO TDD (Test Driven Design)

## Objetivo
* Exercitar um processo de desenvolvimento amplamente difundido no mercado

* Exercitar conceitos de Domain Driven Desing
---
## Proposta

Para fins didáticos, detalhes de implementacão de infraestrutura, como banco de dados ou sistema de notificacão, devem ser abstraídos.

---

## Especificacão Funcional
Implementar lógica de domínio para compra e parcelamento.

### Compra
Uma compra é criada a partir de uma ordem de compra. Uma ordem de compra é composta por:
* Valor total da compra
* Quantidade de parcelas
* Data da compra

Uma compra é composta por:
* Data da compra
* Valor total da compra
* Parcelas

#### Validações Necessárias
1. O valor total da compra deve ser maior que zero.
2. A quantidade de parcelas deve ser maior que zero e no máximo em 12.
3. Só é permitido mais de uma parcela para compras com valor maior ou igual a R$ 100,00.

### Parcelas
Uma parcela é composta de:
 * Número da parcela
 * Valor da parcela
 * Data de vencimento

#### Regras
* Pagamento em apenas uma parcela possui desconto de 5%, considerando o valor total do pedido. Neste caso, o arredondamento deve HALF UP, considerando duas casas de precisão.

| Valor Bruto R$ | Valor Arredondado R$ |
|----------------|----------------------|
| 100.0001       | 100.00               |
| 100.0195       | 100.02               |
| 100.1595       | 100.02               |


* A data de vencimento da primeira parcela deve ser 3 dias após a data atual. Caso o dia seja não útil (sábado ou domingo), postergar para o próximo dia útil.
* As demais parcelas devem vencer no mesmo dia da primeira parcela, porém nos meses consecutivos. Caso o dia seja não útil (sábado ou domingo), postergar para o próximo dia útil.

#### Arredondamento do Valor das Parcelas
Por se tratar de valor monetário, devemos considerar no máximo duas casa decimais, por exemplo: R$ 50,27.
Caso o valor somado das parcelas não seja exatamente igual ao valor total da compra, a diferenca deve ser colocada na última parcela.

#### Exemplo da Funcionalidade:
Valor total da compra: R$100,00
Quantidade de parcelas: 3

| Número Parcela   | Valor Parcela R$ | Vencimento |
|------------------|------------------|------------|
| 1                | 33.33            | 19/05/2022 |
| 2                | 33.33            | 20/06/2022 |
| 3                | 33.34            | 19/07/2022 |
| Total            | 100.00           | --         |

---
## Para saber mais
* [Test Driven Development](https://en.wikipedia.org/wiki/Test-driven_development)
* [Is TDD Dead?](https://martinfowler.com/articles/is-tdd-dead/)
* [Domain Driven Design](https://en.wikipedia.org/wiki/Domain-driven_design)
