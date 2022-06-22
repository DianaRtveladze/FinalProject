package com.drtveladze.finalapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShopListItem(
    val description: String,
    val image: String,
    val name: String,
    val price: String
) : Parcelable {
    constructor() : this("", "", "", "")
}
