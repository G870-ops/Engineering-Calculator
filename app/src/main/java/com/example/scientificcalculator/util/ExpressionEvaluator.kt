package com.example.scientificcalculator.util

import kotlin.math.*
import kotlin.math.roundToInt

/**
 * Engineering-grade expression evaluator supporting:
 * - Basic arithmetic (+, -, *, /, %)
 * - Parentheses
 * - Scientific functions (sin, cos, tan, log, ln, sqrt, abs)
 * - Constants (pi, e)
 * - Power (^)
 * - Factorial (!)
 */
object ExpressionEvaluator {

    private var isDegree = true

    fun setDegreeMode(degree: Boolean) { isDegree = degree }

    fun evaluate(expression: String): Double {
        val cleaned = expression
            .trim()
            .replace("×", "*")
            .replace("÷", "/")
            .replace("−", "-")
            .replace("π", "pi")
            .replace(",", "")
        val tokens = tokenize(cleaned)
        val pos = intArrayOf(0)
        val result = parseExpr(tokens, pos)
        if (pos[0] < tokens.size) throw IllegalArgumentException("Unexpected token: ${tokens[pos[0]]}")
        return result
    }

    private fun toRad(x: Double) = if (isDegree) Math.toRadians(x) else x
    private fun fromRad(x: Double) = if (isDegree) Math.toDegrees(x) else x

    private fun tokenize(expr: String): List<String> {
        val tokens = mutableListOf<String>()
        var i = 0
        while (i < expr.length) {
            when {
                expr[i].isWhitespace() -> i++
                expr[i].isDigit() || expr[i] == '.' -> {
                    val start = i
                    while (i < expr.length && (expr[i].isDigit() || expr[i] == '.')) i++
                    // Handle scientific notation
                    if (i < expr.length && (expr[i] == 'e' || expr[i] == 'E')) {
                        i++
                        if (i < expr.length && (expr[i] == '+' || expr[i] == '-')) i++
                        while (i < expr.length && expr[i].isDigit()) i++
                    }
                    tokens.add(expr.substring(start, i))
                }
                expr[i].isLetter() -> {
                    val start = i
                    while (i < expr.length && expr[i].isLetter()) i++
                    tokens.add(expr.substring(start, i))
                }
                expr[i] == '!' -> { tokens.add("!"); i++ }
                else -> { tokens.add(expr[i].toString()); i++ }
            }
        }
        return tokens
    }

    // expr = term ((+|-) term)*
    private fun parseExpr(tokens: List<String>, pos: IntArray): Double {
        var result = parseTerm(tokens, pos)
        while (pos[0] < tokens.size && (tokens[pos[0]] == "+" || tokens[pos[0]] == "-")) {
            val op = tokens[pos[0]++]
            val term = parseTerm(tokens, pos)
            result = if (op == "+") result + term else result - term
        }
        return result
    }

    // term = power ((*|/|%) power)*
    private fun parseTerm(tokens: List<String>, pos: IntArray): Double {
        var result = parsePower(tokens, pos)
        while (pos[0] < tokens.size && (tokens[pos[0]] == "*" || tokens[pos[0]] == "/" || tokens[pos[0]] == "%")) {
            val op = tokens[pos[0]++]
            val power = parsePower(tokens, pos)
            result = when (op) {
                "*" -> result * power
                "/" -> if (power == 0.0) throw ArithmeticException("Division by zero") else result / power
                "%" -> result % power
                else -> result
            }
        }
        return result
    }

    // power = unary (^ unary)*  (right associative)
    private fun parsePower(tokens: List<String>, pos: IntArray): Double {
        val base = parseUnary(tokens, pos)
        return if (pos[0] < tokens.size && tokens[pos[0]] == "^") {
            pos[0]++
            val exp = parseUnary(tokens, pos)
            base.pow(exp)
        } else base
    }

    // unary = -? factorial
    private fun parseUnary(tokens: List<String>, pos: IntArray): Double {
        if (pos[0] < tokens.size && tokens[pos[0]] == "-") {
            pos[0]++
            return -parseFactorial(tokens, pos)
        }
        if (pos[0] < tokens.size && tokens[pos[0]] == "+") {
            pos[0]++
        }
        return parseFactorial(tokens, pos)
    }

