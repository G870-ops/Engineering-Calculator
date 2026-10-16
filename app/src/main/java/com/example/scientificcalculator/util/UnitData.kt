package com.example.scientificcalculator.util

object UnitData {

    enum class Category(val displayName: String) {
        LENGTH("Length"),
        MASS("Mass"),
        TEMPERATURE("Temperature"),
        AREA("Area"),
        VOLUME("Volume"),
        SPEED("Speed"),
        TIME("Time"),
        PRESSURE("Pressure"),
        ENERGY("Energy"),
        POWER("Power"),
        FORCE("Force"),
        ANGLE("Angle"),
        DATA("Data Storage")
    }

    data class Unit(
        val name: String,
        val symbol: String,
        val toBase: Double,           // multiply to convert to base unit
        val fromBase: Double = 1.0 / toBase,  // divide base to get this unit
        val isTemperature: Boolean = false
    )

    // Each category has a list of units; index 0 = base unit
    val UNITS: Map<Category, List<Unit>> = mapOf(
        Category.LENGTH to listOf(
            Unit("Meter", "m", 1.0),
            Unit("Kilometer", "km", 1000.0),
            Unit("Centimeter", "cm", 0.01),
            Unit("Millimeter", "mm", 0.001),
            Unit("Micrometer", "μm", 1e-6),
            Unit("Nanometer", "nm", 1e-9),
            Unit("Mile", "mi", 1609.344),
            Unit("Yard", "yd", 0.9144),
            Unit("Foot", "ft", 0.3048),
            Unit("Inch", "in", 0.0254),
            Unit("Light Year", "ly", 9.461e15),
            Unit("Astronomical Unit", "AU", 1.496e11)
        ),
        Category.MASS to listOf(
            Unit("Kilogram", "kg", 1.0),
            Unit("Gram", "g", 0.001),
            Unit("Milligram", "mg", 1e-6),
            Unit("Tonne", "t", 1000.0),
            Unit("Pound", "lb", 0.453592),
            Unit("Ounce", "oz", 0.0283495),
            Unit("Ton (US)", "ton", 907.185),
            Unit("Ton (UK)", "long ton", 1016.05),
            Unit("Carat", "ct", 2e-4),
            Unit("Atomic Mass Unit", "u", 1.6605e-27)
        ),
        Category.TEMPERATURE to listOf(
            Unit("Celsius", "°C", 1.0, isTemperature = true),
            Unit("Fahrenheit", "°F", 1.0, isTemperature = true),
            Unit("Kelvin", "K", 1.0, isTemperature = true),
            Unit("Rankine", "°R", 1.0, isTemperature = true)
        ),
        Category.AREA to listOf(
            Unit("Square Meter", "m²", 1.0),
            Unit("Square Kilometer", "km²", 1e6),
            Unit("Square Centimeter", "cm²", 1e-4),
            Unit("Hectare", "ha", 10000.0),
            Unit("Acre", "ac", 4046.86),
            Unit("Square Foot", "ft²", 0.092903),
            Unit("Square Inch", "in²", 6.4516e-4),
            Unit("Square Mile", "mi²", 2.59e6)
        ),
        Category.VOLUME to listOf(
            Unit("Cubic Meter", "m³", 1.0),
            Unit("Liter", "L", 0.001),
            Unit("Milliliter", "mL", 1e-6),
            Unit("Cubic Centimeter", "cm³", 1e-6),
            Unit("Gallon (US)", "gal", 0.00378541),
            Unit("Quart (US)", "qt", 9.46353e-4),
            Unit("Pint (US)", "pt", 4.73176e-4),
            Unit("Cup", "cup", 2.36588e-4),
            Unit("Fluid Ounce", "fl oz", 2.9574e-5),
            Unit("Cubic Foot", "ft³", 0.0283168),
            Unit("Cubic Inch", "in³", 1.6387e-5)
        ),
        Category.SPEED to listOf(
            Unit("Meter/Second", "m/s", 1.0),
            Unit("Kilometer/Hour", "km/h", 1.0/3.6),
            Unit("Mile/Hour", "mph", 0.44704),
            Unit("Knot", "kn", 0.514444),
            Unit("Foot/Second", "ft/s", 0.3048),
            Unit("Speed of Light", "c", 299792458.0),
            Unit("Mach (at sea level)", "Ma", 340.29)
        ),
        Category.TIME to listOf(
            Unit("Second", "s", 1.0),
            Unit("Millisecond", "ms", 0.001),
            Unit("Microsecond", "μs", 1e-6),
            Unit("Nanosecond", "ns", 1e-9),
            Unit("Minute", "min", 60.0),
            Unit("Hour", "h", 3600.0),
            Unit("Day", "d", 86400.0),
            Unit("Week", "wk", 604800.0),
            Unit("Month (avg)", "mo", 2.628e6),
            Unit("Year", "yr", 3.156e7)
        ),
        Category.PRESSURE to listOf(
            Unit("Pascal", "Pa", 1.0),
            Unit("Kilopascal", "kPa", 1000.0),
            Unit("Megapascal", "MPa", 1e6),
            Unit("Bar", "bar", 1e5),
            Unit("Atmosphere", "atm", 101325.0),
            Unit("Torr (mmHg)", "Torr", 133.322),
            Unit("PSI", "psi", 6894.76),
            Unit("Inches of Mercury", "inHg", 3386.39)
        ),
        Category.ENERGY to listOf(
            Unit("Joule", "J", 1.0),
            Unit("Kilojoule", "kJ", 1000.0),
            Unit("Megajoule", "MJ", 1e6),
            Unit("Calorie", "cal", 4.184),
            Unit("Kilocalorie", "kcal", 4184.0),
            Unit("Watt-Hour", "Wh", 3600.0),
            Unit("Kilowatt-Hour", "kWh", 3.6e6),
            Unit("Electron Volt", "eV", 1.60218e-19),
            Unit("BTU", "BTU", 1055.06),
            Unit("Foot-Pound", "ft·lb", 1.35582)
        ),
        Category.POWER to listOf(
            Unit("Watt", "W", 1.0),
            Unit("Kilowatt", "kW", 1000.0),
            Unit("Megawatt", "MW", 1e6),
            Unit("Horsepower (mech)", "hp", 745.7),
            Unit("BTU/hour", "BTU/h", 0.29307),
            Unit("Calorie/second", "cal/s", 4.184)
        ),
        Category.FORCE to listOf(
            Unit("Newton", "N", 1.0),
            Unit("Kilonewton", "kN", 1000.0),
            Unit("Pound-force", "lbf", 4.44822),
            Unit("Dyne", "dyn", 1e-5),
            Unit("Kilogram-force", "kgf", 9.80665)
        ),
        Category.ANGLE to listOf(
            Unit("Degree", "°", 1.0),
            Unit("Radian", "rad", 180.0 / Math.PI),
            Unit("Gradian", "grad", 0.9),
            Unit("Arcminute", "'", 1.0/60),
            Unit("Arcsecond", "\"", 1.0/3600)
        ),
        Category.DATA to listOf(
            Unit("Byte", "B", 1.0),
            Unit("Kilobyte", "KB", 1024.0),
            Unit("Megabyte", "MB", 1048576.0),
            Unit("Gigabyte", "GB", 1073741824.0),
            Unit("Terabyte", "TB", 1.0995e12),
            Unit("Bit", "bit", 0.125),
            Unit("Kilobit", "Kbit", 128.0),
            Unit("Megabit", "Mbit", 131072.0),
            Unit("Gigabit", "Gbit", 1.342e8)
        )
    )

    fun convert(value: Double, from: Unit, to: Unit, category: Category): Double {
        if (category == Category.TEMPERATURE) {
            return convertTemperature(value, from.symbol, to.symbol)
        }
        val baseValue = value * from.toBase
        return baseValue * to.fromBase
    }

    private fun convertTemperature(value: Double, from: String, to: String): Double {
        val celsius = when (from) {
            "°C" -> value
            "°F" -> (value - 32) * 5.0 / 9.0
            "K"  -> value - 273.15
            "°R" -> (value - 491.67) * 5.0 / 9.0
            else -> value
        }
        return when (to) {
            "°C" -> celsius
            "°F" -> celsius * 9.0 / 5.0 + 32
            "K"  -> celsius + 273.15
            "°R" -> (celsius + 273.15) * 9.0 / 5.0
            else -> celsius
        }
    }
}
