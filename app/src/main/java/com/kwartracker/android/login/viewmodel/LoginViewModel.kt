package com.kwartracker.android.login.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kwartracker.android.login.model.User
import com.kwartracker.android.login.repository.LoginRepository
import com.kwartracker.android.utils.CoroutineTask
import com.kwartracker.android.utils.StringValidator
import com.kwartracker.android.utils.TexWatcherHelper
import com.kwartracker.android.utils.extension.get
import com.kwartracker.android.utils.formstate.LoginFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val repository: LoginRepository) :
    ViewModel(),
    Observable {

    val formState = MutableLiveData(LoginFormState())

    val textWatcherHelper = TexWatcherHelper {
        loginDataChanged()
    }

    val user = MutableLiveData<User>()
    private val emailValidator = StringValidator()
        .required()
        .email()
        .lengthRange(2, 50)

    private val passwordValidator = StringValidator()
        .required()
        .lengthRange(2, 50)

    @Bindable
    var emailAddress = MutableLiveData("")
    val etEmailAddress: MutableLiveData<String>
        get() = emailAddress

    @Bindable
    var password = MutableLiveData("")
    val etPassword: MutableLiveData<String>
        get() = password

    init {
        emailAddress.postValue("joey@gmail.com")
        password.postValue("12345678")
    }

    private fun validateEmail(): Boolean {
        return emailValidator.validate(emailAddress.get()) {
            formState.value = formState.get().copy(emailError = it)
        }
    }

    private fun validatePassword(): Boolean {
        return passwordValidator.validate(password.get()) {
            formState.value = formState.get().copy(passwordError = it)
        }
    }

    fun loginDataChanged() {
        val valid = listOf(
            validateEmail(),
            validatePassword()
        )
        formState.value = formState.get().copy(isValid = valid.all { it })
    }

    fun login() {
        if (!formState.get().isValid) return
        userLogin.run()
    }

    val userLogin = CoroutineTask {
        repository.logIn(email = emailAddress.get(), password = password.get())
    }

    override fun onCleared() {
        super.onCleared()
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}
