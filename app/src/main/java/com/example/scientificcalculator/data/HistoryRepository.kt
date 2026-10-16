package com.example.scientificcalculator.data

import android.content.Context
import android.content.SharedPreferences
import com.example.scientificcalculator.model.HistoryItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HistoryRepository(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("calculator_history", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val KEY_HISTORY = "history_list"
    private val MAX_HISTORY = 200

    fun getHistory(): MutableList<HistoryItem> {
        val json = prefs.getString(KEY_HISTORY, null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<HistoryItem>>() {}.type
        return try {
            gson.fromJson(json, type) ?: mutableListOf()
        } catch (e: Exception) {
            mutableListOf()
        }
    }

    fun addEntry(expression: String, result: String) {
        val history = getHistory()
        history.add(0, HistoryItem(expression = expression, result = result))
        if (history.size > MAX_HISTORY) {
            history.removeAt(history.lastIndex)
        }
        saveHistory(history)
    }

    fun clearHistory() {
        prefs.edit().remove(KEY_HISTORY).apply()
    }

    private fun saveHistory(history: List<HistoryItem>) {
        prefs.edit().putString(KEY_HISTORY, gson.toJson(history)).apply()
    }
}
