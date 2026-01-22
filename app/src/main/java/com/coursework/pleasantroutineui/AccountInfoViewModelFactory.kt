package com.coursework.pleasantroutineui

import androidx.lifecycle.ViewModel
import com.coursework.pleasantroutineui.pages.AccountInfoViewModel
import com.coursework.pleasantroutineui.repo.IAccountRepo

class AccountInfoViewModelFactory(
    private val repository: IAccountRepo
) : androidx.lifecycle.ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountInfoViewModel::class.java)) {
            return AccountInfoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
