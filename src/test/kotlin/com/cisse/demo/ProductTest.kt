package com.cisse.demo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigDecimal

internal class ProductTest {

    @ParameterizedTest(name = "{2} {0}(S) coast {3}" )
    @CsvSource(
        "APPLE, 0.20, 0, 0.00",
        "APPLE, 0.20, 1, 0.20",
        "APPLE, 0.20, 4, 0.80",
        "ORANGE, 0.50, 3, 1.50",
        "WATERMELON, 0.80, 5, 4.00"
    )
    fun should_return_total_price_for_given_quantity(productName: String, amount: BigDecimal, quantity: Long, totalAmount: BigDecimal) {
        assertThat(Fruit(FruitType.valueOf(productName), productName, Price.new(amount)).priceOf(quantity))
            .isEqualTo(Price.new(totalAmount))
    }
}