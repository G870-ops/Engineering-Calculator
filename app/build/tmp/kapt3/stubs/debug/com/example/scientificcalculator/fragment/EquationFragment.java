package com.example.scientificcalculator.fragment;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001*B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\tH\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J$\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u000eH\u0016J\u001a\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0010\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\tH\u0002J\u0018\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u0011H\u0002J\b\u0010#\u001a\u00020\u000eH\u0002J(\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u00132\u0006\u0010\'\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0018\u0010(\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u0013H\u0002J \u0010)\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u00132\u0006\u0010\'\u001a\u00020\u0013H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/example/scientificcalculator/fragment/EquationFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/example/scientificcalculator/databinding/FragmentEquationBinding;", "binding", "getBinding", "()Lcom/example/scientificcalculator/databinding/FragmentEquationBinding;", "equationType", "Lcom/example/scientificcalculator/fragment/EquationFragment$EqType;", "inputs", "", "Landroid/widget/EditText;", "buildCoeffInputs", "", "type", "fmt", "", "d", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "selectType", "showResult", "solution", "steps", "solve", "solveCubic", "a", "b", "c", "solveLinear", "solveQuadratic", "EqType", "app_debug"})
public final class EquationFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.example.scientificcalculator.databinding.FragmentEquationBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private com.example.scientificcalculator.fragment.EquationFragment.EqType equationType = com.example.scientificcalculator.fragment.EquationFragment.EqType.LINEAR;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<android.widget.EditText> inputs = null;
    
    public EquationFragment() {
        super();
    }
    
    private final com.example.scientificcalculator.databinding.FragmentEquationBinding getBinding() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void selectType(com.example.scientificcalculator.fragment.EquationFragment.EqType type) {
    }
    
    private final void buildCoeffInputs(com.example.scientificcalculator.fragment.EquationFragment.EqType type) {
    }
    
    private final void solve() {
    }
    
    private final void solveLinear(double a, double b) {
    }
    
    private final void solveQuadratic(double a, double b, double c) {
    }
    
    private final void solveCubic(double a, double b, double c, double d) {
    }
    
    private final java.lang.String fmt(double d) {
        return null;
    }
    
    private final void showResult(java.lang.String solution, java.lang.String steps) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/example/scientificcalculator/fragment/EquationFragment$EqType;", "", "(Ljava/lang/String;I)V", "LINEAR", "QUADRATIC", "CUBIC", "app_debug"})
    static enum EqType {
        /*public static final*/ LINEAR /* = new LINEAR() */,
        /*public static final*/ QUADRATIC /* = new QUADRATIC() */,
        /*public static final*/ CUBIC /* = new CUBIC() */;
        
        EqType() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.example.scientificcalculator.fragment.EquationFragment.EqType> getEntries() {
            return null;
        }
    }
}