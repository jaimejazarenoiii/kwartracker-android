package com.kwartracker.android.signup.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kwartracker.android.signup.repository.SignupRepository
import com.kwartracker.android.utils.GenderType
import com.kwartracker.android.utils.StringValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(val repository: SignupRepository) :
    ViewModel(),
    Observable {

//    val userLogin = CoroutineTask {
//        repository.signUp()
//    }

    private val gender = StringValidator()
        .required()

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
    var age = MutableLiveData(0)
    val etAge: MutableLiveData<Int?>
        get() = age

    @Bindable
    var genderType = MutableLiveData(GenderType.MALE)
    val tvGenderType: MutableLiveData<GenderType>
        get() = genderType

    init {
        genderType.postValue(GenderType.MALE)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}
