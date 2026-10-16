package com.example.scientificcalculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.scientificcalculator.databinding.FragmentStatisticsBinding
import com.example.scientificcalculator.databinding.ItemStatCardBinding
import kotlin.math.*

class StatisticsFragment : Fragment() {

    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCalculateStats.setOnClickListener { calculateStats() }
    }

    private fun calculateStats() {
        val input = binding.etDataInput.text.toString()
        val numbers = input.split(",", " ", "\n")
            .mapNotNull { it.trim().toDoubleOrNull() }

        if (numbers.size < 2) {
            Toast.makeText(context, "Enter at least 2 comma-separated numbers", Toast.LENGTH_SHORT).show()
            return
        }

        val sorted = numbers.sorted()
        val n = numbers.size
        val sum = numbers.sum()
        val mean = sum / n
        val median = if (n % 2 == 0)
            (sorted[n / 2 - 1] + sorted[n / 2]) / 2.0
        else sorted[n / 2]
        val mode = findMode(numbers)
        val range = sorted.last() - sorted.first()
        val variance = numbers.sumOf { (it - mean).pow(2) } / n
        val popVariance = variance  // population variance
        val stdDev = sqrt(popVariance)

        // Set cards using ViewBinding
        setCard(binding.cardCount, "COUNT (n)", n.toString())
        setCard(binding.cardSum, "SUM (Σx)", fmt(sum))
        setCard(binding.cardMean, "MEAN (x̄)", fmt(mean))
        setCard(binding.cardMedian, "MEDIAN", fmt(median))
        setCard(binding.cardMode, "MODE", mode)
        setCard(binding.cardRange, "RANGE", fmt(range))
        setCard(binding.cardVariance, "VARIANCE (σ²)", fmt(popVariance))
        setCard(binding.cardStdDev, "STD DEV (σ)", fmt(stdDev))
        setCard(binding.cardMin, "MINIMUM", fmt(sorted.first()))
        setCard(binding.cardMax, "MAXIMUM", fmt(sorted.last()))

        binding.tvSortedData.text = sorted.joinToString(", ") { fmt(it) }
        binding.statsResultContainer.visibility = View.VISIBLE
    }

    private fun setCard(card: ItemStatCardBinding, label: String, value: String) {
        card.tvStatLabel.text = label
        card.tvStatValue.text = value
    }

    private fun findMode(numbers: List<Double>): String {
        val freq = numbers.groupBy { it }.mapValues { it.value.size }
        val maxFreq = freq.values.maxOrNull() ?: 1
        if (maxFreq == 1) return "No mode"
        val modes = freq.filter { it.value == maxFreq }.keys.sorted()
        return if (modes.size > 4) "${modes.take(4).joinToString(", ") { fmt(it) }}..."
        else modes.joinToString(", ") { fmt(it) }
    }

    private fun fmt(d: Double): String {
        if (d.isNaN() || d.isInfinite()) return "Error"
        return if (d == Math.floor(d) && Math.abs(d) < 1e10) d.toLong().toString()
        else "%.6g".format(d).trimEnd('0').trimEnd('.')
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
