package test.task.dto.get

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Data(
    val avatar: String,
    val email: String,
    val firstName: String,
    val id: Int,
    val lastName: String
)