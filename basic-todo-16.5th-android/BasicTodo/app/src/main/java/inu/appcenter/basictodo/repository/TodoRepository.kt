package inu.appcenter.basictodo.repository

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

interface TodoRepository {

    suspend fun getTodos(
        memberId: Long
    ) : Response<List<TodoRes>>

    suspend fun addTodo(
        todoReq: TodoReq
    ) : Response<TodoRes>

    suspend fun updateTodo(
        todoId: Long,
        updateTodoReq: UpdateTodoReq
    ) : Response<TodoRes>

    suspend fun deleteTodo(
        todoId: Long
    ) : Response<Unit>
}