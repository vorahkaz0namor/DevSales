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

    companion object {
        val initialProductList: List<Product> =
            listOf(
                Product(
                    id = 0,
                    name = "Apple Watch Series 7",
                    time = 1633392000000,
                    tags = listOf("Часы", "Новый", "Рекомендуем"),
                    amount = 0,
                ),
                Product(
                    id = 1,
                    name = "iPhone 13",
                    time = 1633046400000,
                    tags = listOf("Телефон", "Новый", "Распродажа"),
                    amount = 15,
                ),
                Product(
                    id = 2,
                    name = "Samsung Galaxy S21",
                    time = 1633132800000,
                    tags = listOf("Телефон", "Хит"),
                    amount = 30,
                ),
                Product(
                    id = 3,
                    name = "PlayStation 5",
                    time = 1633219200000,
                    tags = listOf("Игровая приставка", "Акция", "Распродажа"),
                    amount = 7,
                ),
                Product(
                    id = 4,
                    name = "LG OLED TV",
                    time = 1633305600000,
                    tags = listOf("Телевизор", "Эксклюзив", "Ограниченный"),
                    amount = 22,
                ),
                Product(
                    id = 6,
                    name = "Xiaomi Mi 11",
                    time = 1633478400000,
                    tags = listOf("Телефон", "Скидка", "Распродажа"),
                    amount = 5,
                ),
                Product(
                    id = 7,
                    name = "MacBook Air M1",
                    time = 1633564800000,
                    tags = listOf("Ноутбук", "Тренд"),
                    amount = 12,
                ),
                Product(
                    id = 8,
                    name = "Amazon Kindle Paperwhite",
                    time = 1633651200000,
                    tags = listOf("Электронная книга", "Последний шанс", "Ограниченный"),
                    amount = 18,
                ),
                Product(
                    id = 9,
                    name = "Fitbit Charge 5",
                    time = 1633737600000,
                    tags = listOf(),
                    amount = 27,
                ),
                Product(
                    id = 10,
                    name = "GoPro Hero 10",
                    time = 1633824000000,
                    tags = listOf("Камера", "Эксклюзив"),
                    amount = 25,
                ),
            )
    }
}