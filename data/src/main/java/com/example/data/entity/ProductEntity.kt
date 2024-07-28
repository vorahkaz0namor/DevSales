package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.dto.Product

@Entity
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val time: Int,
    val tags: List<String>,
    val amount: Int,
) {
    fun toDto() =
        Product(
            id = id,
            name = name,
            time = time,
            tags = tags,
            amount = amount,
        )

    companion object {
        fun fromDto(dtoProduct: Product) =
            ProductEntity(
                id = dtoProduct.id,
                name = dtoProduct.name,
                time = dtoProduct.time,
                tags = dtoProduct.tags,
                amount = dtoProduct.amount,
            )
    }
}
