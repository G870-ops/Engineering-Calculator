package com.example.scientificcalculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.scientificcalculator.databinding.FragmentComplexBinding
import com.example.scientificcalculator.model.ComplexNumber

class ComplexFragment : Fragment() {

    private var _binding: FragmentComplexBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComplexBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnComplexAdd.setOnClickListener {
            calculateBinary { a, b -> a + b }
        }

        binding.btnComplexSub.setOnClickListener {
            calculateBinary { a, b -> a - b }
        }

        binding.btnComplexMul.setOnClickListener {
            calculateBinary { a, b -> a * b }
        }

        binding.btnComplexDiv.setOnClickListener {
            calculateBinary { a, b ->
                try {
                    a / b
                } catch (e: ArithmeticException) {
                    null
                }
            }
        }

        binding.btnComplexModA.setOnClickListener {
            val a = getNumberA()
            val mod = a.modulus()
            val thetaRad = a.argument()
            val thetaDeg = Math.toDegrees(thetaRad)

            binding.complexResultContainer.visibility = View.VISIBLE
            binding.tvComplexResult.text = "|A| = %.6g".format(mod).trimEnd('0').trimEnd('.')
            binding.tvComplexPolar.text = "Angle θ = %.2f° (%.4f rad)".format(thetaDeg, thetaRad)
        }

        binding.btnComplexConjA.setOnClickListener {
            val a = getNumberA()
            val conj = a.conjugate()
            val thetaRad = conj.argument()
            val thetaDeg = Math.toDegrees(thetaRad)

            binding.complexResultContainer.visibility = View.VISIBLE
            binding.tvComplexResult.text = "Ā = $conj"
            binding.tvComplexPolar.text = "Polar: %.4f ∠ %.2f°".format(conj.modulus(), thetaDeg)
        }
    }

    private fun getNumberA(): ComplexNumber {
        val r = binding.etRealA.text.toString().toDoubleOrNull() ?: 0.0
        val i = binding.etImagA.text.toString().toDoubleOrNull() ?: 0.0
        return ComplexNumber(r, i)
    }

    private fun getNumberB(): ComplexNumber {
        val r = binding.etRealB.text.toString().toDoubleOrNull() ?: 0.0
        val i = binding.etImagB.text.toString().toDoubleOrNull() ?: 0.0
        return ComplexNumber(r, i)
    }

    private fun calculateBinary(op: (ComplexNumber, ComplexNumber) -> ComplexNumber?) {
        val a = getNumberA()
        val b = getNumberB()
        val result = op(a, b)

        if (result == null) {
            Toast.makeText(context, "Error: Division by zero", Toast.LENGTH_SHORT).show()
            binding.complexResultContainer.visibility = View.VISIBLE
            binding.tvComplexResult.text = "Error: Division by zero"
            binding.tvComplexPolar.text = ""
            return
        }

        val r = result.modulus()
        val thetaRad = result.argument()
        val thetaDeg = Math.toDegrees(thetaRad)

        binding.complexResultContainer.visibility = View.VISIBLE
        binding.tvComplexResult.text = "= $result"
        binding.tvComplexPolar.text = "Polar: %.4f ∠ %.2f° (%.4f rad)".format(r, thetaDeg, thetaRad)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
