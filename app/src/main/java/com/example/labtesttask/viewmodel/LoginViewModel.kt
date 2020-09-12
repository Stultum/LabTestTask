package com.example.labtesttask.viewmodel

import androidx.lifecycle.*
import com.example.labtesttask.repository.ProfileRepository
import com.example.shiftlabtesttask.profile.Profile
import kotlinx.coroutines.launch
import java.util.*

class LoginViewModel(private val profileRepository: ProfileRepository) : ViewModel() {


    val firstName = MutableLiveData<String>("")

    val secondName = MutableLiveData<String>("")

    val password = MutableLiveData<String>("")

    val passwordProof = MutableLiveData<String>("")

    val birthday = MutableLiveData<Calendar>()
    val age =
        Transformations.map(birthday) {
            val now = Calendar.getInstance()
            var age = now.get(Calendar.YEAR) - it.get(Calendar.YEAR)
            if (now.get(Calendar.DAY_OF_YEAR) <= it.get(Calendar.DAY_OF_YEAR)) {
                age -= 1
            }
            if (now.get(Calendar.YEAR) <= it.get(Calendar.YEAR)) {
                age = 0
            }

            age
        }

    private val _isRegFinished = MutableLiveData<Boolean>()
    val isRegFinished: LiveData<Boolean>
        get() = _isRegFinished

    private val _firstNameError = MutableLiveData(false)
    val firstNameError: LiveData<Boolean>
        get() = _firstNameError

    private val _secondNameError = MutableLiveData(false)
    val secondNameError: LiveData<Boolean>
        get() = _secondNameError

    private val _passwordError = MutableLiveData(false)
    val passwordError: LiveData<Boolean>
        get() = _passwordError

    private val _passwordProofError = MutableLiveData(false)
    val passwordProofError: LiveData<Boolean>
        get() = _passwordProofError

    private val _birthDateError = MutableLiveData(false)
    val birthDateError: LiveData<Boolean>
        get() = _birthDateError

    private val _ageError = MutableLiveData(false)
    val ageError: LiveData<Boolean>
        get() = _ageError

    fun validate(): Boolean {

        if (firstName.value?.length!! <= 1) {
            _firstNameError.value = true
            return false
        }

        if (secondName.value?.length!! <= 1) {
            _secondNameError.value = true
            return false
        }

        if (!password.value.toString().containsLower() ||
            !password.value.toString().containsUpper() || !password.value.toString().containsDigits()
        ) {
            _passwordError.value = true
            return false
        }

        if (password.value != passwordProof.value) {
            _passwordProofError.value = true
            return false
        }

        if (age.value == null) {
            _birthDateError.value = true
            return false
        }

        if (age.value!! <= 12) {
            _ageError.value = true
            return false
        }
        return true
    }

    fun register() {
        val birthdayString =
            "${birthday.value?.get(Calendar.DAY_OF_MONTH)}." +
                    "${birthday.value?.get(Calendar.MONTH)}." +
                    "${birthday.value?.get(Calendar.YEAR)}"
        val profile =
            Profile(firstName.value!!, secondName.value!!, password.value!!, birthdayString)
        viewModelScope.launch {
            profileRepository.writeProfile(profile)
            _isRegFinished.value = true
        }
    }

    private fun String.containsUpper(): Boolean {
        forEach {
            if (it.isUpperCase())
                return true
        }
        return false
    }

    private fun String.containsLower(): Boolean {
        forEach {
            if (it.isLowerCase())
                return true
        }
        return false
    }

    private fun String.containsDigits(): Boolean {
        forEach {
            if (it.isDigit())
                return true
        }
        return false
    }

    fun getBirthdayString(): String {
        val calendar = birthday.value!!
        return String.format(
            "%02d.%02d.%d",
            calendar.get(Calendar.DAY_OF_MONTH),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.YEAR)
        )
    }


}
