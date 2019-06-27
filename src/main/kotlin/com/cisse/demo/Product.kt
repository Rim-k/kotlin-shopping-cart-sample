package com.cisse.demo


abstract class Product (val name: String, val price: Price) {

    fun priceOf(quantity: Long) = price.times(quantity)

    abstract fun type() : String
}