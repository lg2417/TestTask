package test.task

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import test.task.client.ReqresInApiClient
import test.task.dto.post.PostRegisterRequest


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostTests {
    private val client = ReqresInApiClient()

    @Test
    fun successPostRegisterTest() {
        val body = PostRegisterRequest(
            email = "eve.holt@reqres.in",
            password = "pistol"
        )
        val id = 4
        val token = "QpwL5tke4Pnpja7X4"
        val response = client.postRegisterUserSuccess(body = body)
        assertAll(
            {
                assertThat(response?.id).describedAs("Id не соответствует ожидаемому")
                    .isEqualTo(id)
            },
            {
                assertThat(response?.token).describedAs("Token не соответствует ожидаемому")
                    .isEqualTo(token)
            }
        )
    }

    @Test
    fun unsuccessfulPostRegisterOnlyEmailTest() {
        val body = PostRegisterRequest(
            email = "eve.holt@reqres.in"
        )
        val response = client.postRegisterUserUnsuccessful(body = body)
        assertThat(response?.error).describedAs("Ошибка не соответствует ожидаемой")
            .isEqualTo("Missing password")
    }

    @Test
    fun unsuccessfulPostRegisterOnlyPasswordTest() {
        val body = PostRegisterRequest(
            password = "pistol"
        )
        val response = client.postRegisterUserUnsuccessful(body = body)
        assertThat(response?.error).describedAs("Ошибка не соответствует ожидаемой")
            .isEqualTo("Missing email or username")
    }

    @Test
    fun unsuccessfulPostRegisterEmptyTest() {
        val body = PostRegisterRequest()
        val response = client.postRegisterUserUnsuccessful(body = body)
        assertThat(response?.error).describedAs("Ошибка не соответствует ожидаемой")
            .isEqualTo("Missing email or username")
    }

    @Test
    fun unsuccessfulPostRegisterNonexistentUserTest() {
        val body = PostRegisterRequest(
            email = "test.test@reqres.in",
            password = "TEST"
        )
        val response = client.postRegisterUserUnsuccessful(body = body)
        assertThat(response?.error).describedAs("Ошибка не соответствует ожидаемой")
            .isEqualTo("Note: Only defined users succeed registration")
    }
}