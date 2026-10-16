package com.example.scientificcalculator.fragment

import android.animation.ObjectAnimator
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.scientificcalculator.R
import com.example.scientificcalculator.data.HistoryRepository
import com.example.scientificcalculator.databinding.FragmentCalculatorBinding
import com.example.scientificcalculator.util.ExpressionEvaluator

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    private val historyRepo by lazy { HistoryRepository(requireContext()) }
    private var expression = StringBuilder()
    private var isResult = false
    private var isDegree = true
    private var isInverse = false
    private var lastResult = ""

    private var currentPaletteIndex = 0
    private val palettes = listOf("Aesthetic", "Cyberpunk", "Cosmic", "Emerald")
    private val prefs by lazy { requireContext().getSharedPreferences("calc_prefs", Context.MODE_PRIVATE) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
        setupDisplayLongClick()
        val savedPalette = prefs.getString("color_palette", "Aesthetic") ?: "Aesthetic"
        currentPaletteIndex = palettes.indexOf(savedPalette).coerceAtLeast(0)
        applyPalette(palettes[currentPaletteIndex])
    }

    private fun setupButtons() {
        // Numbers
        val numberBtns = mapOf(
            binding.btn0 to "0", binding.btn1 to "1", binding.btn2 to "2",
            binding.btn3 to "3", binding.btn4 to "4", binding.btn5 to "5",
            binding.btn6 to "6", binding.btn7 to "7", binding.btn8 to "8",
            binding.btn9 to "9", binding.btnDot to "."
        )
        numberBtns.forEach { (btn, char) ->
            btn.setOnClickListener { appendToExpression(char); animateButton(it) }
        }

        // Operators
        binding.btnAdd.setOnClickListener { appendOperator("+"); animateButton(it) }
        binding.btnSubtract.setOnClickListener { appendOperator("−"); animateButton(it) }
        binding.btnMultiply.setOnClickListener { appendOperator("×"); animateButton(it) }
        binding.btnDivide.setOnClickListener { appendOperator("÷"); animateButton(it) }
        binding.btnPercent.setOnClickListener { appendOperator("%"); animateButton(it) }
        binding.btnOpenParen.setOnClickListener { appendToExpression("("); animateButton(it) }
        binding.btnCloseParen.setOnClickListener { appendToExpression(")"); animateButton(it) }

        // Scientific Functions
        binding.btnSin.setOnClickListener { appendFunction(if (isInverse) "asin(" else "sin("); animateButton(it) }
        binding.btnCos.setOnClickListener { appendFunction(if (isInverse) "acos(" else "cos("); animateButton(it) }
        binding.btnTan.setOnClickListener { appendFunction(if (isInverse) "atan(" else "tan("); animateButton(it) }
        binding.btnLog.setOnClickListener { appendFunction("log("); animateButton(it) }
        binding.btnLn.setOnClickListener { appendFunction("ln("); animateButton(it) }
        binding.btnSqrt.setOnClickListener { appendFunction("sqrt("); animateButton(it) }
        binding.btnPow2.setOnClickListener { appendToExpression("^2"); animateButton(it) }
        binding.btnPowN.setOnClickListener { appendOperator("^"); animateButton(it) }
        binding.btnPi.setOnClickListener { appendConstant("π"); animateButton(it) }
        binding.btnE.setOnClickListener { appendConstant("e"); animateButton(it) }
        binding.btnFactorial.setOnClickListener { appendToExpression("!"); animateButton(it) }
        binding.btnAbs.setOnClickListener { appendFunction("abs("); animateButton(it) }
        binding.btnMod.setOnClickListener { appendOperator("%"); animateButton(it) }

        // Special
        binding.btnClear.setOnClickListener { clearAll(); animateButton(it) }
        binding.btnDel.setOnClickListener { deleteLast(); animateButton(it) }
        binding.btnPlusMinus.setOnClickListener { toggleSign(); animateButton(it) }
        binding.btnEquals.setOnClickListener { calculate(); animateButton(it) }

        // Mode Toggles
        binding.btnDeg.setOnClickListener {
            isDegree = true
            ExpressionEvaluator.setDegreeMode(true)
            binding.tvAngleMode.text = "DEG"
            binding.btnDeg.setTextColor(resources.getColor(R.color.neon_green, null))
            binding.btnRad.setTextColor(resources.getColor(R.color.text_dim, null))
        }
        binding.btnRad.setOnClickListener {
            isDegree = false
            ExpressionEvaluator.setDegreeMode(false)
            binding.tvAngleMode.text = "RAD"
            binding.btnRad.setTextColor(resources.getColor(R.color.neon_green, null))
            binding.btnDeg.setTextColor(resources.getColor(R.color.text_dim, null))
        }
        binding.btnInv.setOnClickListener {
            isInverse = !isInverse
            val color = if (isInverse) R.color.neon_cyan else R.color.neon_yellow
            binding.btnInv.setTextColor(resources.getColor(color, null))
            updateInverseLabels()
        }
        binding.btnTheme.setOnClickListener {
            cycleColorPalette()
            animateButton(it)
        }
    }

    private fun updateInverseLabels() {
        if (isInverse) {
            binding.btnSin.text = "sin⁻¹"
            binding.btnCos.text = "cos⁻¹"
            binding.btnTan.text = "tan⁻¹"
        } else {
            binding.btnSin.text = "sin"
            binding.btnCos.text = "cos"
            binding.btnTan.text = "tan"
        }
    }

    private fun appendToExpression(char: String) {
        vibrate()
        if (isResult) {
            // If we pressed a number after a result, start fresh
            if (char[0].isDigit() || char == ".") {
                expression.clear()
            }
            // If operator, keep result and append operator
            isResult = false
        }
        expression.append(char)
        updateDisplay()
    }

    private fun appendOperator(op: String) {
        vibrate()
        if (isResult) isResult = false
        val last = expression.lastOrNull()
        if (last != null && last in listOf('+', '-', '−', '×', '÷', '%', '^')) {
            expression.deleteCharAt(expression.lastIndex)
        }
        expression.append(op)
        updateDisplay()
    }

    private fun appendFunction(func: String) {
        vibrate()
        if (isResult) isResult = false
        expression.append(func)
        updateDisplay()
    }

    private fun appendConstant(const: String) {
        vibrate()
        if (isResult) {
            expression.clear()
            isResult = false
        }
        expression.append(const)
        updateDisplay()
    }

    private fun clearAll() {
        vibrate()
        expression.clear()
        isResult = false
        binding.tvResult.text = "0"
        binding.tvExpression.text = ""
        binding.tvResult.textSize = 38f
    }

    private fun deleteLast() {
        vibrate()
        if (isResult) {
            clearAll()
            return
        }
        if (expression.isNotEmpty()) {
            // Handle multi-character tokens (sin(, cos(, etc.)
            val expr = expression.toString()
            val functionPattern = Regex("(asin\\(|acos\\(|atan\\(|sin\\(|cos\\(|tan\\(|log\\(|ln\\(|sqrt\\(|abs\\()$")
            val match = functionPattern.find(expr)
            if (match != null) {
                expression.delete(match.range.first, expression.length)
            } else {
                expression.deleteCharAt(expression.lastIndex)
            }
            updateDisplay()
        }
    }

    private fun toggleSign() {
        vibrate()
        val expr = expression.toString()
        if (expr.isEmpty()) return
        if (expr.startsWith("-")) {
            expression.deleteCharAt(0)
        } else {
            expression.insert(0, "-")
        }
        updateDisplay()
    }

    private fun calculate() {
        vibrate()
        val expr = expression.toString()
        if (expr.isEmpty()) return

        try {
            val result = ExpressionEvaluator.evaluate(expr)
            val resultStr = ExpressionEvaluator.formatResult(result)

            // Save to history
            historyRepo.addEntry(expr, resultStr)
            lastResult = resultStr

            binding.tvPreviousResult.text = binding.tvExpression.text
            binding.tvExpression.text = expr
            binding.tvResult.text = resultStr
            animateResult()

            // Adjust text size if long
            val len = resultStr.length
            binding.tvResult.textSize = when {
                len > 16 -> 20f
                len > 12 -> 26f
                len > 8 -> 32f
                else -> 38f
            }

            expression.clear()
            expression.append(resultStr)
            isResult = true

        } catch (e: ArithmeticException) {
            showError("Math Error: ${e.message}")
        } catch (e: Exception) {
            showError("Syntax Error")
        }
    }

    private fun showError(msg: String) {
        binding.tvResult.text = msg
        binding.tvResult.textSize = 18f
        expression.clear()
        isResult = true
    }

    private fun updateDisplay() {
        binding.tvExpression.text = expression.toString()
        // Live preview
        val expr = expression.toString()
        if (expr.isNotEmpty() && !expr.endsWith("(") &&
            !expr.last().isLetter() && expr.last() !in listOf('+', '-', '−', '×', '÷', '^', '%')) {
            try {
                val previewVal = ExpressionEvaluator.evaluate(expr)
                val preview = ExpressionEvaluator.formatResult(previewVal)
                if (preview != expr) {
                    binding.tvResult.text = preview
                    val len = preview.length
                    binding.tvResult.textSize = when {
                        len > 16 -> 20f
                        len > 12 -> 26f
                        len > 8 -> 32f
                        else -> 38f
                    }
                }
            } catch (e: Exception) {
                // Silent - expression is incomplete
            }
        }
    }

    private fun animateResult() {
        val anim = ObjectAnimator.ofFloat(binding.tvResult, "scaleX", 0.9f, 1f)
        anim.duration = 150
        anim.interpolator = DecelerateInterpolator()
        anim.start()
        val animY = ObjectAnimator.ofFloat(binding.tvResult, "scaleY", 0.9f, 1f)
        animY.duration = 150
        animY.start()
    }

    private fun animateButton(view: View) {
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.9f, 1.0f)
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.9f, 1.0f)
        scaleX.duration = 100
        scaleY.duration = 100
        scaleX.start()
        scaleY.start()
    }

    private fun vibrate() {
        try {
            val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            vibrator?.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE))
        } catch (e: Exception) { /* No vibration if not available */ }
    }

    private fun setupDisplayLongClick() {
        binding.tvResult.setOnLongClickListener {
            val result = binding.tvResult.text.toString()
            if (result.isNotEmpty() && result != "0") {
                val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clipboard.setPrimaryClip(ClipData.newPlainText("result", result))
                Toast.makeText(context, "Copied: $result", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    private fun cycleColorPalette() {
        currentPaletteIndex = (currentPaletteIndex + 1) % palettes.size
        val newPalette = palettes[currentPaletteIndex]
        prefs.edit().putString("color_palette", newPalette).apply()
        applyPalette(newPalette)
        Toast.makeText(context, "Color Palette: $newPalette", Toast.LENGTH_SHORT).show()
    }

    private data class PaletteColors(
        val trig: Int,
        val log: Int,
        val power: Int,
        val const: Int,
        val paren: Int,
        val arith: Int,
        val del: Int,
        val sign: Int
    )

    private fun applyPalette(paletteName: String) {
        val colors = when (paletteName) {
            "Cyberpunk" -> PaletteColors(
                trig = 0xFFFF007F.toInt(), log = 0xFF00F5FF.toInt(), power = 0xFF39FF14.toInt(),
                const = 0xFFFFE600.toInt(), paren = 0xFF00FFFF.toInt(), arith = 0xFFFF3366.toInt(),
                del = 0xFFFF0055.toInt(), sign = 0xFF00FFCC.toInt()
            )
            "Cosmic" -> PaletteColors(
                trig = 0xFFE0AAFF.toInt(), log = 0xFF8338EC.toInt(), power = 0xFFF72585.toInt(),
                const = 0xFFFFB703.toInt(), paren = 0xFF4CC9F0.toInt(), arith = 0xFFFB8500.toInt(),
                del = 0xFFFF4D6D.toInt(), sign = 0xFF90E0EF.toInt()
            )
            "Emerald" -> PaletteColors(
                trig = 0xFF00F5D4.toInt(), log = 0xFF70E000.toInt(), power = 0xFF38B000.toInt(),
                const = 0xFFCCFF33.toInt(), paren = 0xFF00E676.toInt(), arith = 0xFF00BBF9.toInt(),
                del = 0xFFFF0054.toInt(), sign = 0xFF80ED99.toInt()
            )
            else -> PaletteColors( // "Aesthetic" (Universal Multi-Color)
                trig = 0xFFC77DFF.toInt(), log = 0xFF60A5FA.toInt(), power = 0xFF22D3EE.toInt(),
                const = 0xFFFDE047.toInt(), paren = 0xFF4ADE80.toInt(), arith = 0xFFFB923C.toInt(),
                del = 0xFFF43F5E.toInt(), sign = 0xFF2DD4BF.toInt()
            )
        }

        listOf(binding.btnSin, binding.btnCos, binding.btnTan).forEach { it.setTextColor(colors.trig) }
        listOf(binding.btnLog, binding.btnLn).forEach { it.setTextColor(colors.log) }
        listOf(binding.btnSqrt, binding.btnPow2, binding.btnPowN, binding.btnFactorial, binding.btnAbs).forEach { it.setTextColor(colors.power) }
        listOf(binding.btnPi, binding.btnE).forEach { it.setTextColor(colors.const) }
        listOf(binding.btnOpenParen, binding.btnCloseParen, binding.btnMod, binding.btnPercent).forEach { it.setTextColor(colors.paren) }
        listOf(binding.btnDivide, binding.btnMultiply, binding.btnSubtract, binding.btnAdd).forEach { it.setTextColor(colors.arith) }
        binding.btnDel.setTextColor(colors.del)
        binding.btnPlusMinus.setTextColor(colors.sign)
        binding.btnTheme.text = "🎨 $paletteName"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
