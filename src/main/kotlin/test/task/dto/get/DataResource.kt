package test.task.dto.get

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class DataResource(
    val color: String,
    val id: Int,
    val name: String,
    val pantoneValue: String,
    val year: Int
)