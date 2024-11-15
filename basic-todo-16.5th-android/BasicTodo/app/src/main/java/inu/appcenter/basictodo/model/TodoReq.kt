package inu.appcenter.basictodo.model

data class TodoReq(
    val memberId: Long,
    val content: String,
    val deadLine: String,
)
