package com.cisse.demo

import com.cisse.demo.Fruit.Companion.new
import com.cisse.demo.FruitType.*
import java.math.BigDecimal
import java.util.*

fun main(args: Array<String>) {

    val apple = new(APPLE, "Apple", Price.new(BigDecimal.valueOf(0.20)))
    val orange = new(ORANGE, "Orange", Price.new(BigDecimal.valueOf(0.50)))
    val watermelon = new(WATERMELON, "watermelon", Price.new(BigDecimal.valueOf(0.80)))

    val priceCalculator = PriceCalculatorImpl.priceCalculator

    print(
        """
        |Product  -----> Unit Price (${Price.CURRENCY.printSymbol()})
        |Apple --------> ${apple.price.amount}
        |Orange -------> ${orange.price.amount}
        |Watermelon ---> ${watermelon.price.amount}
        |
        |***Current offers***
        |Buy One Get One Free on Apples
        |Three For The Price Of Two on Watermelons
        |
        """.trimMargin()
    )

    val cart = ShoppingCart()

    val apples: Long
    val oranges: Long
    val watermelons: Long


    val reader = Scanner(System.`in`)
    print("\nHow many apples do you want ? : ")
    apples = reader.nextLong()
    print("How many oranges do you want ? : ")
    oranges = reader.nextLong()
    print("How many watermelons do you want ? : ")
    watermelons = reader.nextLong()

    with (cart) {
        add(apple, apples)
        add(orange, oranges)
        add(watermelon, watermelons)
    }

    print(
        """
        |
        |Product ----> Price (${Price.CURRENCY.printSymbol()}) -> Quantity -> Total 
        |Apple ------> ${apple.price.printAmount()}  --------> $apples --------> ${priceCalculator.getTotal(apple, apples).printAmount()}
        |Orange -----> ${orange.price.printAmount()}  --------> $oranges --------> ${priceCalculator.getTotal(orange, oranges).printAmount()}
        |Watermelon -> ${watermelon.price.printAmount()}  --------> $watermelons --------> ${priceCalculator.getTotal(watermelon, watermelons).printAmount()}
        |
        |Total ----------------------------------> ${priceCalculator.getTotal(cart).amount}
        """.trimMargin()
    )


}

