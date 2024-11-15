package inu.appcenter.basictodo.repository

import inu.appcenter.basictodo.model.MemberReq
import inu.appcenter.basictodo.model.MemberRes
import inu.appcenter.basictodo.network.APIService
import retrofit2.Response

class MemberRepositoryImpl(private val apiService: APIService) : MemberRepository {
    override suspend fun getMember(memberId: Long): Response<MemberRes> {
        return apiService.getMember(memberId)
    }

    override suspend fun deleteMember(memberId: Long): Response<Unit> {
        return apiService.deleteMember(memberId)
    }

    override suspend fun login(memberReq: MemberReq): Response<MemberRes> {
        return apiService.login(memberReq)
    }
}