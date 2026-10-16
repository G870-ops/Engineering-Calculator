package com.example.scientificcalculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.scientificcalculator.R
import com.example.scientificcalculator.databinding.FragmentMatrixBinding
import kotlin.math.roundToInt
import kotlin.random.Random

class MatrixFragment : Fragment() {

    private var _binding: FragmentMatrixBinding? = null
    private val binding get() = _binding!!

    private var matrixSize = 2
    private val cellsA = mutableListOf<MutableList<EditText>>()
    private val cellsB = mutableListOf<MutableList<EditText>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatrixBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buildMatrices(2)

        binding.btn2x2.setOnClickListener { selectSize(2) }
        binding.btn3x3.setOnClickListener { selectSize(3) }
        binding.btn4x4.setOnClickListener { selectSize(4) }

        binding.btnFillRandom.setOnClickListener { fillRandom() }
        binding.btnDeterminant.setOnClickListener { computeDeterminant() }
        binding.btnInverse.setOnClickListener { computeInverse() }
        binding.btnTranspose.setOnClickListener { computeTranspose() }
        binding.btnMatrixMultiply.setOnClickListener { computeMultiply() }
        binding.btnMatrixAdd.setOnClickListener { computeAdd() }
        binding.btnTrace.setOnClickListener { computeTrace() }
    }

    private fun selectSize(size: Int) {
        matrixSize = size
        val cyan = resources.getColor(R.color.neon_cyan, null)
        val dim = resources.getColor(R.color.text_dim, null)
        binding.btn2x2.setTextColor(if (size == 2) cyan else dim)
        binding.btn3x3.setTextColor(if (size == 3) cyan else dim)
        binding.btn4x4.setTextColor(if (size == 4) cyan else dim)
        buildMatrices(size)
        binding.matrixResultContainer.visibility = View.GONE
    }

    private fun buildMatrices(size: Int) {
        cellsA.clear(); cellsB.clear()
        binding.matrixAContainer.removeAllViews()
        binding.matrixBContainer.removeAllViews()
        repeat(size) { r ->
            val rowA = mutableListOf<EditText>()
            val rowB = mutableListOf<EditText>()
            val rowViewA = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.HORIZONTAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                ).also { it.bottomMargin = 6 }
            }
            val rowViewB = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.HORIZONTAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                ).also { it.bottomMargin = 6 }
            }
            repeat(size) { _ ->
                rowA.add(createCell(rowViewA)); rowB.add(createCell(rowViewB))
            }
            cellsA.add(rowA); cellsB.add(rowB)
            binding.matrixAContainer.addView(rowViewA)
            binding.matrixBContainer.addView(rowViewB)
        }
    }

    private fun createCell(parent: LinearLayout): EditText {
        return EditText(requireContext()).apply {
            layoutParams = LinearLayout.LayoutParams(0, 48).also {
                it.weight = 1f; it.marginEnd = 6
            }
            hint = "0"; textSize = 14f
            setTextColor(resources.getColor(R.color.text_primary, null))
            setHintTextColor(resources.getColor(R.color.text_dim, null))
            background = resources.getDrawable(R.drawable.input_bg, null)
            setPadding(8, 4, 8, 4)
            inputType = android.text.InputType.TYPE_CLASS_NUMBER or
                    android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL or
                    android.text.InputType.TYPE_NUMBER_FLAG_SIGNED
            gravity = android.view.Gravity.CENTER
            parent.addView(this)
        }
    }

    private fun fillRandom() {
        cellsA.forEach { row -> row.forEach { cell -> cell.setText(Random.nextInt(-9, 10).toString()) } }
        cellsB.forEach { row -> row.forEach { cell -> cell.setText(Random.nextInt(-9, 10).toString()) } }
    }

    private fun readMatrix(cells: List<List<EditText>>): Array<DoubleArray>? {
        return try {
            Array(cells.size) { r ->
                DoubleArray(cells[r].size) { c ->
                    cells[r][c].text.toString().toDoubleOrNull() ?: 0.0
                }
            }
        } catch (e: Exception) { null }
    }

    private fun computeDeterminant() {
        val a = readMatrix(cellsA) ?: return showError("Invalid matrix")
        val det = determinant(a)
        showResult("Determinant of A:\ndet(A) = ${formatNum(det)}")
    }

    private fun computeInverse() {
        val a = readMatrix(cellsA) ?: return showError("Invalid matrix")
        val inv = inverse(a) ?: return showResult("Matrix is singular — no inverse exists")
        showResult("Inverse of A:\n${formatMatrix(inv)}")
    }

    private fun computeTranspose() {
        val a = readMatrix(cellsA) ?: return showError("Invalid matrix")
        val n = a.size; val m = a[0].size
        val t = Array(m) { r -> DoubleArray(n) { c -> a[c][r] } }
        showResult("Transpose of A:\n${formatMatrix(t)}")
    }

    private fun computeMultiply() {
        val a = readMatrix(cellsA) ?: return showError("Invalid matrix A")
        val b = readMatrix(cellsB) ?: return showError("Invalid matrix B")
        if (a[0].size != b.size) return showError("Incompatible sizes: A cols ≠ B rows")
        val result = Array(a.size) { r ->
            DoubleArray(b[0].size) { c ->
                a[r].indices.sumOf { k -> a[r][k] * b[k][c] }
            }
        }
        showResult("A × B =\n${formatMatrix(result)}")
    }

    private fun computeAdd() {
        val a = readMatrix(cellsA) ?: return showError("Invalid matrix A")
        val b = readMatrix(cellsB) ?: return showError("Invalid matrix B")
        if (a.size != b.size || a[0].size != b[0].size) return showError("Matrices must be same size")
        val result = Array(a.size) { r -> DoubleArray(a[0].size) { c -> a[r][c] + b[r][c] } }
        showResult("A + B =\n${formatMatrix(result)}")
    }

    private fun computeTrace() {
        val a = readMatrix(cellsA) ?: return showError("Invalid matrix")
        if (a.size != a[0].size) return showError("Trace requires square matrix")
        val trace = a.indices.sumOf { i -> a[i][i] }
        showResult("Trace of A:\ntr(A) = ${formatNum(trace)}")
    }

    private fun determinant(m: Array<DoubleArray>): Double {
        val n = m.size
        if (n == 1) return m[0][0]
        if (n == 2) return m[0][0] * m[1][1] - m[0][1] * m[1][0]
        // LU decomposition for larger matrices
        val lu = m.map { it.clone() }.toTypedArray()
        var sign = 1.0
        for (col in 0 until n) {
            var maxRow = col
            for (row in col + 1 until n) {
                if (Math.abs(lu[row][col]) > Math.abs(lu[maxRow][col])) maxRow = row
            }
            if (maxRow != col) { val tmp = lu[col]; lu[col] = lu[maxRow]; lu[maxRow] = tmp; sign *= -1 }
            if (lu[col][col] == 0.0) return 0.0
            for (row in col + 1 until n) {
                val factor = lu[row][col] / lu[col][col]
                for (k in col until n) lu[row][k] -= factor * lu[col][k]
            }
        }
        return sign * (0 until n).fold(1.0) { acc, i -> acc * lu[i][i] }
    }

    private fun inverse(m: Array<DoubleArray>): Array<DoubleArray>? {
        val n = m.size
        val aug = Array(n) { r -> DoubleArray(2 * n) { c -> if (c < n) m[r][c] else if (c - n == r) 1.0 else 0.0 } }
        for (col in 0 until n) {
            var maxRow = col
            for (row in col + 1 until n)
                if (Math.abs(aug[row][col]) > Math.abs(aug[maxRow][col])) maxRow = row
            val tmp = aug[col]; aug[col] = aug[maxRow]; aug[maxRow] = tmp
            val pivot = aug[col][col]
            if (Math.abs(pivot) < 1e-10) return null
            for (k in 0 until 2 * n) aug[col][k] /= pivot
            for (row in 0 until n) {
                if (row == col) continue
                val factor = aug[row][col]
                for (k in 0 until 2 * n) aug[row][k] -= factor * aug[col][k]
            }
        }
        return Array(n) { r -> DoubleArray(n) { c -> aug[r][c + n] } }
    }

    private fun formatMatrix(m: Array<DoubleArray>): String {
        return m.joinToString("\n") { row ->
            "[ " + row.joinToString("  ") { formatNum(it) } + " ]"
        }
    }

    private fun formatNum(d: Double): String {
        val rounded = (d * 1e9).roundToInt() / 1e9
        return if (rounded == Math.floor(rounded) && Math.abs(rounded) < 1e8)
            rounded.toLong().toString()
        else "%.4g".format(rounded)
    }

    private fun showResult(text: String) {
        binding.tvMatrixResult.text = text
        binding.matrixResultContainer.visibility = View.VISIBLE
    }

    private fun showError(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
