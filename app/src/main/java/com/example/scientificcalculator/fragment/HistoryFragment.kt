package com.example.scientificcalculator.fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scientificcalculator.adapter.HistoryAdapter
import com.example.scientificcalculator.data.HistoryRepository
import com.example.scientificcalculator.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val historyRepo by lazy { HistoryRepository(requireContext()) }
    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HistoryAdapter(emptyList()) { item ->
            // Copy result or expression to clipboard on click
            val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Calculation", "${item.expression} = ${item.result}")
            clipboard.setPrimaryClip(clip)
            Toast.makeText(requireContext(), "Copied: ${item.result}", Toast.LENGTH_SHORT).show()
        }

        binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHistory.adapter = adapter

        binding.btnClearHistory.setOnClickListener {
            historyRepo.clearHistory()
            loadHistory()
            Toast.makeText(requireContext(), "History cleared", Toast.LENGTH_SHORT).show()
        }

        loadHistory()
    }

    override fun onResume() {
        super.onResume()
        loadHistory()
    }

    private fun loadHistory() {
        val list = historyRepo.getHistory()
        if (list.isEmpty()) {
            binding.rvHistory.visibility = View.GONE
            binding.tvHistoryEmpty.visibility = View.VISIBLE
        } else {
            binding.rvHistory.visibility = View.VISIBLE
            binding.tvHistoryEmpty.visibility = View.GONE
            adapter.updateData(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
