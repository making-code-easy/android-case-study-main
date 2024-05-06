package com.target.targetcasestudy.data.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DealsListResponse(
    @Json(name = "products")
    val products: List<Product>
) {
    @JsonClass(generateAdapter = true)
    data class Product(
        @Json(name = "aisle")
        val aisle: String,
        @Json(name = "availability")
        val availability: String,
        @Json(name = "description")
        val description: String,
        @Json(name = "fulfillment")
        val fulfillment: String,
        @Json(name = "id")
        val id: String,
        @Json(name = "image_url")
        val imageUrl: String,
        @Json(name = "regular_price")
        val regularPrice: RegularPrice,
        @Json(name = "sale_price")
        val salePrice: SalePrice = SalePrice(),
        @Json(name = "title")
        val title: String
    ) {
        @JsonClass(generateAdapter = true)
        data class RegularPrice(
            @Json(name = "amount_in_cents")
            val amountInCents: Int,
            @Json(name = "currency_symbol")
            val currencySymbol: String,
            @Json(name = "display_string")
            val displayString: String
        )

        @JsonClass(generateAdapter = true)
        data class SalePrice(
            @Json(name = "amount_in_cents")
            val amountInCents: Int = 0,
            @Json(name = "currency_symbol")
            val currencySymbol: String = "",
            @Json(name = "display_string")
            val displayString: String = ""
        )
    }
}