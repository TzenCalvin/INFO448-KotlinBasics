package edu.uw.basickotlin

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    val result: String = when(arg) {
        is String -> when(arg) {
            "Hello" -> "world"
            else -> "Say what?"
        }
        else -> when(arg) {
            0 -> "zero"
            1 -> "one"
            in 2..10 -> "low number"
            else -> "I don't understand"
        }
    }

    return result
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(lhs: Int, rhs: Int): Int = lhs + rhs

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(lhs: Int, rhs: Int): Int = lhs - rhs

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(lhs: Int, rhs: Int, op: (Int, Int) -> Int) = op(lhs, rhs)

// write a class "Person" with first name, last name and age
class Person(var firstName: String, val lastName: String, var age: Int) {
    public var debugString: String = "[Person firstName:$firstName lastName:$lastName age:$age]"
}

// write a class "Money"
class Money(var amount: Int, var currency: String) {
    init {
        if (amount < 0) {
            throw IllegalArgumentException("Amount is less than 0.")
        }

        if (currency !in arrayOf("USD", "EUR", "CAN", "GBP")) {
            throw IllegalArgumentException("Invalid currency")
        }
    }

    fun convert(arg: String): Money {
        val result: Money = when(arg) {
            currency -> return Money(amount, currency)
            "USD" -> when(currency) {
                "EUR" -> Money(amount * 2 / 3, arg)
                "CAN" -> Money(amount * 4 / 5, arg)
                else -> Money(amount * 2, arg)
            }
            "EUR" -> when(currency) {
                "USD" -> Money(amount * 3 / 2, arg)
                "CAN" -> Money(amount * 6 / 5, arg)
                else -> Money(amount * 3, arg)
            }
            "CAN" -> when(currency) {
                "USD" -> Money(amount * 5 / 4, arg)
                "EUR" -> Money(amount * 5 / 6, arg)
                else -> Money(amount * 2 / 5, arg)
            }
            else -> when(currency) {
                "USD" -> Money(amount / 2, arg)
                "EUR" -> Money(amount /3, arg)
                else -> Money(amount * 5 / 2, arg)
            }
        }

        return result
    }

    operator fun plus(other: Money): Money {
        if (this.currency != other.currency)
            return Money(this.amount + other.convert(this.currency).amount, this.currency)
        else
            return Money(this.amount + other.amount, this.currency)
    }
}