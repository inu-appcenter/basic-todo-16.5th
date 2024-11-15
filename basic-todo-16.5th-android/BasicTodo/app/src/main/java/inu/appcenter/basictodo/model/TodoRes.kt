package inu.appcenter.basictodo.model


data class TodoRes(
    val todoId: Long,
    val content: String,
    val deadLine: String,
    val isCompleted: Boolean,
)
