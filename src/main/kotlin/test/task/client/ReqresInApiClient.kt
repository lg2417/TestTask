package test.task.client

import okhttp3.Response
import test.task.dto.UpdateRequest
import test.task.dto.UpdateResponse
import test.task.dto.get.GetListResourceResponse
import test.task.dto.get.GetListUserResponse
import test.task.dto.get.GetSingleResourceResponse
import test.task.dto.get.GetSingleUserResponse
import test.task.dto.post.PostRegisterRequest
import test.task.dto.post.PostRegisterSuccessfulResponse
import test.task.dto.post.PostRegisterUnsuccessfulResponse

class ReqresInApiClient : BaseClient() {
    companion object {
        const val url = "https://reqres.in/api"
    }

    fun getListUser(page: Int? = 2): GetListUserResponse? {
        val response = get("$url/users?page=$page")
        return mapResponse(response, GetListUserResponse::class.java, true)
    }

    fun getSingleUser(id: Int? = null): GetSingleUserResponse? {
        val response = get("$url/users/$id")
        return mapResponse(response, GetSingleUserResponse::class.java, true)
    }

    fun getSingleUserNotFound(id: Int? = null): Response {
        return get("$url/users/$id")
    }

    fun getListResource(resource: String? = "unknown"): GetListResourceResponse? {
        val response = get("$url/$resource")
        return mapResponse(response, GetListResourceResponse::class.java, true)
    }

    fun getSingleResource(resource: String? = "unknown", id: Int? = null): GetSingleResourceResponse? {
        val response = get("$url/$resource/$id")
        return mapResponse(response, GetSingleResourceResponse::class.java, true)
    }

    fun getSingleResourceNotFound(resource: String? = "unknown", id: Int? = null): Response {
        return get("$url/$resource/$id")
    }

    fun postRegisterUserSuccess(body: PostRegisterRequest): PostRegisterSuccessfulResponse? {
        val response = post("$url/register", createRequest(body))
        return mapResponse(response, PostRegisterSuccessfulResponse::class.java, true)
    }

    fun postRegisterUserUnsuccessful(body: PostRegisterRequest): PostRegisterUnsuccessfulResponse? {
        val response = post("$url/register", createRequest(body))
        return mapResponse(response, PostRegisterUnsuccessfulResponse::class.java, false)
    }

    fun patchUpdateUser(body: UpdateRequest, id: Int): UpdateResponse? {
        val response = patch("$url/users/$id", createRequest(body))
        return mapResponse(response, UpdateResponse::class.java, true)
    }

    fun putUpdateUser(body: UpdateRequest, id: Int): UpdateResponse? {
        val response = put("$url/users/$id", createRequest(body))
        return mapResponse(response, UpdateResponse::class.java, true)
    }

    fun deleteUser(id: Int): Response {
        return delete("$url/users/$id")
    }

}