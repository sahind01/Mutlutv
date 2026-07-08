// Channel.kt
package com.mutlumedyasanalortam.mutlutv.models

data class Channel(
    val id: String = "",
    val name: String = "",
    val logo: String = "",
    val stream_url: String = "",
    val category: String = "",
    val isActive: Boolean = true
)

// Movie.kt
package com.mutlumedyasanalortam.mutlutv.models

data class Movie(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val poster: String = "",
    val stream_url: String = "",
    val category: String = "",
    val year: Int = 0,
    val isActive: Boolean = true
)
