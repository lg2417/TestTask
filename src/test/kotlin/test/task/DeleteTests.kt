package test.task

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import test.task.client.ReqresInApiClient

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeleteTests {
    private val client = ReqresInApiClient()

    @Test
    fun successDeleteUserTest() {
        val response = client.deleteUser(id = 2)
        assertAll(
            {
                assertThat(response.code).describedAs("Код ответа не соответствует ожидаемому")
                    .isEqualTo(204)
            },
            {
                assertThat(response.body?.string()).describedAs("Тело ответа не пустое")
                    .isEqualTo("")
            }
        )
    }
}