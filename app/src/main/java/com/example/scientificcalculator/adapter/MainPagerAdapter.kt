package com.example.scientificcalculator.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.scientificcalculator.fragment.*

class MainPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount() = 9

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CalculatorFragment()
            1 -> UnitConverterFragment()
            2 -> MatrixFragment()
            3 -> EquationFragment()
            4 -> StatisticsFragment()
            5 -> NumberBaseFragment()
            6 -> FormulaFragment()
            7 -> HistoryFragment()
            8 -> ComplexFragment()
            else -> CalculatorFragment()
        }
    }
}
