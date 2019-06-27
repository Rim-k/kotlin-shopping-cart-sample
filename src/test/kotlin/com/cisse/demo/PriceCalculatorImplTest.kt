package com.cisse.demo

import com.cisse.demo.FruitType.*
import com.cisse.demo.PriceCalculatorImpl.Companion.buyOneGetOneFreeOnApples
import com.cisse.demo.PriceCalculatorImpl.Companion.priceCalculator
import com.cisse.demo.PriceCalculatorImpl.Companion.threeForThePriceOfTwoForWatermelons
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigDecimal

internal class PriceCalculatorImplTest {

    private val apple = Fruit.new(APPLE, "Apple", Price.new(BigDecimal.valueOf(0.20)))
    private val orange = Fruit.new(ORANGE, "Orange", Price.new(BigDecimal.valueOf(0.50)))
    private val watermelon = Fruit.new(WATERMELON, "watermelon", Price.new(BigDecimal.valueOf(0.80)))


    private val products = mapOf(APPLE to apple, ORANGE to orange, WATERMELON to watermelon)

    @ParameterizedTest(name = "{1} {0}(S) coast {2} after discount" )
    @CsvSource(
        "APPLE, 0, 0.00",
        "APPLE, 1, 0.20",
        "APPLE, 4, 0.40",
        "ORANGE, 3, 1.50",
        "WATERMELON, 3, 1.60",
        "WATERMELON, 5, 3.20"
    )
    fun should_return_total_price_for_product_after_discount(productName: String, quantity: Long, totalAmountAfterDiscount: BigDecimal) {

        assertThat(priceCalculator.getTotal(products[valueOf(productName)]!!, quantity))
            .isEqualTo(Price.new(totalAmountAfterDiscount))
    }

    @Test
    fun should_return_total_price_of_cart_after_discount() {
        val apples = 4L
        val oranges = 3L
        val watermelons = 5L

        val cart = ShoppingCart()

        with (cart) {
            add(apple, apples)
            add(orange, oranges)
            add(watermelon, watermelons)
        }

        assertThat(priceCalculator.getTotal(cart)).isEqualTo(Price.new(BigDecimal(5.1)))
    }


    @ParameterizedTest(name = "buying {1} {0}(S) gives a discount of {2}" )
    @CsvSource(
        "APPLE, 0, 0.00",
        "APPLE, 1, 0.00",
        "APPLE, 2, 0.20",
        "APPLE, 4, 0.40",
        "ORANGE, 2, 0.00",
        "WATERMELON, 2, 0.00"
    )
    fun buyOneGetOneFreeOnApples(fruitType: FruitType, quantity: Long, expectedDiscount: BigDecimal) {
        assertThat(buyOneGetOneFreeOnApples.apply(products[fruitType]!!, quantity))
            .isEqualTo(Price.new(expectedDiscount))
    }

    @ParameterizedTest(name = "buying {1} {0}(S) gives a discount of {2}" )
    @CsvSource(
        "WATERMELON, 0, 0.00",
        "WATERMELON, 3, 0.80",
        "WATERMELON, 5, 0.80",
        "WATERMELON, 6, 1.60",
        "APPLE, 3, 0.00",
        "ORANGE, 3, 0.00"
    )
    fun threeForThePriceOfTwoForWatermelons(fruitType: FruitType, quantity: Long, expectedDiscount: BigDecimal) {
        assertThat(threeForThePriceOfTwoForWatermelons.apply(products[fruitType]!!, quantity))
            .isEqualTo(Price.new(expectedDiscount))
    }
}