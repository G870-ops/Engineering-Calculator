package com.example.scientificcalculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scientificcalculator.R
import com.example.scientificcalculator.databinding.FragmentFormulaBinding
import com.example.scientificcalculator.util.FormulaData

class FormulaFragment : Fragment() {

    private var _binding: FragmentFormulaBinding? = null
    private val binding get() = _binding!!

    private var selectedCategory = "All"
    private lateinit var adapter: FormulaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormulaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupCategoryBar()
    }

    private fun setupRecyclerView() {
        adapter = FormulaAdapter(FormulaData.ALL_FORMULAS)
        binding.rvFormulas.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFormulas.adapter = adapter
    }

    private fun setupCategoryBar() {
        binding.formulaCategoryBar.removeAllViews()
        val allCats = listOf("All") + FormulaData.CATEGORIES
        allCats.forEach { cat ->
            val btn = Button(requireContext()).apply {
                text = cat
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
                    filterFormulas()
                }
            }
            binding.formulaCategoryBar.addView(btn)
        }
    }

    private fun filterFormulas() {
        val filtered = if (selectedCategory == "All")
            FormulaData.ALL_FORMULAS
        else
            FormulaData.ALL_FORMULAS.filter { it.category == selectedCategory }
        adapter.updateData(filtered)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class FormulaAdapter(
    private var formulas: List<FormulaData.Formula>
) : RecyclerView.Adapter<FormulaAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCategory: TextView = view.findViewById(R.id.tvFormulaCategory)
        val tvName: TextView = view.findViewById(R.id.tvFormulaName)
        val tvEquation: TextView = view.findViewById(R.id.tvFormulaEquation)
        val tvDesc: TextView = view.findViewById(R.id.tvFormulaDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_formula, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val formula = formulas[position]
        holder.tvCategory.text = formula.category
        holder.tvName.text = formula.name
        holder.tvEquation.text = formula.equation
        holder.tvDesc.text = formula.description
    }

    override fun getItemCount() = formulas.size

    fun updateData(newFormulas: List<FormulaData.Formula>) {
        formulas = newFormulas
        notifyDataSetChanged()
    }
}
