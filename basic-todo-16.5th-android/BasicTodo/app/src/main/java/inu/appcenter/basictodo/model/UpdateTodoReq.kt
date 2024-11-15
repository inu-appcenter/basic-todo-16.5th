package inu.appcenter.basictodo.model

data class UpdateTodoReq(
    val content: String,
    val deadLine: String,
    val isCompleted: Boolean
)
