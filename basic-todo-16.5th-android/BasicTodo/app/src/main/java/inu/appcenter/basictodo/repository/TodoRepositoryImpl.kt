package inu.appcenter.basictodo.repository

import inu.appcenter.basictodo.model.TodoReq
import inu.appcenter.basictodo.model.TodoRes
import inu.appcenter.basictodo.model.UpdateTodoReq
import inu.appcenter.basictodo.network.APIService
import retrofit2.Response

class TodoRepositoryImpl(private val apiService: APIService): TodoRepository {
    override suspend fun getTodos(memberId: Long): Response<List<TodoRes>> {
        return apiService.getTodos(memberId)
    }

    override suspend fun addTodo(todoReq: TodoReq): Response<TodoRes> {
        return apiService.addTodo(todoReq)
    }

    override suspend fun updateTodoContent(
        todoId: Long,
        updateTodoReq: UpdateTodoReq
    ): Response<TodoRes> {
        return apiService.updateTodoContent(todoId, updateTodoReq)
    }

    override suspend fun updateTodoCompleted(todoId: Long): Response<TodoRes> {
        return apiService.updateTodoCompleted(todoId)
    }


    override suspend fun deleteTodo(todoId: Long): Response<Unit> {
        return apiService.deleteTodo(todoId)
    }
}