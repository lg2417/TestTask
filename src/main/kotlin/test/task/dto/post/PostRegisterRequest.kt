package test.task.dto.post

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PostRegisterRequest(
    val email: String? = null,
    val password: String? = null,
    val username: String? = null
)