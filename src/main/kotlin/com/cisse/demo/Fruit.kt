package com.cisse.demo

class Fruit (val type: FruitType, name: String, price: Price) : Product(name, price) {

    override fun type() = type.name

    companion object {
        fun new(type: FruitType, name: String, price: Price) = Fruit(type, name, price)
    }
}

enum class FruitType {
    APPLE,
    ORANGE,
    WATERMELON
}