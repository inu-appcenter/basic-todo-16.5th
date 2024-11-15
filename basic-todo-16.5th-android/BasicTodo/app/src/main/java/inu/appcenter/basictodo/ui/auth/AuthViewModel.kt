package inu.appcenter.basictodo.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inu.appcenter.basictodo.model.MemberReq
import inu.appcenter.basictodo.repository.MemberRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val loginEmail: String = "",
    val loginPassword: String = "",
    val signupEmail: String = "",
    val signupPassword: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLoggedIn: Boolean = false
)

sealed class AuthResult {
    object Success : AuthResult()
    data class Error(val message: String) : AuthResult()
}

class AuthViewModel(private val memberRepository: MemberRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun login() {
        viewModelScope.launch {
            try {
                setLoading(true)
                clearError()

                val response = memberRepository.login(
                    MemberReq(
                        email = _uiState.value.loginEmail,
                        password = _uiState.value.loginPassword
                    )
                )

                if (response.isSuccessful) {
                    response.body()?.let {
                        _uiState.value = _uiState.value.copy(
                            loginEmail = "",
                            loginPassword = "",
                            isLoggedIn = true
                        )
                    }
                } else {
                    handleError("Login failed: ${response.message()}")
                }
            } catch (e: Exception) {
                handleError(e.message ?: "Unknown error occurred during login")
            } finally {
                setLoading(false)
            }
        }
    }

    fun signup() {
        viewModelScope.launch {
            try {
                setLoading(true)
                clearError()

                val response = memberRepository.login(  // Changed from login to signup
                    MemberReq(
                        email = _uiState.value.signupEmail,
                        password = _uiState.value.signupPassword
                    )
                )

                if (response.isSuccessful) {
                    response.body()?.let {
                        _uiState.value = _uiState.value.copy(
                            signupEmail = "",
                            signupPassword = "",
                            isLoggedIn = true
                        )
                    }
                } else {
                    handleError("Signup failed: ${response.message()}")
                }
            } catch (e: Exception) {
                handleError(e.message ?: "Unknown error occurred during signup")
            } finally {
                setLoading(false)
            }
        }
    }

    fun logout() {
        _uiState.value = AuthUiState()  // Reset to initial state
    }

    fun updateLoginEmail(email: String) {
        _uiState.value = _uiState.value.copy(loginEmail = email)
    }

    fun updateLoginPassword(password: String) {
        _uiState.value = _uiState.value.copy(loginPassword = password)
    }

    fun updateSignupEmail(email: String) {
        _uiState.value = _uiState.value.copy(signupEmail = email)
    }

    fun updateSignupPassword(password: String) {
        _uiState.value = _uiState.value.copy(signupPassword = password)
    }

    private fun setLoading(isLoading: Boolean) {
        _uiState.value = _uiState.value.copy(isLoading = isLoading)
    }

    private fun handleError(message: String) {
        _uiState.value = _uiState.value.copy(
            error = message,
            isLoggedIn = false
        )
    }

    private fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}