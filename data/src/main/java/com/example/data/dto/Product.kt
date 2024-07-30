package com.example.data.dto

data class Product(
    val id: Int,
    val name: String,
    val time: Long,
    val tags: List<String>,
    val amount: Int,
) {
    override fun toString(): String =
        "name: $name\n" +
                "amount = $amount $"
}
