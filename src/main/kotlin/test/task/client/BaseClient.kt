package test.task.client

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import test.task.ClientException
import test.task.Utils

open class BaseClient {

    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder().addInterceptor(logging).build()

    fun get(url: String): Response {
        val request = Request.Builder()
            .url(url)
            .build()
        return client.newCall(request).execute()
    }

    fun post(url: String, body: RequestBody): Response {
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()
        return client.newCall(request).execute()
    }

    fun put(url: String, body: RequestBody): Response {
        val request = Request.Builder()
            .url(url)
            .put(body)
            .build()
        return client.newCall(request).execute()
    }

    fun patch(url: String, body: RequestBody): Response {
        val request = Request.Builder()
            .url(url)
            .patch(body)
            .build()
        return client.newCall(request).execute()
    }

    fun delete(url: String): Response {
        val request = Request.Builder()
            .url(url)
            .delete()
            .build()
        return client.newCall(request).execute()
    }

    fun <T> mapResponse(response: Response, className: Class<T>, checkIsSuccessful: Boolean): T? {
        if (response.isSuccessful == checkIsSuccessful) {
            return response.body?.let { Utils.readValue(it.string(), className) }
        }
        throw ClientException("Ошибка при выполнении запроса. Статус код ответа: " + response.code.toString())
    }

    fun createRequest(body: Any): RequestBody {
        return Utils.writeValueAsString(body).toRequestBody("application/json".toMediaTypeOrNull())
    }

}
