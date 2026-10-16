package com.example.scientificcalculator.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002\u0012\u0013B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0005J \u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u0011H\u0002R#\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0014"}, d2 = {"Lcom/example/scientificcalculator/util/UnitData;", "", "()V", "UNITS", "", "Lcom/example/scientificcalculator/util/UnitData$Category;", "", "Lcom/example/scientificcalculator/util/UnitData$Unit;", "getUNITS", "()Ljava/util/Map;", "convert", "", "value", "from", "to", "category", "convertTemperature", "", "Category", "Unit", "app_debug"})
public final class UnitData {
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<com.example.scientificcalculator.util.UnitData.Category, java.util.List<com.example.scientificcalculator.util.UnitData.Unit>> UNITS = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.scientificcalculator.util.UnitData INSTANCE = null;
    
    private UnitData() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<com.example.scientificcalculator.util.UnitData.Category, java.util.List<com.example.scientificcalculator.util.UnitData.Unit>> getUNITS() {
        return null;
    }
    
    public final double convert(double value, @org.jetbrains.annotations.NotNull()
    com.example.scientificcalculator.util.UnitData.Unit from, @org.jetbrains.annotations.NotNull()
    com.example.scientificcalculator.util.UnitData.Unit to, @org.jetbrains.annotations.NotNull()
    com.example.scientificcalculator.util.UnitData.Category category) {
        return 0.0;
    }
    
    private final double convertTemperature(double value, java.lang.String from, java.lang.String to) {
        return 0.0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013\u00a8\u0006\u0014"}, d2 = {"Lcom/example/scientificcalculator/util/UnitData$Category;", "", "displayName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getDisplayName", "()Ljava/lang/String;", "LENGTH", "MASS", "TEMPERATURE", "AREA", "VOLUME", "SPEED", "TIME", "PRESSURE", "ENERGY", "POWER", "FORCE", "ANGLE", "DATA", "app_debug"})
    public static enum Category {
        /*public static final*/ LENGTH /* = new LENGTH(null) */,
        /*public static final*/ MASS /* = new MASS(null) */,
        /*public static final*/ TEMPERATURE /* = new TEMPERATURE(null) */,
        /*public static final*/ AREA /* = new AREA(null) */,
        /*public static final*/ VOLUME /* = new VOLUME(null) */,
        /*public static final*/ SPEED /* = new SPEED(null) */,
        /*public static final*/ TIME /* = new TIME(null) */,
        /*public static final*/ PRESSURE /* = new PRESSURE(null) */,
        /*public static final*/ ENERGY /* = new ENERGY(null) */,
        /*public static final*/ POWER /* = new POWER(null) */,
        /*public static final*/ FORCE /* = new FORCE(null) */,
        /*public static final*/ ANGLE /* = new ANGLE(null) */,
        /*public static final*/ DATA /* = new DATA(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String displayName = null;
        
        Category(java.lang.String displayName) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDisplayName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.example.scientificcalculator.util.UnitData.Category> getEntries() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\tH\u00c6\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\tH\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f\u00a8\u0006\u001d"}, d2 = {"Lcom/example/scientificcalculator/util/UnitData$Unit;", "", "name", "", "symbol", "toBase", "", "fromBase", "isTemperature", "", "(Ljava/lang/String;Ljava/lang/String;DDZ)V", "getFromBase", "()D", "()Z", "getName", "()Ljava/lang/String;", "getSymbol", "getToBase", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
    public static final class Unit {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String name = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String symbol = null;
        private final double toBase = 0.0;
        private final double fromBase = 0.0;
        private final boolean isTemperature = false;
        
        public Unit(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.String symbol, double toBase, double fromBase, boolean isTemperature) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSymbol() {
            return null;
        }
        
        public final double getToBase() {
            return 0.0;
        }
        
        public final double getFromBase() {
            return 0.0;
        }
        
        public final boolean isTemperature() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        public final double component3() {
            return 0.0;
        }
        
        public final double component4() {
            return 0.0;
        }
        
        public final boolean component5() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.scientificcalculator.util.UnitData.Unit copy(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.String symbol, double toBase, double fromBase, boolean isTemperature) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}