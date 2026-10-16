# 🌌 Sci-Fi Scientific Calculator

<div align="center">
  <img src="https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" />
  <img src="https://img.shields.io/badge/Language-Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" />
  <img src="https://img.shields.io/badge/UI-XML%20%26%20Custom%20Drawables-FF6F00?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Theme-Interstellar%20Sci--Fi-00FFFF?style=for-the-badge" />
</div>

---

## 🚀 Overview

**Sci-Fi Scientific Calculator** is an advanced, feature-rich Android application designed with an immersive cosmic and interstellar aesthetic. Combining a futuristic neon-glow user interface with modular architecture, it delivers a high-performance computational suite for students, engineers, and tech enthusiasts.

---

## ✨ Key Features & Modules

* **🎨 Immersive Sci-Fi UI Theme:** Custom-crafted glowing neon borders, multi-textured button groups (`btn_number_bg`, `btn_equal_bg`, `btn_del_bg`, etc.), and a sleek dark-mode cosmic background.
* **🔢 Comprehensive Calculator Fragment:** Handles standard arithmetic, algebraic inputs, and instant precision calculations.
* **📐 Advanced Mathematical Tools:**
  * **Equation Solver (`EquationFragment`)**
  * **Complex Number Operations (`ComplexFragment`)**
  * **Matrix Calculations (`MatrixFragment`)**
  * **Statistics & Number Base Conversions (`StatisticsFragment`, `NumberBaseFragment`)**
  * **Unit Converter & Formula Library (`UnitConverterFragment`, `FormulaFragment`)**
* **📜 History Tracking:** Save and review past computation logs seamlessly (`HistoryFragment`).

---

## 📂 Project Architecture

The project follows a clean **Single-Activity, Multi-Fragment** architectural pattern written entirely in **Kotlin**:

```text
ScientificCalculator/
├── app/src/main/
│   ├── java/com/example/scientificcalculator/
│   │   ├── adapter/         # Adapters for lists and history views
│   │   ├── data/            # Data management and models
│   │   ├── fragment/        # Core calculator and functional fragments
│   │   │   ├── CalculatorFragment.kt
│   │   │   ├── EquationFragment.kt
│   │   │   ├── MatrixFragment.kt
│   │   │   └── ...
│   │   ├── model/           # Data structures and entities
│   │   ├── util/            # Helper utilities and extensions
│   │   └── MainActivity.kt  # Host activity
│   └── res/
│       ├── drawable/        # Custom neon button shapes, strokes, and gradients
│       ├── layout/          # XML layouts (activity_main, fragment_*.xml)
│       └── values/          # Strings, themes, and colors configuration
└── AndroidManifest.xml      # App configuration and launcher settings
