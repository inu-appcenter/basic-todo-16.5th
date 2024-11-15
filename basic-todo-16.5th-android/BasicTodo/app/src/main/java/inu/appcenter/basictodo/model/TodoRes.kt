package inu.appcenter.basictodo.model


data class TodoRes(
    val memberId: Long,
    val content: String,
    val deadLine: String,
    val isCompleted: Boolean,
)
