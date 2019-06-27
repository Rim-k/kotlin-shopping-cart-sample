package com.cisse.demo

class PriceCalculatorImpl constructor(private val offers: Set<Offer>): PriceCalculator {

    override fun getTotal(cart: ShoppingCart): Price {
        return cart.products().map { getTotal(it.key, it.value) }
                              .fold(Price.ZERO) { sum, price -> sum.add(price) }
    }

    override fun getTotal(product: Product, quantity: Long): Price {
        val total = product.priceOf(quantity)

        val discount = offers.map { it.apply(product, quantity) }
                             .fold(Price.ZERO) { sum, price -> sum.add(price) }

        return total.minus(discount)
    }

    companion object {

         fun new (offers: Set<Offer>) = PriceCalculatorImpl(offers)

         val buyOneGetOneFreeOnApples = Offer.new(
            {product, quantity -> (FruitType.APPLE.name == product.type()) && quantity > 1},
            {product, quantity ->  product.priceOf(quantity.div(2)) }
         )

        val threeForThePriceOfTwoForWatermelons = Offer.new(
            {product, quantity -> (FruitType.WATERMELON.name == product.type()) && quantity > 2},
            {product, quantity ->  product.priceOf(quantity.div(3)) }
        )

        val priceCalculator: PriceCalculator = new(setOf(buyOneGetOneFreeOnApples, threeForThePriceOfTwoForWatermelons))
    }
}