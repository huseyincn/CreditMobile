package com.huseyincn.kreditest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huseyincn.kreditest.data.model.CreditResult
import com.huseyincn.kreditest.data.repository.CreditRepository
import kotlinx.coroutines.launch

class CreditViewModel : ViewModel() {
    private val _data = MutableLiveData<CreditResult>()
    val data: LiveData<CreditResult> = _data

    private val repository: CreditRepository

    init {
        repository = CreditRepository()
    }

    fun clearData(){
        _data.value=CreditResult(" ")
    }

    fun getData(
        checking_status: String,
        duration: String,
        credit_history: String,
        purpose: String,
        credit_amount: String,
        savings_status: String,
        employment: String,
        installment_commitment: String,
        other_parties: String,
        residence_since: String,
        property_magnitude: String,
        age: String,
        other_payment_plans: String,
        housing: String,
        existing_credits: String,
        job: String,
        num_dependents: String,
        own_telephone: String,
        foreign_worker: String,
        sex: String,
        marriage: String
    ) {
        viewModelScope.launch {
            val response = repository.getData(
                checking_status,
                duration,
                credit_history,
                purpose,
                credit_amount,
                savings_status,
                employment,
                installment_commitment,
                other_parties,
                residence_since,
                property_magnitude,
                age,
                other_payment_plans,
                housing,
                existing_credits,
                job,
                num_dependents,
                own_telephone,
                foreign_worker,
                sex,
                marriage
            )
            if (response.isSuccessful) {
                _data.value = response.body()
            }
        }
    }
}