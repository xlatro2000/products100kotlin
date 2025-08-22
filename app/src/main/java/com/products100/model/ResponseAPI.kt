package com.products100.model

data class ResponseAPI(
    val info: Info,
    val results: List<UserResult> = emptyList()
)