package com.cisse.demo

import com.cisse.demo.Price.Companion.CURRENCY
import com.cisse.demo.Price.Companion.GPB_CURRENCY_SYMBOL
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

data class Price private constructor(val amount: BigDecimal,
                 val currency: Currency = CURRENCY) {

    fun times(count: Long) = new(amount.multiply(BigDecimal.valueOf(count)))

    fun add(price: Price) = new(amount.plus(price.amount))

    fun minus(price: Price) = new(amount.minus(price.amount))

    fun printAmount() = amount.toString()

    companion object {
        val CURRENCY: Currency = Currency.getInstance(Locale.UK)
        private const val SCALE = 2
        fun new(amount: BigDecimal) = Price(amount.setScale(SCALE, RoundingMode.CEILING))
        val ZERO = new(BigDecimal.ZERO)
        val GPB_CURRENCY_SYMBOL = '£'
    }

}

fun Currency.printSymbol() = if (this.currencyCode == CURRENCY.currencyCode) GPB_CURRENCY_SYMBOL else this.getSymbol(Locale.UK)
