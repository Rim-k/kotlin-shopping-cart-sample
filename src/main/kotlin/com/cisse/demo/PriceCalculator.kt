package com.cisse.demo

interface PriceCalculator {
    fun getTotal(cart: ShoppingCart) : Price
    fun getTotal(product: Product, quantity: Long): Price
}