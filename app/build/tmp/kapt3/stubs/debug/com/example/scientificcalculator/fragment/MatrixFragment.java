package com.example.scientificcalculator.fragment;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0013\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0002J\b\u0010\u0011\u001a\u00020\u000fH\u0002J\b\u0010\u0012\u001a\u00020\u000fH\u0002J\b\u0010\u0013\u001a\u00020\u000fH\u0002J\b\u0010\u0014\u001a\u00020\u000fH\u0002J\b\u0010\u0015\u001a\u00020\u000fH\u0002J\b\u0010\u0016\u001a\u00020\u000fH\u0002J\u0010\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u001b\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0002\u00a2\u0006\u0002\u0010\u001fJ\b\u0010 \u001a\u00020\u000fH\u0002J\u001b\u0010!\u001a\u00020\"2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0002\u00a2\u0006\u0002\u0010#J\u0010\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020\u001bH\u0002J#\u0010&\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001d2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0002\u00a2\u0006\u0002\u0010\'J$\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\b\u00100\u001a\u00020\u000fH\u0016J\u001a\u00101\u001a\u00020\u000f2\u0006\u00102\u001a\u00020)2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J)\u00103\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001d2\u0012\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0505H\u0002\u00a2\u0006\u0002\u00106J\u0010\u00107\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0002J\u0010\u00108\u001a\u00020\u000f2\u0006\u00109\u001a\u00020\"H\u0002J\u0010\u0010:\u001a\u00020\u000f2\u0006\u0010;\u001a\u00020\"H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006<"}, d2 = {"Lcom/example/scientificcalculator/fragment/MatrixFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/example/scientificcalculator/databinding/FragmentMatrixBinding;", "binding", "getBinding", "()Lcom/example/scientificcalculator/databinding/FragmentMatrixBinding;", "cellsA", "", "Landroid/widget/EditText;", "cellsB", "matrixSize", "", "buildMatrices", "", "size", "computeAdd", "computeDeterminant", "computeInverse", "computeMultiply", "computeTrace", "computeTranspose", "createCell", "parent", "Landroid/widget/LinearLayout;", "determinant", "", "m", "", "", "([[D)D", "fillRandom", "formatMatrix", "", "([[D)Ljava/lang/String;", "formatNum", "d", "inverse", "([[D)[[D", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "readMatrix", "cells", "", "(Ljava/util/List;)[[D", "selectSize", "showError", "msg", "showResult", "text", "app_debug"})
public final class MatrixFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.example.scientificcalculator.databinding.FragmentMatrixBinding _binding;
    private int matrixSize = 2;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.util.List<android.widget.EditText>> cellsA = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.util.List<android.widget.EditText>> cellsB = null;
    
    public MatrixFragment() {
        super();
    }
    
    private final com.example.scientificcalculator.databinding.FragmentMatrixBinding getBinding() {
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
    
    private final void selectSize(int size) {
    }
    
    private final void buildMatrices(int size) {
    }
    
    private final android.widget.EditText createCell(android.widget.LinearLayout parent) {
        return null;
    }
    
    private final void fillRandom() {
    }
    
    private final double[][] readMatrix(java.util.List<? extends java.util.List<? extends android.widget.EditText>> cells) {
        return null;
    }
    
    private final void computeDeterminant() {
    }
    
    private final void computeInverse() {
    }
    
    private final void computeTranspose() {
    }
    
    private final void computeMultiply() {
    }
    
    private final void computeAdd() {
    }
    
    private final void computeTrace() {
    }
    
    private final double determinant(double[][] m) {
        return 0.0;
    }
    
    private final double[][] inverse(double[][] m) {
        return null;
    }
    
    private final java.lang.String formatMatrix(double[][] m) {
        return null;
    }
    
    private final java.lang.String formatNum(double d) {
        return null;
    }
    
    private final void showResult(java.lang.String text) {
    }
    
    private final void showError(java.lang.String msg) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}