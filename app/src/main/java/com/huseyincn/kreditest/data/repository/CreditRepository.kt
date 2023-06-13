package com.huseyincn.kreditest.data.repository

import com.huseyincn.kreditest.data.model.CreditResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CreditRepository() {
    private val apiService: CreditCheck

    init {

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        // Create Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.31:8080/") // Replace with your API base URL
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()

        // Create ApiService instance
        apiService = retrofit.create(CreditCheck::class.java)
    }

    suspend fun getData(
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
    ): Response<CreditResult> = apiService.check(
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

    suspend fun check(
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
    ): Result<CreditResult> {
        return try {
            val response = apiService.check(
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
                val creditResponse = response.body()
                if (creditResponse != null) {
                    Result.success(creditResponse)
                } else {
                    Result.failure(Error("Login response is null"))
                }
            } else {
                Result.failure(Error("Login failed"))
            }
        } catch (e: Exception) {
            Result.failure(Error("Login exception: ${e.message}"))
        }
    }
}