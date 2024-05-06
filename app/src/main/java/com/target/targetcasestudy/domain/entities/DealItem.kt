package com.target.targetcasestudy.domain.entities

data class DealItem(
    var id: String,
    val salePrice: String = "",
    val regularPrice: String,
    val status: String,
    var title: String,
    var description: String,
    var aisle: String,
    var imageUrl: String
)