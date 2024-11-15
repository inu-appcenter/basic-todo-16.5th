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
    val todoRes: List<TodoRes> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedTodoRes: TodoRes? = null
)

class MainViewModel(private val todoRepository: TodoRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
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
                        _uiState.update { it.copy(todoRes = todos) }
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

    fun selectTodo(id: Long) {
        val selectedTodo = _uiState.value.todoRes.find { it.memberId == id }
        _uiState.update { it.copy(selectedTodoRes = selectedTodo) }
    }

    fun updateTodoCompleted(selectedTodoRes: TodoRes, isCompleted: Boolean) {
        viewModelScope.launch {
            try {
                setLoading(true)
                clearError()

                // 현재 Todo 찾기
                val response = todoRepository.updateTodo(
                    selectedTodoRes.memberId,
                    UpdateTodoReq(
                        content = selectedTodoRes.content,  // 기존 내용 유지
                        deadLine = selectedTodoRes.deadLine,  // 기존 마감일 유지
                        isCompleted = isCompleted  // 완료 상태만 업데이트
                    )
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

    fun deleteTodo(id: Long) {
        viewModelScope.launch {
            try {
                setLoading(true)
                clearError()

                val response = todoRepository.deleteTodo(id)
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

    fun addTodo(title: String, date: String) {
        viewModelScope.launch {
            try {
                setLoading(true)
                clearError()

                val response = todoRepository.addTodo(
                    TodoReq(
                        memberId = uiState.value.member.memberId,
                        content = title,
                        deadLine = date
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

    fun updateTodo(id: Long, title: String, finishDate: String) {
        viewModelScope.launch {
            try {
                setLoading(true)
                clearError()

                val response = todoRepository.updateTodo(
                    id,
                    UpdateTodoReq(
                        content = title,
                        deadLine = finishDate,
                        isCompleted = false
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