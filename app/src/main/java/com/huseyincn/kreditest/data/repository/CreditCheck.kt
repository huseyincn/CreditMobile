package com.huseyincn.kreditest.data.repository

import com.huseyincn.kreditest.data.model.CreditResult
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CreditCheck {

    @FormUrlEncoded
    @POST("predict")
    suspend fun check(
        @Field("checking_status") credit_status: String,
        @Field("duration") duration: String,
        @Field("credit_history") credit_history: String,
        @Field("purpose") purpose: String,
        @Field("credit_amount") credit_amount: String,
        @Field("savings_status") saving_status: String,
        @Field("employment") employment: String,
        @Field("installment_commitment") installment_commitment: String,
        @Field("other_parties") other_parties: String,
        @Field("residence_since") residence_since: String,
        @Field("property_magnitude") property_magnitude: String,
        @Field("age") age: String,
        @Field("other_payment_plans") other_payment_plans: String,
        @Field("housing") housing: String,
        @Field("existing_credits") existing_credits: String,
        @Field("job") job: String,
        @Field("num_dependents") num_dependents: String,
        @Field("own_telephone") own_telephone: String,
        @Field("foreign_worker") foreign_worker: String,
        @Field("sex") sex: String,
        @Field("marriage") marriage: String,
    ): Response<CreditResult>
}