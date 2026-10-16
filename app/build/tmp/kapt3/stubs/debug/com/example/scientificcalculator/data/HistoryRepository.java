package com.example.scientificcalculator.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0006J\u0006\u0010\u0011\u001a\u00020\u000eJ\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J\u0016\u0010\u0015\u001a\u00020\u000e2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00140\u0017H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/example/scientificcalculator/data/HistoryRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "KEY_HISTORY", "", "MAX_HISTORY", "", "gson", "Lcom/google/gson/Gson;", "prefs", "Landroid/content/SharedPreferences;", "addEntry", "", "expression", "result", "clearHistory", "getHistory", "", "Lcom/example/scientificcalculator/model/HistoryItem;", "saveHistory", "history", "", "app_debug"})
public final class HistoryRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String KEY_HISTORY = "history_list";
    private final int MAX_HISTORY = 200;
    
    public HistoryRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.scientificcalculator.model.HistoryItem> getHistory() {
        return null;
    }
    
    public final void addEntry(@org.jetbrains.annotations.NotNull()
    java.lang.String expression, @org.jetbrains.annotations.NotNull()
    java.lang.String result) {
    }
    
    public final void clearHistory() {
    }
    
    private final void saveHistory(java.util.List<com.example.scientificcalculator.model.HistoryItem> history) {
    }
}