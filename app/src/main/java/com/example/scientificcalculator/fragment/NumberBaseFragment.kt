package com.example.scientificcalculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.scientificcalculator.R
import com.example.scientificcalculator.databinding.FragmentNumberBaseBinding

class NumberBaseFragment : Fragment() {

    private var _binding: FragmentNumberBaseBinding? = null
    private val binding get() = _binding!!

    private enum class Base { DEC, BIN, OCT, HEX }
    private var selectedBase = Base.DEC

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNumberBaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectBase(Base.DEC)

        binding.btnFromDec.setOnClickListener { selectBase(Base.DEC) }
        binding.btnFromBin.setOnClickListener { selectBase(Base.BIN) }
        binding.btnFromOct.setOnClickListener { selectBase(Base.OCT) }
        binding.btnFromHex.setOnClickListener { selectBase(Base.HEX) }
        binding.btnConvertBase.setOnClickListener { convertBase() }
    }

    private fun selectBase(base: Base) {
        selectedBase = base
        val cyan = resources.getColor(R.color.neon_cyan, null)
        val dim = resources.getColor(R.color.text_dim, null)
        binding.btnFromDec.setTextColor(if (base == Base.DEC) cyan else dim)
        binding.btnFromBin.setTextColor(if (base == Base.BIN) cyan else dim)
        binding.btnFromOct.setTextColor(if (base == Base.OCT) cyan else dim)
        binding.btnFromHex.setTextColor(if (base == Base.HEX) cyan else dim)

        val hint = when (base) {
            Base.DEC -> "e.g. 255"
            Base.BIN -> "e.g. 11111111"
            Base.OCT -> "e.g. 377"
            Base.HEX -> "e.g. FF or ff"
        }
        binding.etBaseInput.hint = hint
        binding.baseResultContainer.visibility = View.GONE
    }

    private fun convertBase() {
        val input = binding.etBaseInput.text.toString().trim()
        if (input.isEmpty()) {
            Toast.makeText(context, "Please enter a number", Toast.LENGTH_SHORT).show()
            return
        }

        val decValue: Long = try {
            when (selectedBase) {
                Base.DEC -> input.toLong()
                Base.BIN -> input.toLong(2)
                Base.OCT -> input.toLong(8)
                Base.HEX -> input.toLong(16)
            }
        } catch (e: NumberFormatException) {
            Toast.makeText(context, "Invalid ${selectedBase.name} number", Toast.LENGTH_SHORT).show()
            return
        }

        val bin = java.lang.Long.toBinaryString(decValue).chunked(4).joinToString(" ")
        val oct = java.lang.Long.toOctalString(decValue)
        val hex = java.lang.Long.toHexString(decValue).uppercase()

        binding.tvDecResult.text = decValue.toString()
        binding.tvBinResult.text = bin
        binding.tvOctResult.text = oct
        binding.tvHexResult.text = hex
        binding.baseResultContainer.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
