package com.cisse.demo

class Offer (private val accept: (Product, Long) -> Boolean,
             private val getDiscount: (Product, Long) -> Price
            ) {

    fun apply(cart: ShoppingCart): Price {
        return cart.products().filter { accept(it.key, it.value) }
                              .map { getDiscount(it.key, it.value) }
                              .fold(Price.ZERO) { sum, price -> sum.add(price) }
    }

    fun apply(product: Product, quantity: Long): Price {
        return if (accept(product, quantity)) getDiscount(product, quantity) else Price.ZERO
    }

    companion object {
        fun new (accept: (Product, Long) -> Boolean, getDiscount: (Product, Long) -> Price) = Offer(accept, getDiscount)
    }

}
