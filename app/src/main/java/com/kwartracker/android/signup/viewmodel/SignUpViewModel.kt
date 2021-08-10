package com.kwartracker.android.signup.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kwartracker.android.R
import com.kwartracker.android.signup.repository.SignupRepository
import com.kwartracker.android.utils.GenderType
import com.kwartracker.android.utils.StringValidator
import com.kwartracker.android.utils.TexWatcherHelper
import com.kwartracker.android.utils.ToastHelper
import com.kwartracker.android.utils.extension.get
import com.kwartracker.android.utils.formstate.SignUpFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(val repository: SignupRepository) :
    ViewModel(), Observable {

    val formState = MutableLiveData(SignUpFormState())

    val textWatcherHelper = TexWatcherHelper {
        validateFields()
    }

    private val emailValidator = StringValidator(R.string.lbl_email)
        .required()
        .email()
        .lengthRange(2, 50)

    private val passwordValidator = StringValidator(R.string.lbl_password)
        .required()
        .lengthRange(2, 50)

    private val detailsValidator = StringValidator()
        .required()
        .lengthRange(2, 50)

    private val ageValidator = StringValidator(R.string.lbl_age)
        .required()
        .validateAge(18)
        .maxLength(2)

    @Bindable
    var emailAddress = MutableLiveData("")
    val etEmailAddress: MutableLiveData<String>
        get() = emailAddress

    @Bindable
    var password = MutableLiveData("")
    val etPassword: MutableLiveData<String>
        get() = password

    @Bindable
    var confpassword = MutableLiveData("")
    val etConfPassword: MutableLiveData<String>
        get() = confpassword

    @Bindable
    var firstName = MutableLiveData("")
    val etFirstName: MutableLiveData<String>
        get() = firstName

    @Bindable
    var lastName = MutableLiveData("")
    val etLastNamee: MutableLiveData<String>
        get() = lastName

    @Bindable
    var middleName = MutableLiveData("")
    val etMiddleName: MutableLiveData<String>
        get() = middleName

    @Bindable
    var age = MutableLiveData("")
    val etAge: MutableLiveData<String>
        get() = age

    @Bindable
    var genderType = MutableLiveData(GenderType.MALE)
    val tvGenderType: MutableLiveData<GenderType>
        get() = genderType

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

    private fun validateConfPassword(): Boolean {
        return passwordValidator.validate(confpassword.get()) {
            formState.value = formState.get().copy(passwordError = it)
        }
    }

    private fun validateFirstName(): Boolean {
        return detailsValidator.validate(firstName.get()) {
            formState.value = formState.get().copy(firstNameError = it)
        }
    }

    private fun validateLastName(): Boolean {
        return detailsValidator.validate(lastName.get()) {
            formState.value = formState.get().copy(lastNameError = it)
        }
    }

    private fun validateAge(): Boolean {
        return ageValidator.validate(age.get()) {
            formState.value = formState.get().copy(ageError = it)
        }
    }

    init {
        emailAddress.postValue("")
        password.postValue("")
        confpassword.postValue("")
        firstName.postValue("")
        middleName.postValue("")
        lastName.postValue("")
        age.postValue("")
        genderType.postValue(GenderType.MALE)
    }

    fun validateFields() {
        val valid = listOf(
            validateEmail(),
            validatePassword(),
            validateConfPassword(),
            validateAge(),
            validateFirstName(),
            validateLastName()
        )
        formState.value = formState.get().copy(isValid = valid.all { it })
    }

    fun signup() {
        if (!formState.get().isValid) return
        ToastHelper.showText("Valid")
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}
