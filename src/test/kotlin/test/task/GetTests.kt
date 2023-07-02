package test.task

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import test.task.client.ReqresInApiClient

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetTests {
    private val client = ReqresInApiClient()

    @Test
    fun successGetListUserTest() {
        val page = 2
        val response = client.getListUser(page = page)
        assertAll(
            {
                assertThat(response?.page).describedAs("Номер страницы не соответствует ожидаемому")
                    .isEqualTo(page)
            },
            {
                assertThat(response?.data?.size).describedAs("Данные не найдены")
                    .isGreaterThan(0)
            },
            {
                assertThat(response?.support?.text).describedAs("Текст в блоке поддержки не соответствует ожидаемому")
                    .isEqualTo("To keep ReqRes free, contributions towards server costs are appreciated!")
            },
            {
                assertThat(response?.support?.url).describedAs("Url в блоке поддержки не соответствует ожидаемому")
                    .isEqualTo("https://reqres.in/#support-heading")
            }
        )
    }

    @Test
    fun successGetSingleUserTest() {
        val id = 4
        val response = client.getSingleUser(id = id)
        assertAll(
            {
                assertThat(response?.data?.id).describedAs("Id не соответствует ожидаемому")
                    .isEqualTo(id)
            },
            {
                assertThat(response?.data?.firstName).describedAs("Id не соответствует ожидаемому")
                    .isEqualTo("Eve")
            },
            {
                assertThat(response?.data?.lastName).describedAs("Id не соответствует ожидаемому")
                    .isEqualTo("Holt")
            },
            {
                assertThat(response?.support?.text).describedAs("Текст в блоке поддержки не соответствует ожидаемому")
                    .isEqualTo("To keep ReqRes free, contributions towards server costs are appreciated!")
            },
            {
                assertThat(response?.support?.url).describedAs("Url в блоке поддержки не соответствует ожидаемому")
                    .isEqualTo("https://reqres.in/#support-heading")
            }
        )
    }

    @Test
    fun successGetListResourceTest() {
        val resource = "unknown"
        val response = client.getListResource(resource = resource)
        assertAll(
            {
                assertThat(response?.page).describedAs("Номер страницы не соответствует ожидаемому")
                    .isEqualTo(1)
            },
            {
                assertThat(response?.data?.size).describedAs("Данные не найдены")
                    .isGreaterThan(0)
            },
            {
                assertThat(response?.support?.text).describedAs("Текст в блоке поддержки не соответствует ожидаемому")
                    .isEqualTo("To keep ReqRes free, contributions towards server costs are appreciated!")
            },
            {
                assertThat(response?.support?.url).describedAs("Url в блоке поддержки не соответствует ожидаемому")
                    .isEqualTo("https://reqres.in/#support-heading")
            }
        )
    }

    @Test
    fun successGetSingleResourceTest() {
        val id = 1
        val resource = "unknown"
        val response = client.getSingleResource(resource = resource, id = id)
        assertAll(
            {
                assertThat(response?.data?.id).describedAs("Id не соответствует ожидаемому")
                    .isEqualTo(id)
            },
            {
                assertThat(response?.data?.name).describedAs("Name не соответствует ожидаемому")
                    .isEqualTo("cerulean")
            },
            {
                assertThat(response?.data?.color).describedAs("Color не соответствует ожидаемому")
                    .isEqualTo("#98B2D1")
            },
            {
                assertThat(response?.data?.year).describedAs("Year не соответствует ожидаемому")
                    .isEqualTo(2000)
            },
            {
                assertThat(response?.data?.pantoneValue).describedAs("Panton value не соответствует ожидаемому")
                    .isEqualTo("15-4020")
            },
            {
                assertThat(response?.support?.text).describedAs("Текст в блоке поддержки не соответствует ожидаемому")
                    .isEqualTo("To keep ReqRes free, contributions towards server costs are appreciated!")
            },
            {
                assertThat(response?.support?.url).describedAs("Url в блоке поддержки не соответствует ожидаемому")
                    .isEqualTo("https://reqres.in/#support-heading")
            }
        )
    }

    @Test
    fun getSingleUserNotFoundTest() {
        val id = 44
        val response = client.getSingleUserNotFound(id = id)
        assertAll(
            {
                assertThat(response.code).describedAs("Код ответа не соответствует ожидаемому")
                    .isEqualTo(404)
            },
            {
                assertThat(response.body?.string()).describedAs("Тело ответа не пустое")
                    .isEqualTo("{}")
            }
        )
    }

    @Test
    fun getSingleResourceNotFoundTest() {
        val id = 123
        val resource = "unknown"
        val response = client.getSingleResourceNotFound(resource = resource, id = id)
        assertAll(
            {
                assertThat(response.code).describedAs("Код ответа не соответствует ожидаемому")
                    .isEqualTo(404)
            },
            {
                assertThat(response.body?.string()).describedAs("Тело ответа не пустое")
                    .isEqualTo("{}")
            }
        )
    }
}