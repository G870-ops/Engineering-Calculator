package com.example.scientificcalculator.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.scientificcalculator.R
import com.example.scientificcalculator.databinding.FragmentUnitConverterBinding
import com.example.scientificcalculator.util.UnitData

class UnitConverterFragment : Fragment() {

    private var _binding: FragmentUnitConverterBinding? = null
    private val binding get() = _binding!!

    private var selectedCategory = UnitData.Category.LENGTH
    private val categories = UnitData.Category.values()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUnitConverterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCategoryBar()
        updateSpinners()

        binding.btnConvert.setOnClickListener {
            performConversion()
        }
    }

    private fun setupCategoryBar() {
        binding.categoryContainer.removeAllViews()
        categories.forEach { cat ->
            val btn = Button(requireContext()).apply {
                text = cat.displayName
                textSize = 11f
                setTextColor(if (cat == selectedCategory)
                    resources.getColor(R.color.neon_cyan, null)
                else
                    resources.getColor(R.color.text_dim, null))
                background = resources.getDrawable(R.drawable.btn_mode_bg, null)
                setPadding(24, 0, 24, 0)
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                ).also { it.marginEnd = 8 }
                setOnClickListener {
                    selectedCategory = cat
                    setupCategoryBar()
                    updateSpinners()
                    binding.resultContainer.visibility = View.GONE
                }
            }
            binding.categoryContainer.addView(btn)
        }
    }

    private fun updateSpinners() {
        val units = UnitData.UNITS[selectedCategory] ?: return
        val unitNames = units.map { "${it.name} (${it.symbol})" }.toTypedArray()

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, unitNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerFrom.adapter = adapter
        binding.spinnerTo.adapter = adapter
        if (units.size > 1) binding.spinnerTo.setSelection(1)

        // Auto-convert on spinner change
        binding.spinnerFrom.onItemSelectedListener = autoConvertListener
        binding.spinnerTo.onItemSelectedListener = autoConvertListener
    }

    private val autoConvertListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, v: View?, pos: Int, id: Long) {
            if (binding.etUnitInput.text.isNotEmpty()) performConversion()
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    private fun performConversion() {
        val input = binding.etUnitInput.text.toString().toDoubleOrNull()
        if (input == null) {
            Toast.makeText(context, "Please enter a valid number", Toast.LENGTH_SHORT).show()
            return
        }

        val units = UnitData.UNITS[selectedCategory] ?: return
        val fromUnit = units[binding.spinnerFrom.selectedItemPosition]
        val toUnit = units[binding.spinnerTo.selectedItemPosition]

        val result = UnitData.convert(input, fromUnit, toUnit, selectedCategory)
        val formattedResult = formatConvResult(result)

        binding.tvUnitResult.text = "$formattedResult ${toUnit.symbol}"
        binding.tvUnitFormula.text = "${formatConvResult(input)} ${fromUnit.symbol} = $formattedResult ${toUnit.symbol}"
        binding.resultContainer.visibility = View.VISIBLE
    }

    private fun formatConvResult(value: Double): String {
        if (value.isNaN() || value.isInfinite()) return "Error"
        return if (value == Math.floor(value) && Math.abs(value) < 1e12)
            value.toLong().toString()
        else "%.8g".format(value).trimEnd('0').trimEnd('.')
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
