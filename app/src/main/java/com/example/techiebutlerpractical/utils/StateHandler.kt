package com.example.techiebutlerpractical.utils

data class StateHandler<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val message: String? = null,
    val error: String = ""
)