    // factorial = primary (!)?
    private fun parseFactorial(tokens: List<String>, pos: IntArray): Double {
        var result = parsePrimary(tokens, pos)
        while (pos[0] < tokens.size && tokens[pos[0]] == "!") {
            pos[0]++
            val n = result.toInt()
            if (n < 0) throw IllegalArgumentException("Factorial of negative")
            result = factorial(n).toDouble()
        }
        return result
    }

    // primary = number | constant | func(expr) | (expr)
    private fun parsePrimary(tokens: List<String>, pos: IntArray): Double {
        if (pos[0] >= tokens.size) throw IllegalArgumentException("Unexpected end of expression")
        val token = tokens[pos[0]]

        // Number
        token.toDoubleOrNull()?.let { pos[0]++; return it }

        // Constants and functions
        if (token[0].isLetter()) {
            pos[0]++
            return when (token.lowercase()) {
                "pi" -> Math.PI
                "e" -> Math.E
                "sin" -> sin(toRad(parseParenArg(tokens, pos)))
                "cos" -> cos(toRad(parseParenArg(tokens, pos)))
                "tan" -> tan(toRad(parseParenArg(tokens, pos)))
                "asin" -> fromRad(asin(parseParenArg(tokens, pos)))
                "acos" -> fromRad(acos(parseParenArg(tokens, pos)))
                "atan" -> fromRad(atan(parseParenArg(tokens, pos)))
                "sinh" -> sinh(parseParenArg(tokens, pos))
                "cosh" -> cosh(parseParenArg(tokens, pos))
                "tanh" -> tanh(parseParenArg(tokens, pos))
                "log" -> log10(parseParenArg(tokens, pos))
                "ln" -> ln(parseParenArg(tokens, pos))
                "log2" -> log2(parseParenArg(tokens, pos))
                "sqrt" -> sqrt(parseParenArg(tokens, pos))
                "cbrt" -> cbrt(parseParenArg(tokens, pos))
                "abs" -> abs(parseParenArg(tokens, pos))
                "ceil" -> ceil(parseParenArg(tokens, pos))
                "floor" -> floor(parseParenArg(tokens, pos))
                "round" -> (parseParenArg(tokens, pos)).roundToInt().toDouble()
                "exp" -> exp(parseParenArg(tokens, pos))
                "mod" -> {
                    // mod(a, b)
                    if (pos[0] < tokens.size && tokens[pos[0]] == "(") {
                        pos[0]++
                        val a = parseExpr(tokens, pos)
                        if (pos[0] < tokens.size && tokens[pos[0]] == ",") pos[0]++
                        val b = parseExpr(tokens, pos)
                        if (pos[0] < tokens.size && tokens[pos[0]] == ")") pos[0]++
                        a % b
                    } else throw IllegalArgumentException("Expected (")
                }
                else -> throw IllegalArgumentException("Unknown function: $token")
            }
        }

        // Parentheses
        if (token == "(") {
            pos[0]++
            val result = parseExpr(tokens, pos)
            if (pos[0] < tokens.size && tokens[pos[0]] == ")") pos[0]++
            return result
        }

        throw IllegalArgumentException("Unexpected token: $token")
    }

    private fun parseParenArg(tokens: List<String>, pos: IntArray): Double {
        if (pos[0] >= tokens.size || tokens[pos[0]] != "(")
            throw IllegalArgumentException("Expected '(' after function")
        pos[0]++
        val result = parseExpr(tokens, pos)
        if (pos[0] < tokens.size && tokens[pos[0]] == ")") pos[0]++
        return result
    }

    private fun factorial(n: Int): Long {
        if (n > 20) throw IllegalArgumentException("Factorial too large (max 20!)")
        return if (n <= 1) 1L else n * factorial(n - 1)
    }

    fun formatResult(value: Double): String {
        if (value.isNaN()) return "Error"
        if (value.isInfinite()) return if (value > 0) "∞" else "-∞"
        // Show as integer if possible
        if (value == Math.floor(value) && Math.abs(value) < 1e15) {
            return value.toLong().toString()
        }
        // Use scientific notation for very large/small numbers
        return if (Math.abs(value) >= 1e10 || (Math.abs(value) < 1e-6 && value != 0.0)) {
            "%.8e".format(value).replace("e+0", "e").replace("e-0", "e-").replace("e0", "")
        } else {
            "%.10g".format(value).trimEnd('0').trimEnd('.')
        }
    }
}
