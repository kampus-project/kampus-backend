package com.example.kampusbackend.exception

data class ErrorMessageModel(
    var status: Int? = null,
    var message: String? = null
)