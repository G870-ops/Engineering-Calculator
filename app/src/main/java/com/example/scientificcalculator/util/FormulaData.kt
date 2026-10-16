package com.example.scientificcalculator.util

object FormulaData {

    data class Formula(
        val name: String,
        val equation: String,
        val description: String,
        val category: String
    )

    val ALL_FORMULAS = listOf(
        // Physics - Mechanics
        Formula("Newton's 2nd Law", "F = m × a", "Force = mass × acceleration", "Physics"),
        Formula("Work Done", "W = F × d × cos(θ)", "Work = Force × displacement × angle", "Physics"),
        Formula("Kinetic Energy", "KE = ½mv²", "Energy of a moving object", "Physics"),
        Formula("Potential Energy", "PE = mgh", "Gravitational potential energy", "Physics"),
        Formula("Power", "P = W/t = F×v", "Power = Work per unit time", "Physics"),
        Formula("Momentum", "p = mv", "Linear momentum of an object", "Physics"),
        Formula("Impulse", "J = F×Δt = Δp", "Change in momentum", "Physics"),
        Formula("Velocity (uniform acc.)", "v = u + at", "Final velocity with constant acceleration", "Physics"),
        Formula("Distance (uniform acc.)", "s = ut + ½at²", "Displacement with constant acceleration", "Physics"),
        Formula("v² Equation", "v² = u² + 2as", "Velocity-displacement relation", "Physics"),
        Formula("Centripetal Force", "F = mv²/r", "Force for circular motion", "Physics"),
        Formula("Angular Velocity", "ω = 2πf = 2π/T", "Rotational speed", "Physics"),

        // Physics - Waves & Optics
        Formula("Wave Speed", "v = fλ", "Speed = frequency × wavelength", "Physics"),
        Formula("Snell's Law", "n₁sin(θ₁) = n₂sin(θ₂)", "Refraction of light", "Physics"),
        Formula("Lens Formula", "1/f = 1/v - 1/u", "Thin lens equation", "Physics"),

        // Physics - Electricity
        Formula("Ohm's Law", "V = IR", "Voltage = Current × Resistance", "Electrical"),
        Formula("Power (Electrical)", "P = VI = I²R = V²/R", "Electrical power", "Electrical"),
        Formula("Coulomb's Law", "F = kq₁q₂/r²", "Electrostatic force between charges", "Electrical"),
        Formula("Electric Field", "E = F/q = kQ/r²", "Field strength at a point", "Electrical"),
        Formula("Capacitance", "C = Q/V", "Charge stored per volt", "Electrical"),
        Formula("Inductance (EMF)", "EMF = -L(dI/dt)", "Induced EMF in inductor", "Electrical"),
        Formula("Resistors in Series", "R_T = R₁ + R₂ + ...", "Total series resistance", "Electrical"),
        Formula("Resistors in Parallel", "1/R_T = 1/R₁ + 1/R₂", "Total parallel resistance", "Electrical"),
        Formula("Kirchhoff's Voltage Law", "ΣV = 0", "Sum of voltages in loop = 0", "Electrical"),
        Formula("Kirchhoff's Current Law", "ΣI_in = ΣI_out", "Current entering = current leaving", "Electrical"),
        Formula("RLC Resonance Freq.", "f₀ = 1/(2π√(LC))", "Natural frequency of RLC circuit", "Electrical"),

        // Mathematics
        Formula("Quadratic Formula", "x = (-b ± √(b²-4ac)) / 2a", "Roots of ax² + bx + c = 0", "Math"),
        Formula("Pythagorean Theorem", "a² + b² = c²", "Right triangle sides relation", "Math"),
        Formula("Distance Formula", "d = √((x₂-x₁)² + (y₂-y₁)²)", "Distance between two points", "Math"),
        Formula("Slope of Line", "m = (y₂-y₁)/(x₂-x₁)", "Gradient of a straight line", "Math"),
        Formula("Area of Circle", "A = πr²", "Area enclosed by a circle", "Math"),
        Formula("Circumference", "C = 2πr = πd", "Perimeter of a circle", "Math"),
        Formula("Area of Triangle", "A = ½ × b × h", "Area of a triangle", "Math"),
        Formula("Binomial Theorem", "(a+b)ⁿ = Σ C(n,k)aⁿ⁻ᵏbᵏ", "Expansion of binomial power", "Math"),
        Formula("Taylor Series (eˣ)", "eˣ = 1 + x + x²/2! + x³/3! + ...", "Exponential function series", "Math"),
        Formula("Sum of AP", "S = n/2(2a + (n-1)d)", "Sum of arithmetic progression", "Math"),
        Formula("Sum of GP", "S = a(1-rⁿ)/(1-r)", "Sum of geometric progression", "Math"),
        Formula("Euler's Formula", "e^(iθ) = cos(θ) + i·sin(θ)", "Complex exponential identity", "Math"),
        Formula("Bayes' Theorem", "P(A|B) = P(B|A)P(A)/P(B)", "Conditional probability", "Math"),

        // Chemistry
        Formula("Ideal Gas Law", "PV = nRT", "P=pressure, V=volume, n=moles, R=8.314, T=Kelvin", "Chemistry"),
        Formula("Molar Mass", "n = m/M", "moles = mass / molar mass", "Chemistry"),
        Formula("pH Formula", "pH = -log[H⁺]", "Acidity of a solution", "Chemistry"),
        Formula("Arrhenius Equation", "k = A·e^(-Ea/RT)", "Reaction rate vs temperature", "Chemistry"),
        Formula("Nernst Equation", "E = E° - (RT/nF)lnQ", "Cell potential at non-standard conditions", "Chemistry"),
        Formula("First Order Kinetics", "ln[A] = ln[A]₀ - kt", "Concentration decay over time", "Chemistry"),
        Formula("Avogadro's Number", "Nₐ = 6.022 × 10²³ /mol", "Particles per mole", "Chemistry"),

        // Thermodynamics
        Formula("First Law of Thermo.", "ΔU = Q - W", "Internal energy change", "Thermo"),
        Formula("Ideal Gas Work", "W = nRT·ln(V₂/V₁)", "Isothermal expansion work", "Thermo"),
        Formula("Heat Transfer (Conv.)", "Q = mcΔT", "Sensible heat change", "Thermo"),
        Formula("Fourier's Law", "q = -k·A·(dT/dx)", "Heat conduction rate", "Thermo"),
        Formula("Carnot Efficiency", "η = 1 - T_L/T_H", "Maximum thermal efficiency", "Thermo"),
        Formula("Entropy Change", "ΔS = Q_rev/T", "Entropy at constant temperature", "Thermo"),

        // Fluid Mechanics
        Formula("Bernoulli's Equation", "P + ½ρv² + ρgh = const", "Energy conservation in fluids", "Fluids"),
        Formula("Continuity Equation", "A₁v₁ = A₂v₂", "Flow rate conservation", "Fluids"),
        Formula("Reynolds Number", "Re = ρvD/μ", "Flow regime indicator", "Fluids"),
        Formula("Darcy-Weisbach", "hf = f(L/D)(v²/2g)", "Head loss in pipe flow", "Fluids"),

        // Signals & Systems
        Formula("Fourier Transform", "F(ω) = ∫f(t)e^(-jωt)dt", "Time to frequency domain", "Signals"),
        Formula("Sampling Theorem", "fs ≥ 2·fmax", "Nyquist sampling condition", "Signals"),
        Formula("Transfer Function", "H(s) = Y(s)/X(s)", "System input-output ratio (Laplace)", "Signals"),
        Formula("Time Constant (RC)", "τ = RC", "Charging/discharging time constant", "Signals"),

        // Structures
        Formula("Stress", "σ = F/A", "Normal stress in material", "Structures"),
        Formula("Strain", "ε = ΔL/L", "Deformation per unit length", "Structures"),
        Formula("Young's Modulus", "E = σ/ε", "Material stiffness", "Structures"),
        Formula("Bending Moment (UDL)", "M = wL²/8", "Max moment for simply supported beam", "Structures"),
        Formula("Moment of Inertia (rect.)", "I = bh³/12", "Second moment of area (rectangle)", "Structures"),
    )

    val CATEGORIES = ALL_FORMULAS.map { it.category }.distinct().sorted()
}
