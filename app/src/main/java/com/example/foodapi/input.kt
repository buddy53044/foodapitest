package com.example.foodapi

data class input(
    val _links: Links,
    val hints: List<Hint>,
    val parsed: List<Any>,
    val text: String
)

data class Links(
    val next: Next
)

data class Hint(
    val food: Food,
    val measures: List<Measure>
)

data class Next(
    val href: String,
    val title: String
)

data class Food(
    val category: String,
    val categoryLabel: String,
    val foodId: String,
    val image: String,
    val knownAs: String,
    val label: String,
    val nutrients: Nutrients
)

data class Measure(
    val label: String,
    val qualified: List<Qualified>,
    val uri: String,
    val weight: Double
)

data class Nutrients(
    val CHOCDF: Double,
    val ENERC_KCAL: Double,
    val FAT: Double,
    val FIBTG: Double,
    val PROCNT: Double
)

data class Qualified(
    val qualifiers: List<Qualifier>,
    val weight: Double
)

data class Qualifier(
    val label: String,
    val uri: String
)