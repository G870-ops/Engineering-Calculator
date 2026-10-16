package com.example.scientificcalculator.fragment

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.scientificcalculator.R
import com.example.scientificcalculator.databinding.FragmentEquationBinding
import kotlin.math.*

class EquationFragment : Fragment() {

    private var _binding: FragmentEquationBinding? = null
    private val binding get() = _binding!!

    private enum class EqType { LINEAR, QUADRATIC, CUBIC }
    private var equationType = EqType.LINEAR
    private val inputs = mutableListOf<EditText>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEquationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectType(EqType.LINEAR)

        binding.btnLinear.setOnClickListener { selectType(EqType.LINEAR) }
        binding.btnQuadratic.setOnClickListener { selectType(EqType.QUADRATIC) }
        binding.btnCubic.setOnClickListener { selectType(EqType.CUBIC) }
        binding.btnSolve.setOnClickListener { solve() }
    }

    private fun selectType(type: EqType) {
        equationType = type
        val cyan = resources.getColor(R.color.neon_cyan, null)
        val dim = resources.getColor(R.color.text_dim, null)
        binding.btnLinear.setTextColor(if (type == EqType.LINEAR) cyan else dim)
        binding.btnQuadratic.setTextColor(if (type == EqType.QUADRATIC) cyan else dim)
        binding.btnCubic.setTextColor(if (type == EqType.CUBIC) cyan else dim)
        binding.solutionContainer.visibility = View.GONE
        buildCoeffInputs(type)
        binding.tvEquationHint.text = when (type) {
            EqType.LINEAR -> "ax + b = 0"
            EqType.QUADRATIC -> "ax² + bx + c = 0"
            EqType.CUBIC -> "ax³ + bx² + cx + d = 0"
        }
    }

    private fun buildCoeffInputs(type: EqType) {
        binding.coeffContainer.removeAllViews()
        inputs.clear()
        val labels = when (type) {
            EqType.LINEAR -> listOf("a (coefficient of x)", "b (constant)")
            EqType.QUADRATIC -> listOf("a (x²)", "b (x)", "c (constant)")
            EqType.CUBIC -> listOf("a (x³)", "b (x²)", "c (x)", "d (constant)")
        }
        labels.forEach { label ->
            val row = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = android.view.Gravity.CENTER_VERTICAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                ).also { it.bottomMargin = 10 }
            }
            val tvLabel = android.widget.TextView(requireContext()).apply {
                text = label
                textSize = 13f
                setTextColor(resources.getColor(R.color.text_secondary, null))
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                    .also { it.marginEnd = 10 }
            }
            val et = EditText(requireContext()).apply {
                hint = "0"
                textSize = 16f
                setTextColor(resources.getColor(R.color.text_primary, null))
                setHintTextColor(resources.getColor(R.color.text_dim, null))
                background = resources.getDrawable(R.drawable.input_bg, null)
                setPadding(16, 10, 16, 10)
                inputType = InputType.TYPE_CLASS_NUMBER or
                        InputType.TYPE_NUMBER_FLAG_DECIMAL or
                        InputType.TYPE_NUMBER_FLAG_SIGNED
                layoutParams = LinearLayout.LayoutParams(0, 50, 1f)
                gravity = android.view.Gravity.CENTER
            }
            inputs.add(et)
            row.addView(tvLabel)
            row.addView(et)
            binding.coeffContainer.addView(row)
        }
    }

    private fun solve() {
        val coeffs = inputs.map { it.text.toString().toDoubleOrNull() ?: 0.0 }
        when (equationType) {
            EqType.LINEAR -> solveLinear(coeffs[0], coeffs[1])
            EqType.QUADRATIC -> solveQuadratic(coeffs[0], coeffs[1], coeffs[2])
            EqType.CUBIC -> solveCubic(coeffs[0], coeffs[1], coeffs[2], coeffs[3])
        }
    }

    private fun solveLinear(a: Double, b: Double) {
        if (a == 0.0) {
            showResult(
                if (b == 0.0) "Infinite solutions (0 = 0)"
                else "No solution (${b} = 0 is false)",
                "With a=0, equation becomes ${b} = 0"
            )
            return
        }
        val x = -b / a
        showResult(
            "x = ${fmt(x)}",
            "Step 1: ${a}x + ${b} = 0\nStep 2: ${a}x = ${-b}\nStep 3: x = ${-b}/${a} = ${fmt(x)}"
        )
    }

    private fun solveQuadratic(a: Double, b: Double, c: Double) {
        if (a == 0.0) { solveLinear(b, c); return }
        val disc = b * b - 4 * a * c
        val solution: String
        val steps: String
        when {
            disc > 0 -> {
                val x1 = (-b + sqrt(disc)) / (2 * a)
                val x2 = (-b - sqrt(disc)) / (2 * a)
                solution = "x₁ = ${fmt(x1)}\nx₂ = ${fmt(x2)}"
                steps = "Discriminant Δ = b²-4ac = ${fmt(disc)} > 0\n" +
                        "Two real roots:\nx₁ = (-${b} + √${fmt(disc)}) / (2×${a})\nx₂ = (-${b} - √${fmt(disc)}) / (2×${a})"
            }
            disc == 0.0 -> {
                val x = -b / (2 * a)
                solution = "x = ${fmt(x)}  (repeated root)"
                steps = "Discriminant Δ = 0\nOne repeated root:\nx = -b/2a = ${-b}/${2 * a} = ${fmt(x)}"
            }
            else -> {
                val realPart = -b / (2 * a)
                val imagPart = sqrt(-disc) / (2 * a)
                solution = "x₁ = ${fmt(realPart)} + ${fmt(imagPart)}i\nx₂ = ${fmt(realPart)} - ${fmt(imagPart)}i"
                steps = "Discriminant Δ = ${fmt(disc)} < 0\nTwo complex roots"
            }
        }
        showResult(solution, steps)
    }

    private fun solveCubic(a: Double, b: Double, c: Double, d: Double) {
        if (a == 0.0) { solveQuadratic(b, c, d); return }
        // Cardano's method (depressed cubic)
        val p = (3 * a * c - b * b) / (3 * a * a)
        val q = (2 * b * b * b - 9 * a * b * c + 27 * a * a * d) / (27 * a * a * a)
        val disc = -(4 * p * p * p + 27 * q * q)
        val offset = -b / (3 * a)

        val roots = when {
            disc > 0 -> {
                val m = 2 * sqrt(-p / 3)
                (0..2).map { k ->
                    m * cos(1.0 / 3 * acos(3 * q / (p * m) * sqrt(-3 / p)) - 2 * Math.PI * k / 3) + offset
                }
            }
            disc == 0.0 -> {
                val x1 = 3 * q / p + offset
                val x2 = -3 * q / (2 * p) + offset
                listOf(x1, x2, x2)
            }
            else -> {
                val A = cbrt(-q / 2 + sqrt(q * q / 4 + p * p * p / 27))
                val B = cbrt(-q / 2 - sqrt(q * q / 4 + p * p * p / 27))
                listOf(A + B + offset)
            }
        }
        val solution = roots.mapIndexed { i, r -> "x${i + 1} = ${fmt(r)}" }.joinToString("\n")
        showResult(solution, "Solved using Cardano's formula\nDiscriminant Δ = ${fmt(disc)}")
    }

    private fun fmt(d: Double): String {
        return if (d == Math.floor(d) && Math.abs(d) < 1e9) d.toLong().toString()
        else "%.6g".format(d).trimEnd('0').trimEnd('.')
    }

    private fun showResult(solution: String, steps: String) {
        binding.tvSolution.text = solution
        binding.tvSteps.text = steps
        binding.solutionContainer.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
