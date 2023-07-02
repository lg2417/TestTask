package test.task

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Utils {
    private val objectMapper = ObjectMapper().registerModule(KotlinModule.Builder().build())

    fun <T> readValue(response: String, className: Class<T>): T {
        return objectMapper.readValue(response, className)
    }

    fun writeValueAsString(response: Any): String {
        return objectMapper.writeValueAsString(response)
    }

    fun getDateTimeFromStringRequest(inputDate: String?): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        return LocalDateTime.parse(inputDate, formatter)
    }
}
