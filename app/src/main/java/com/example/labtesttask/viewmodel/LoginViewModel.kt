package com.example.labtesttask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class LoginViewModel : ViewModel() {

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

    private val _secondNameError = MutableLiveData<Boolean>(false)
    val secondNameError: LiveData<Boolean>
        get() = _secondNameError

    private val _passwordError = MutableLiveData<Boolean>(false)
    val passwordError: LiveData<Boolean>
        get() = _passwordError

    private val _passwordProofError = MutableLiveData<Boolean>(false)
    val passwordProofError: LiveData<Boolean>
        get() = _firstNameError

    private val _birthDateError = MutableLiveData<Boolean>(false)
    val birthDateError: LiveData<Boolean>
        get() = _birthDateError

    private val _ageError = MutableLiveData<Boolean>(false)
    val ageError: LiveData<Boolean>
        get() = _ageError

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
