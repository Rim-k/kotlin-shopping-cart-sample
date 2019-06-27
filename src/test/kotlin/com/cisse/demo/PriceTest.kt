package com.cisse.demo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigDecimal
import java.util.*

internal class PriceTest {

    @ParameterizedTest(name = "{0} plus {1} gives {2}" )
    @CsvSource(
        "0.20, 0.20, 0.40",
        "0.00, 0.00, 0.00",
        "10.00, 0.12, 10.12"
    )
    fun should_return_a_price_with_the_total_amount(initialAmount: BigDecimal, amountToAdd: BigDecimal, expectedAmount: BigDecimal) {
        assertThat(Price.new(initialAmount).add(Price.new(amountToAdd)))
            .isEqualTo(Price.new(expectedAmount))
    }

    @ParameterizedTest(name = "{0} minus {1} gives {2}" )
    @CsvSource(
        "0.40, 0.10, 0.30",
        "1000.00, 0.50, 999.50",
        "10.00, 0.20, 9.80"
    )
    fun should_return_a_price_with_the_amount_of_the_difference(initialAmount: BigDecimal, amountToSubtract: BigDecimal, expectedAmount: BigDecimal) {
        assertThat(Price.new(initialAmount).minus(Price.new(amountToSubtract)))
            .isEqualTo(Price.new(expectedAmount))
    }

    @ParameterizedTest(name = "{0} multiplied by {1} gives {2}" )
    @CsvSource(
        "0.00, 3, 0.00",
        "0.50, 4, 2.00",
        "0.80, 1, 0.80"
    )
    fun should_return_a_price_with_the_amount_multiplied_by_the_quantity(initialAmount: BigDecimal, quantity: Long, expectedAmount: BigDecimal) {
        assertThat(Price.new(initialAmount).times(quantity))
            .isEqualTo(Price.new(expectedAmount))
    }

    @Test
    fun default_currency_should_be_GBP(){
        assertThat(Price.ZERO.currency).isEqualTo(Currency.getInstance("GBP"))
        assertThat(Price.ZERO.currency.printSymbol()).isEqualTo(Price.GPB_CURRENCY_SYMBOL)
    }
}