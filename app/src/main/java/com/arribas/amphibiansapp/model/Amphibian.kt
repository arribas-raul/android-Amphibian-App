package com.arribas.amphibiansapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Amphibian(
    val id: Int = (0..1000).random(),
    val name: String,
    val type: String,
    val description: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)
