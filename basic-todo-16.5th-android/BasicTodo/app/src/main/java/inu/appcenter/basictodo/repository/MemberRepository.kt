package inu.appcenter.basictodo.repository

import inu.appcenter.basictodo.model.MemberReq
import inu.appcenter.basictodo.model.MemberRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MemberRepository {
    suspend fun getMember(
         memberId: Long
    ) : Response<MemberRes>

    suspend fun deleteMember(
        memberId: Long
    ) : Response<Unit>

    suspend fun login(
        memberReq: MemberReq
    ) : Response<MemberRes>
}