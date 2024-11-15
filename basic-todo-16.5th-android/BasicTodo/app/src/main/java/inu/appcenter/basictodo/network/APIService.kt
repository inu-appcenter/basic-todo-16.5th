package inu.appcenter.basictodo.network

import inu.appcenter.basictodo.model.MemberReq
import inu.appcenter.basictodo.model.MemberRes
import inu.appcenter.basictodo.model.TodoReq
import inu.appcenter.basictodo.model.TodoRes
import inu.appcenter.basictodo.model.UpdateTodoReq
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface APIService {

    // TODO
    @GET("/api/todos")
    suspend fun getTodos(
        memberId: Long
    ) : Response<List<TodoRes>>

    @POST("/api/todos")
    suspend fun addTodo(
        @Body todoReq: TodoReq
    ) : Response<TodoRes>

    @PUT("/api/todos/{todoId}")
    suspend fun updateTodo(
        @Path("todoId") todoId: Long,
        @Body updateTodoReq: UpdateTodoReq
    ) : Response<TodoRes>

    @DELETE("/api/todos/{todoId}")
    suspend fun deleteTodo(
        @Path("todoId") todoId: Long
    ) : Response<Unit>

    // MEMBER
    @GET("/api/members/{memberId}")
    suspend fun getMember(
        @Path("memberId") memberId: Long
    ) : Response<MemberRes>

    @DELETE("/api/members/{memberId}")
    suspend fun deleteMember(
        @Path("memberId") memberId: Long
    ) : Response<Unit>

    @POST("/api/members/login")
    suspend fun login(
        @Body memberReq: MemberReq
    ) : Response<MemberRes>
}