package test.task

import java.time.LocalDateTime
import java.time.ZoneOffset
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import test.task.Utils.getDateTimeFromStringRequest
import test.task.client.ReqresInApiClient
import test.task.dto.UpdateRequest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PatchTest {
    private val client = ReqresInApiClient()

    @Test
    fun successPatchUpdateTest() {
        val id = 2
        val job = "AQA"
        val name = "Tester"
        val date = LocalDateTime.now(ZoneOffset.UTC).withNano(3)
        val response = client.patchUpdateUser(body = UpdateRequest(job = job, name = name), id = id)
        assertAll(
            { assertThat(response?.job).describedAs("Job не соответствует ожидаемому").isEqualTo(job) },
            { assertThat(response?.name).describedAs("Name не соответствует ожидаемому").isEqualTo(name) },
            {
                assertThat(getDateTimeFromStringRequest(response?.updatedAt))
                    .describedAs("Update at date не соответствует текущей")
                    .isAfterOrEqualTo(date)
            }
        )
    }
}