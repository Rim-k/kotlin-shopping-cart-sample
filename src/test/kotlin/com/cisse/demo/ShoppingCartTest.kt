package com.cisse.demo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class ShoppingCartTest {

    @ParameterizedTest(name = "add {0} {1} to cart" )
    @CsvSource(
        "4, APPLE, 4",
        "3, ORANGE, 3",
        "5, WATERMELON, 5"
    )
    fun should_add_product_to_cart_with_the_right_quantity(quantityToAdd: Long, fruitType: FruitType, expectedAddedQuantity: Long) {
        val cart = ShoppingCart()
        val fruit = Fruit(fruitType, fruitType.name, Price.ZERO)
        cart.add(fruit, quantityToAdd)
        assertThat(cart.products()[fruit]).isEqualTo(expectedAddedQuantity)
    }

    @Test
    fun new_cart_should_have_no_product() {
        assertThat(ShoppingCart().products()).isEmpty()
    }
}