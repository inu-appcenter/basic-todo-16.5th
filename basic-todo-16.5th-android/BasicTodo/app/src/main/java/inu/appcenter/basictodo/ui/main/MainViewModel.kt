package inu.appcenter.basictodo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inu.appcenter.basictodo.model.MemberRes
import inu.appcenter.basictodo.model.TodoReq
import inu.appcenter.basictodo.model.TodoRes
import inu.appcenter.basictodo.model.UpdateTodoReq
import inu.appcenter.basictodo.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class MainUiState(
    val member: MemberRes = MemberRes(memberId = 0, email = ""),
    val todos: List<TodoRes> = emptyList(),
    val selectedTodoRes: TodoRes? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)

class MainViewModel(private val todoRepository: TodoRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        getTodos()
    }

    fun setMember(member: MemberRes) {
        _uiState.update { it.copy(member = member) }
        getTodos()
    }

    fun getTodos() {
        viewModelScope.launch {
            try {
                setLoading(true)
                clearError()

                val response = todoRepository.getTodos(memberId = uiState.value.member.memberId)
                if (response.isSuccessful) {
                    response.body()?.let { todos ->
                        _uiState.update { it.copy(todos = todos) }
                    }
                } else {
                    handleError("Failed to fetch todos: ${response.message()}")
                }
            } catch (e: Exception) {
                handleError(e.message ?: "Unknown error occurred while fetching todos")
            } finally {
                setLoading(false)
            }
        }
    }

    fun selectTodo(todo: TodoRes) {
        _uiState.update { it.copy(selectedTodoRes = todo) }
    }

    fun updateTodoCompleted(selectedTodoRes: TodoRes) {
        viewModelScope.launch {
            try {
                setLoading(true)
                clearError()

                // 현재 Todo 찾기
                val response = todoRepository.updateTodoCompleted(
                    selectedTodoRes.todoId
                )
                if (response.isSuccessful) {
                    getTodos()
                } else {
                    handleError("Failed to update todo status: ${response.message()}")
                }
            } catch (e: Exception) {
                handleError(e.message ?: "Unknown error occurred while updating todo status")
            } finally {
                setLoading(false)
            }
        }
    }

    fun deleteTodo(selectedTodoRes: TodoRes) {
        viewModelScope.launch {
            try {
                setLoading(true)
                clearError()

                val response = todoRepository.deleteTodo(selectedTodoRes.todoId)
                if (response.isSuccessful) {
                    getTodos()
                } else {
                    handleError("Failed to delete todo: ${response.message()}")
                }
            } catch (e: Exception) {
                handleError(e.message ?: "Unknown error occurred while deleting todo")
            } finally {
                setLoading(false)
            }
        }
    }

    fun addTodo(content: String, deadLine: String) {
        viewModelScope.launch {
            try {
                setLoading(true)
                clearError()

                val response = todoRepository.addTodo(
                    TodoReq(
                        memberId = uiState.value.member.memberId,
                        content = content,
                        deadLine = deadLine
                    )
                )
                if (response.isSuccessful) {
                    getTodos()
                } else {
                    handleError("Failed to add todo: ${response.message()}")
                }
            } catch (e: Exception) {
                handleError(e.message ?: "Unknown error occurred while adding todo")
            } finally {
                setLoading(false)
            }
        }
    }

    fun updateTodo(selectedTodoRes: TodoRes, content: String, deadLine: String) {
        viewModelScope.launch {
            try {
                setLoading(true)
                clearError()

                val response = todoRepository.updateTodoContent(
                    todoId = selectedTodoRes.todoId,
                    updateTodoReq = UpdateTodoReq(
                        content = content,
                        deadLine = deadLine,
                    )
                )
                if (response.isSuccessful) {
                    getTodos()
                } else {
                    handleError("Failed to update todo: ${response.message()}")
                }
            } catch (e: Exception) {
                handleError(e.message ?: "Unknown error occurred while updating todo")
            } finally {
                setLoading(false)
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    private fun handleError(message: String) {
        _uiState.update { it.copy(error = message) }
    }

    private fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}