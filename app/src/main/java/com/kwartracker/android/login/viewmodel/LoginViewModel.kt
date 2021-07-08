package com.kwartracker.android.login.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kwartracker.android.R
import com.kwartracker.android.login.repository.LoginRepository
import com.kwartracker.android.utils.CoroutineTask
import com.kwartracker.android.utils.StringValidator
import com.kwartracker.android.utils.extension.get
import com.kwartracker.android.utils.formstate.LoginFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val repository: LoginRepository) :
    ViewModel(),
    Observable {

    val formState = MutableLiveData(LoginFormState())

    private val emailValidator = StringValidator(R.string.lbl_email)
        .required()
        .email()
        .lengthRange(2, 50)

    private val passwordValidator = StringValidator(R.string.lbl_password)
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
        emailAddress.postValue("")
        password.postValue("")
    }

    fun validateEmail(): Boolean {
        return emailValidator.validate(emailAddress.get()) {
            formState.value = formState.get().copy(emailError = it)
        }
    }

    fun validatePassword(): Boolean {
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

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}
