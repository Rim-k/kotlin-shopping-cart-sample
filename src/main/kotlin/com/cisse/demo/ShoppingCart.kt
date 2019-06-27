package com.cisse.demo

class ShoppingCart(private val quantitiesByProduct: MutableMap<Product, Long> = mutableMapOf()) {
    fun products() = HashMap(quantitiesByProduct)

    fun add(product: Product, quantity: Long) = quantitiesByProduct.put(product, quantity)
}