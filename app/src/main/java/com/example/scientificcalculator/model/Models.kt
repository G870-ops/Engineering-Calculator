package com.example.scientificcalculator.model

data class HistoryItem(
    val id: Long = System.currentTimeMillis(),
    val expression: String,
    val result: String,
    val timestamp: Long = System.currentTimeMillis()
)

data class FormulaItem(
    val name: String,
    val equation: String,
    val description: String,
    val category: String
)

data class ComplexNumber(
    val real: Double,
    val imaginary: Double
) {
    operator fun plus(other: ComplexNumber) =
        ComplexNumber(real + other.real, imaginary + other.imaginary)

    operator fun minus(other: ComplexNumber) =
        ComplexNumber(real - other.real, imaginary - other.imaginary)

    operator fun times(other: ComplexNumber) = ComplexNumber(
        real * other.real - imaginary * other.imaginary,
        real * other.imaginary + imaginary * other.real
    )

    operator fun div(other: ComplexNumber): ComplexNumber {
        val denom = other.real * other.real + other.imaginary * other.imaginary
        if (denom == 0.0) throw ArithmeticException("Division by zero")
        return ComplexNumber(
            (real * other.real + imaginary * other.imaginary) / denom,
            (imaginary * other.real - real * other.imaginary) / denom
        )
    }

    fun modulus() = Math.sqrt(real * real + imaginary * imaginary)

    fun argument() = Math.atan2(imaginary, real)

    fun conjugate() = ComplexNumber(real, -imaginary)

    override fun toString(): String {
        val r = formatNum(real)
        val i = formatNum(Math.abs(imaginary))
        return when {
            imaginary == 0.0 -> r
            real == 0.0 && imaginary > 0 -> "${i}i"
            real == 0.0 -> "-${i}i"
            imaginary > 0 -> "$r + ${i}i"
            else -> "$r - ${i}i"
        }
    }

    private fun formatNum(d: Double): String {
        return if (d == Math.floor(d) && !d.isInfinite()) d.toLong().toString()
        else "%.6g".format(d).trimEnd('0').trimEnd('.')
    }
}
