package inu.appcenter.basictodo.di

import inu.appcenter.basictodo.network.RetrofitAPI
import inu.appcenter.basictodo.repository.MemberRepository
import inu.appcenter.basictodo.repository.MemberRepositoryImpl
import inu.appcenter.basictodo.repository.TodoRepository
import inu.appcenter.basictodo.repository.TodoRepositoryImpl
import inu.appcenter.basictodo.ui.auth.AuthViewModel
import inu.appcenter.basictodo.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { RetrofitAPI() }
    single { get<RetrofitAPI>().apiService }

    single<MemberRepository> { MemberRepositoryImpl(get()) }
    single<TodoRepository> { TodoRepositoryImpl(get()) }

    viewModel{ MainViewModel(get()) }
    viewModel{ AuthViewModel(get()) }
}